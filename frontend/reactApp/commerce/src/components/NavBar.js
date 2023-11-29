import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

//general navbar 
function NavBar(){
    return (
       
        <div>
            <Navbar bg="light" data-bs-theme="light">
                <Container>
                <Navbar.Brand href="#home">Change Management System</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="#myChanges">My Changes</Nav.Link>
                    <Nav.Link href="#outstandingChanges">Outstanding Changes</Nav.Link>
                    <Nav.Link href="#completedChanges">Completed Changes</Nav.Link>
                    <Nav.Link href="#reqForm">Create Change Request</Nav.Link>
                    <Nav.Link href="#Login">Log Out</Nav.Link>
                </Nav>
                </Container>
             </Navbar>         
        </div>

    );

}

export default NavBar