import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

function LoginComponent() {
  return (
    <Form>
        <Form.Group className="mb-3" controlId="userId">
            <Form.Label>User ID</Form.Label>
            <Form.Control type="id" placeholder="User ID" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
            <Form.Text className="text-muted">
                Case sensitive.
            </Form.Text>
        </Form.Group>
        
        <Button variant="primary" type="submit">
            Log In
        </Button>
    </Form>
  );
}

export default LoginComponent;