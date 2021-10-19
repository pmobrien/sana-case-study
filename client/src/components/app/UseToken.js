import { useState } from 'react';

export default function UseToken() {

  const getToken = () => {
    return JSON.parse(localStorage.getItem('token'));
  };

  const getUsername = () => {
    return localStorage.getItem('username');
  };

  const [token, setToken] = useState(getToken());
  const [username, setUsername] = useState(getUsername());

  const saveToken = (token) => {
    localStorage.setItem('token', JSON.stringify(token));
    setToken(token);
  };

  const saveUsername = (username) => {
    localStorage.setItem('username', username);
    setUsername(username);
  }

  const deleteToken = () => {
    localStorage.clear();
    setToken(null);
    setUsername(null);
  }

  return {
    token,
    username,
    setToken: saveToken,
    setUsername: saveUsername,
    deleteToken: deleteToken
  }
}
