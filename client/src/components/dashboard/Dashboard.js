import React from 'react';

import Button from 'react-bootstrap/Button';

export default function Dashboard({deleteToken}) {

  const logout = async (e) => {
    deleteToken();
  }

  return(
    <div>
      <h2>Dashboard</h2>
      <Button onClick={logout}>Logout</Button>
    </div>
  );
}
