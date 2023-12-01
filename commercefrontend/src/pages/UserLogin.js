import React from 'react';
import { useState } from 'react';
import { useAuth } from '../components/AuthContext';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';

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
              props.history.push('/');
            }else{
              alert('fails');
            }
          
          });
      } 
  
  
  
    return (
      <div>
  
          <Form onSubmit={submitUserLogin}>
              <Container>
                <Row>
                      <Form.Label>Username</Form.Label>
                      <Form.Control 
                          type="text"
                          id= 'userId'
                          name = "userId"
                          onChange = {changeValue}
                      />
                  </Row>
  
                  <Row>
                      <Form.Label>Password</Form.Label>
                      <Form.Control 
                          type="title"
                          id='password'
                          name = "password"
                          onChange = {changeValue}
                      />
                    </Row>

              </Container>
              <br></br>
  
              <Button as="input" type="Submit" value="Submit" />
  
  
        </Form>
  
  
  
  
      </div>
    );
  }
  
  export default UserLogin;