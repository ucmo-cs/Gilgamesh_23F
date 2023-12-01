import React from 'react';
import { useState } from 'react';
import { useAuth } from '../components/AuthContext';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';
import fullCBLogo from '../images/commerce-bank-full-logo.png';

function UserLogin(props) {
  
      const[userLogin, setUserLogin] = useState({
          userId:'',
          password:''
        });
      
      
        const changeValue=(e)=>{
          console.log(e);
  
          setUserLogin({
           ...userLogin, [e.target.name]:e.target.value  
          });
          console.log(e.target.name + " name "  );
          console.log(e.target.value + " value " );
  
  
          console.log(userLogin);
        }
  
        const submitUserLogin =(e)=>{
          e.preventDefault();
          fetch("http://localhost:8080/user/login", {
            method:"POST",
            headers:{
              "Content-Type" : "application/json"
            },
            body: JSON.stringify(userLogin)
          })
          .then(res=>{
              console.log(1,res);
              if(res.status === 200){
                return res.json();
              }else{
                return null;
              }
            })
          .then(res=>{
            console.log(res)
            if(res!==null){
              setUserLogin(res)
              props.history.push('/changerequest');
            }else{
              alert('Invalid Username or Password');
            }
          
          });
      } 


    const loginDiv = {
      border: '2px solid #006747',
      borderRadius: '10px',
      backgroundColor: '',
      margin: '50px auto',
      padding: '25px',
      width: '400px'
    }

    const logoStyle = {
      display: 'block',
      margin: 'auto',
      marginTop: '100px',
      marginBottom: '40px'
    }

    const projectTitle = {
      fontSize: '40px',
      textAlign: 'center',
      color: '#006747',
    }

    const loginButton = {
      display: 'block',
      margin: 'auto',
      width: '100px',
      height: '40px',
      backgroundColor: '#006747',
      color: 'white'
    }

    const biggerText = {
      margin: '10px',
      fontSize: '20px'
    }
  
  
    return (
      <>
      <img src={fullCBLogo} style={logoStyle}></img>
      <h1 style={projectTitle}>Change Management System</h1>
      <div style={loginDiv}>
  
          <Form onSubmit={submitUserLogin}>
              <Container>
                <Row>
                      <Form.Label style={biggerText}>Username</Form.Label>
                      <Form.Control 
                          type="text"
                          id= 'userId'
                          name = "userId"
                          onChange = {changeValue}
                      />
                  </Row>
  
                  <Row>
                      <Form.Label style={biggerText}>Password</Form.Label>
                      <Form.Control 
                          type="title"
                          id='password'
                          name = "password"
                          onChange = {changeValue}
                      />
                    </Row>

              </Container>
              <br></br>
  
              <Button as="input" type="Submit" value="Login" style={loginButton}/>
  
  
        </Form>
  
  
  
  
      </div>
      </>
    );
  }
  
  export default UserLogin;