package com.pmobrien.webserver;

import com.google.common.base.Strings;
import com.pmobrien.webserver.exceptions.UncaughtExceptionMapper;
import com.pmobrien.webserver.mappers.DefaultObjectMapper;
import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.model.HelloWorld;
import com.pmobrien.webserver.services.impl.HelloWorldService;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;

import com.pmobrien.webserver.services.impl.LocationsService;
import com.pmobrien.webserver.services.impl.TokenService;
import com.pmobrien.webserver.services.impl.UsersService;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;

public class Application {
  
  private static final String WEBAPP_RESOURCE_PATH = "/com/pmobrien/webserver/webapp";
  private static final String INDEX_HTML_PATH = String.format("%s/index.html", WEBAPP_RESOURCE_PATH);
  
  private static final ApplicationProperties PROPERTIES = ApplicationProperties.load();
  
  public static void main(String[] args) throws Exception {
    try {
      new Application().run(new Server());
    } catch(Throwable ex) {
      ex.printStackTrace(System.out);
    }
  }
  
  private Application() {
    Sessions.sessionOperation(session -> session.save(new HelloWorld()));
  }
  
  public static ApplicationProperties getProperties() {
    return PROPERTIES;
  }
  
  private void run(Server server) {
    try {      
      server.setHandler(configureHandlers());
      server.addConnector(configureConnector(server));
      
      server.start();
      server.join();
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    } finally {
      server.destroy();
    }
  }
  
  private HandlerList configureHandlers() throws MalformedURLException, URISyntaxException {
    HandlerList handlers = new HandlerList();
    handlers.setHandlers(
        new Handler[] {
          configureApiHandler(),
          configureStaticHandler()
        }
    );
    
    return handlers;
  }
  
  private ServletContextHandler configureApiHandler() {
    ServletContextHandler handler = new ServletContextHandler();
    handler.setContextPath("/api");
    
    handler.addServlet(
        new ServletHolder(
            new ServletContainer(
                new ResourceConfig()
                    .register(TokenService.class)
                    .register(UsersService.class)
                    .register(LocationsService.class)
                    .register(HelloWorldService.class)
                    .register(DefaultObjectMapper.class)
                    .register(UncaughtExceptionMapper.class)
            )
        ),
        "/*"
    );

    if (Application.getProperties().getConfiguration().getClient().getCors().isEnabled()) {
      FilterHolder cors = handler.addFilter(CrossOriginFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
      cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
      cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
      cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD,DELETE");
      cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
    }
    
    return handler;
  }
  
  private ServletContextHandler configureStaticHandler() throws MalformedURLException, URISyntaxException {
    ServletContextHandler handler = new ServletContextHandler();
    handler.setContextPath("/");
    
    handler.setBaseResource(
        Resource.newResource(
            URI.create(
                this.getClass().getResource(INDEX_HTML_PATH)
                    .toURI()
                    .toASCIIString()
                    .replaceFirst("/index.html$", "/")
            )
        )
    );
    
    handler.setWelcomeFiles(new String[] { "index.html" });
    handler.addServlet(DefaultServlet.class, "/");
    
    return handler;
  }
  
  private ServerConnector configureConnector(Server server) {
    HttpConfiguration config = new HttpConfiguration();
    config.addCustomizer(new SecureRequestCustomizer());
    config.addCustomizer(new ForwardedRequestCustomizer());
    
    HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(config);
    ServerConnector httpConnector = new ServerConnector(server, httpConnectionFactory);
    httpConnector.setPort(Application.getProperties().getConfiguration().getHttp().getPort());
    server.addConnector(httpConnector);
    
    if(Application.PROPERTIES.getConfiguration().getHttps().isEnabled()) {
      if(Application.PROPERTIES.getConfiguration().getHttps().getPort() == null
          || Strings.isNullOrEmpty(Application.PROPERTIES.getConfiguration().getHttps().getKeyStorePath())
          || Strings.isNullOrEmpty(Application.PROPERTIES.getConfiguration().getHttps().getKeyStorePassword())) {
        throw new RuntimeException("https.port, https.keyStorePath, and https.keyStorePassword are all required for https.");
      }

      SslContextFactory sslContextFactory = new SslContextFactory();
      sslContextFactory.setKeyStoreType("PKCS12");
      sslContextFactory.setKeyStorePath(Application.PROPERTIES.getConfiguration().getHttps().getKeyStorePath());
      sslContextFactory.setKeyStorePassword(Application.PROPERTIES.getConfiguration().getHttps().getKeyStorePassword());
      sslContextFactory.setKeyManagerPassword(Application.PROPERTIES.getConfiguration().getHttps().getKeyStorePassword());
      
      ServerConnector connector = new ServerConnector(server, sslContextFactory, httpConnectionFactory);
      connector.setPort(Application.PROPERTIES.getConfiguration().getHttps().getPort());
      
      return connector;
    } else {
      return new ServerConnector(server, httpConnectionFactory);
    }
  }
}
