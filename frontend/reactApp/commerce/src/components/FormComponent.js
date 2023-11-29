import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Dropdown } from 'react-bootstrap';
import '../css/styles.css'

function FormComponent() {
    return (
      <Form>
          <div>
            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Select Change Type
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
                    <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
                    <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>

            <>
            <Form.Label htmlFor="inputPassword5">Team</Form.Label>
            <Form.Control
                type="id"
                id="inputPassword5"
                aria-describedby="passwordHelpBlock"
            />
            <Form.Text id="passwordHelpBlock" muted>
                Please enter the 3 letter identifer for the application team.
            </Form.Text>
            </>
          </div>
          <div>
            <Form.Label>Brief Title</Form.Label>
            <Form.Control 
                type="title"
                id='title'
            />
            <Form.Text>
                50 character limit.
            </Form.Text>
          </div>
      </Form>
    );
}

export default FormComponent;