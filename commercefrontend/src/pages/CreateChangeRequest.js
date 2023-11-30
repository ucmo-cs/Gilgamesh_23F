import React from 'react';
import { useState } from 'react';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';
 
function CreateChangeRequest(props) {



    const[changereqeust, setChangeRequests] = useState({
        applicationId:'',
        description:'',
        startTime:'',
        endDate:'',
        changeType:''
      });
    
    
      const changeValue=(e)=>{
        console.log(e);

        setChangeRequests({
         ...changereqeust, [e.target.name]:e.target.value  
        });
        console.log(e.target.name + " name "  );
        console.log(e.target.value + " value " );


        console.log(changereqeust);
      }


      const submitChangeRequest =(e)=>{
        e.preventDefault();
        fetch("http://localhost:8080/request", {
          method:"POST",
          headers:{
            "Content-Type" : "application/json"
          },
          body: JSON.stringify(changereqeust)
        })
        .then(res=>{
            console.log(1,res);
            if(res.status === 201){
              return res.json();
            }else{
              return null;
            }
          })
        .then(res=>{
          console.log(res)
          if(res!==null){
            props.history.push('/changerequest');
          }else{
            alert('fails');
          }
        
        });
    } 



  return (
    <div>

        <Form onSubmit={submitChangeRequest}>
            <Container>
                <Row>
                    <Col>
                        <Form.Label>
                            Change Type
                        </Form.Label>
                        <Form.Select defaultValue='Select Change Type'
                            name = "changeType"
                            onChange = {changeValue}
                        >
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
                        name = "applicationId"
                        onChange = {changeValue}
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
                        name = "title"
                        onChange = {changeValue}
                    />
                    <Form.Text>
                        50 character limit.
                    </Form.Text>
                <Row>

                </Row>
                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                        type="title"
                        id='description'
                        name = "description"
                        onChange = {changeValue}
                    />

                    <Form.Text>
                        50 character limit.
                    </Form.Text>
                <Row>


                    <Col>
                        <Form.Label>Start Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput' defaultValue=''                        
                         name = "startTime"
                         onChange = {changeValue}/>
                    </Col>
                    <Col>
                        <Form.Label>End Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput' defaultValue=''
                            name = "endDate"
                            onChange = {changeValue}
                        />
                    </Col>
                </Row>
            </Container>
            <br></br>

            <Button as="input" type="Submit" value="Submit" />


      </Form>




    </div>
  );
}

export default CreateChangeRequest;
