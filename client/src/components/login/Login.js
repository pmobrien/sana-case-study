import React, { useState } from 'react';
import PropTypes from 'prop-types';
import Button from 'react-bootstrap/Button';

import './Login.css';

async function login(credentials) {
  return fetch('http://localhost:15000/api/tokens', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(credentials)
  })
  .then(data => data.json());
}

export default function Login({setToken, setUsername}) {

  const [loginUsername, setLoginUsername] = useState();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!loginUsername) {
      return;
    }

    const token = await login({
      loginUsername
    });
    setToken(token.token);
    setUsername(loginUsername);
  };

  return(
    <div className="center">
      <h1 className="horizontal-center">Sana Case Study - Log In</h1>
      <form className="horizontal-center" onSubmit={handleSubmit}>
        <input autoFocus className="login-username horizontal-center" type="text" placeholder="username" onChange={(e) => setLoginUsername(e.target.value)} />
        <p>(no password required - if username doesn't exist, a new user will be created)</p>
        <Button className="login-button" variant="dark" type="submit" onSubmit={handleSubmit}>Login</Button>
      </form>
    </div>
  )
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired
}
