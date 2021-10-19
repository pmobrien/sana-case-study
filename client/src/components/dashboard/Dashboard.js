import React, { useCallback, useEffect, useState } from 'react';

import { Button, Col, Form, Row } from 'react-bootstrap';

import Navbar from '../navbar/Navbar.js'
import ThresholdCard from './ThresholdCard.js';

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
        <Form className="add-threshold-form">
          <Row className="align-items-center">
            <Col xs="auto">
              <Form.Label htmlFor="inlineFormInput" visuallyHidden>
                City Name
              </Form.Label>
              <Form.Control className="mb-2" id="inlineFormInput" placeholder="enter city name..." />
            </Col>
            <Col xs="auto">
              <Button type="submit" className="mb-2">
                Add
              </Button>
            </Col>
          </Row>
        </Form>

        <div>
          {thresholds.map(threshold => <ThresholdCard threshold={threshold} />)}
        </div>
      </div>
    </div>
  );
}
