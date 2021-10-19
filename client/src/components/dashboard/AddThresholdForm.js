import React, { useState } from 'react';

import { Button, Col, Form, Row } from 'react-bootstrap';

async function addThreshold(username, threshold) {
  return fetch(`/api/users/${username}/thresholds`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(threshold)
  })
  .then(data => data.json());
}

export default function AddThresholdForm({username, loadThresholds}) {

  const [location, setLocation] = useState();
  const [aqi, setAqi] = useState();

  const onClick = (e) => {
    if (!location || !aqi) {
      return;
    }

    addThreshold(username, { locationId: location, threshold: aqi })
      .then(_ => loadThresholds());
  };

 return (
  <Form className="add-threshold-form">
    <Row className="align-items-center">
      <Col xs={8}>
        <Form.Label htmlFor="cityNameInput" visuallyHidden>
          City Name
        </Form.Label>
        <Form.Control className="mb-2" id="cityNameInput" placeholder="enter city name..." onChange={(e) => setLocation(e.target.value)} />
      </Col>
      <Col xs={2}>
        <Form.Label htmlFor="aqiInput" visuallyHidden>
          AQI Threshold
        </Form.Label>
        <Form.Control className="mb-2" id="aqiInput" type="number" placeholder="aqi" onChange={(e) => setAqi(e.target.value)}/>
      </Col>
      <Col xs="auto">
        <Button className="mb-2" onClick={((e) => onClick(e))}>
          Add
        </Button>
      </Col>
    </Row>
  </Form>
 );
}