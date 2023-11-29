import React from 'react';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';
import '../css/styles.css'

function FormComponent() {
    return (
      <Form>
            <Container>
                <Row>
                    <Col>
                        <Form.Label>
                            Change Type
                        </Form.Label>
                        <Form.Select defaultValue='Select Change Type'>
                            <option>Planned</option>
                            <option>Unplanned</option>
                            <option>Emergency</option>
                        </Form.Select>
                    </Col>

                    <Col>
                    <>
                    <Form.Label htmlFor="inputPassword5">Team</Form.Label>
                    <Form.Control
                        type="id"
                        id='teamId'
                    />
                    <Form.Text id="passwordHelpBlock" muted>
                        Please enter the 3 letter identifer for the application team.
                    </Form.Text>
                    </>
                    </Col>
                </Row>
                    <Form.Label>Brief Title</Form.Label>
                    <Form.Control 
                        type="title"
                        id='title'
                    />
                    <Form.Text>
                        50 character limit.
                    </Form.Text>
                <Row>
                    <Col>
                        <Form.Label>Start Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput' defaultValue=''/>
                    </Col>
                    <Col>
                        <Form.Label>End Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput' defaultValue=''/>
                    </Col>
                </Row>
            </Container>
      </Form>
    );
}

export default FormComponent;