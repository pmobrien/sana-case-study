import React, { useState } from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';

import Button from 'react-bootstrap/Button';

export default function Dashboard({deleteToken, username}) {

  const logout = async (e) => {
    deleteToken();
  }

  return(
    <div>
      <Navbar>
        <Container>
          <Nav className="mr-auto my-2 my-lg-0">
            <Navbar.Brand>Sana - Case Study</Navbar.Brand>
          </Nav>

          <Nav>
            <Nav.Link>{username}</Nav.Link>
            <Nav.Link>
              <Button variant="outline-dark" onClick={logout}>Logout</Button>
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
}
