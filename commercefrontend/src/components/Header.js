import react from 'react';
import React, { useState } from 'react';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';
import {Link} from 'react-router-dom';
import Logout from './Logout';

 
function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
<>
  <Navbar bg="dark" variant="dark">
    <Container>
    <Link to ="/changerequest"  className = "navbar-brand">Change Management System</Link>

    <Nav className="me-auto">
      <Link to ="/changerequest" className = "nav-link">My Changes</Link>
      <Link to ="/outstanding" className = "nav-link">Outstanding Changes</Link>
      <Link to ="/completed" className = "nav-link">Completed Changes</Link>
      <Link to ="/create" className = "nav-link">Create ChangeReqeust</Link>
      {isLoggedIn ? (
        <Logout />
      ) : (
        <Link to="/login" className = "nav-link">Logout</Link>
      )}

  
    </Nav>
    </Container>
  </Navbar>
  <br />
 
 
</>
  );
}

export default App;

