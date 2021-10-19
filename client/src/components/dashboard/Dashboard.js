import React, { useCallback, useEffect, useState } from 'react';

import Navbar from '../navbar/Navbar.js'
import ThresholdCard from './ThresholdCard.js';
import AddThresholdForm from './AddThresholdForm.js';

import './Dashboard.css';

export default function Dashboard({deleteToken, username}) {

  const [thresholds, setThresholds] = useState([]);

  const loadThresholds = useCallback(async () => {
    await fetch(`http://localhost:15000/api/users/${username}/thresholds`)
      .then(res => res.json())
      .then(
        (result) => {
          setThresholds(result);
        },
        (error) => {
          console.error(error);
        }
      );
  }, [username]);

  useEffect(() => {
    loadThresholds();
  }, [loadThresholds]);

  if (!thresholds) {
    return false;
  }

  return(
    <div>
      <Navbar deleteToken={deleteToken} username={username} />

      <div className="dashboard-container">
        <h2>My Air Quality Thresholds</h2>
        <AddThresholdForm username={username} loadThresholds={loadThresholds} />

        <div>
          {thresholds.map(threshold => <ThresholdCard username={username} threshold={threshold} loadThresholds={loadThresholds} />)}
        </div>
      </div>
    </div>
  );
}
