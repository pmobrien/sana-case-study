import React, { useState } from 'react';

import { Button, Card, Col, Container, Row } from 'react-bootstrap';

import './ThresholdCard.css'

async function checkLocationAirQuality(locationId) {
  return fetch(`http://localhost:15000/api/locations/${locationId}/air-quality`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(data => data.json());
}

async function deleteThreshold(username, thresholdId) {
  return fetch(`http://localhost:15000/api/users/${username}/thresholds/${thresholdId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    }
  });
}

export default function ThresholdCard({username, threshold, loadThresholds}) {

  const [message, setMessage] = useState();
  const [messageColor, setMessageColor] = useState();

  const onCheckClick = async (e, data) => {
    const city = await checkLocationAirQuality(data.city.name);

    setMessage(`AQI for ${threshold.city.name} is currently ${city.aqi}`);
    setMessageColor(city.aqi >= threshold.threshold ? 'text-danger' : 'text-success');
  };

  const onDeleteClick = async (e) => {
    deleteThreshold(username, threshold.uuid)
      .then(_ => loadThresholds());
  };

  return (
    <Card className="threshold-card" style={{ width: '50rem' }} key={threshold.uuid}>
      <Container>
        <Card.Body>
          <Row>
            <Col xs={2}>
              <Card.Title>{threshold.city.name}</Card.Title>
            </Col>
            <Col xs={1}>
              <Button className="threshold-card-button" variant="outline-primary">{threshold.threshold}</Button>
            </Col>
            <Col xs={1}>
              <Button className="threshold-card-button" variant="outline-dark" onClick={((e) => onCheckClick(e, threshold))}>Check</Button>
            </Col>
            <Col xs={1}>
            </Col>
            <Col xs={6}>
              <p className={messageColor}>{message}</p>
            </Col>
            <Col xs={1}>
              <Button className="threshold-card-button" variant="danger" onClick={onDeleteClick}>X</Button>
            </Col>
          </Row>
        </Card.Body>
      </Container>
    </Card>
  );
}
