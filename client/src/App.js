import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import Dashboard from './components/dashboard/Dashboard';
import Login from './components/login/Login';
import UseToken from "./components/app/UseToken";

import './App.css';

function App() {

  const { token, setToken } = UseToken();

  if (!token) {
    return <Login setToken={setToken} />
  }

  return (
    <div className="wrapper">
      <h1>Application</h1>
      <BrowserRouter>
        <Switch>
          <Route path="/dashboard">
            <Dashboard />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
