import { useState } from 'react';

export default function UseToken() {

  const getToken = () => {
    return JSON.parse(localStorage.getItem('token'));
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (token) => {
    localStorage.setItem('token', JSON.stringify(token));
    setToken(token);
  };

  const deleteToken = () => {
    localStorage.clear();
    setToken(null);
  }

  return {
    token,
    setToken: saveToken,
    deleteToken: deleteToken
  }
}
