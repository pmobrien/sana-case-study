import React, { useEffect, useState } from "react";
import logo from './logo.svg';

import './App.css';

function App() {

  const [hello, setHello] = useState(null);
  const [world, setWorld] = useState(null);

  useEffect(() => {
    fetch('/api/hello-world')
      .then(res => res.json())
      .then(
        (result) => {
          setHello(result.hello);
          setWorld(result.world);
        },
        (error) => {
          console.error(error);
        }
      )
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>{hello} {world}</p>
      </header>
    </div>
  );
}

export default App;
