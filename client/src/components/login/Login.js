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
  .then(data => data.json())
}

export default function Login({setToken}) {

  const [username, setUsername] = useState();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = await login({
      username
    });
    setToken(token.token);
  }

  return(
    <div className="center">
      <h1 className="horizontal-center">Log In</h1>
      <form className="horizontal-center" onSubmit={handleSubmit}>
        <input className="login-username horizontal-center" type="text" placeholder="username" onChange={(e) => setUsername(e.target.value)}/>
        <p>(no password required - if username doesn't exist, a new user will be created)</p>
        <div className="horizontal-center">
          <Button variant="dark" type="submit">Login</Button>
        </div>
      </form>
    </div>
  )
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired
}
