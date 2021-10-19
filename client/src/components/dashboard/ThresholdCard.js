import React from 'react';

import { Button, Card, Col, Container, Row } from 'react-bootstrap';

import './ThresholdCard.css'

export default function ThresholdCard({threshold}) {
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
              <Button className="threshold-card-button" variant="outline-dark">Check</Button>
            </Col>
            <Col>
              <Button className="threshold-card-button float-end" variant="danger">X</Button>
            </Col>
          </Row>
        </Card.Body>
      </Container>
    </Card>
  );
}
