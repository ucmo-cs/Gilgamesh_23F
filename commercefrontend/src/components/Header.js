import react from 'react';
import React, { useState } from 'react';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';
import {Link} from 'react-router-dom';
import Logout from './Logout';
import cbLogo from '../images/commerce-bank-logo.png'
 
function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const headerStyle = {
    backgroundColor: '#f2f2f2',
    color: '#006747',
    height: '100px',
    borderBottom: '2px solid #006747'
  };

  const logoStyle = {
    padding: '5px',
  }

  const navButtons = {
    backgroundColor: '#f2f2f2',
    border: 'none',
    height: '98px',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
    margin: '0px',
  }

  return (
<>
  <Navbar style={headerStyle}>
    <Container>
      <img src={cbLogo} alt="logo" style={logoStyle}></img>
    <Link to ="/changerequest"  className = "navbar-brand">Change<br></br>Management<br></br>System</Link>

    <Nav className="me-auto">
      <Link to ="/changerequest" className = "nav-link"><button style={navButtons} className='navButtons'>My Changes</button></Link>
      <Link to ="/outstanding" className = "nav-link"><button style={navButtons} className='navButtons'>Outstanding Changes</button></Link>
      <Link to ="/completed" className = "nav-link"><button style={navButtons} className='navButtons'>Completed Changes</button></Link>
      <Link to ="/create" className = "nav-link"><button style={navButtons} className='navButtons'>Create Change Request</button></Link>
      <Logout />


  
    </Nav>
    </Container>
  </Navbar>
  <br />
 
 
</>
  );
}

export default App;

