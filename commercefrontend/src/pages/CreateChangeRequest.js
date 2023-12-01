import React from 'react';
import { useState } from 'react';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';
 
function CreateChangeRequest(props) {

  const [reason, setReason] = useState('');
  const [whyOccurring, setWhyOccurring] = useState('');
  const [backoutPlan, setBackoutPlan] = useState('');

    const[changereqeust, setChangeRequests] = useState({
        applicationId:'',
        description:'',
        reason:'',
        startDate:'',
        endDate:'',
        changeType:'',
        whyOccurring:'',
        backoutPlan:'',
        backoutMinutes:'',
        riskAssessment: '',
      });
    

      const reaminingCharactersReasonFunction=(e)=>{
        setReason(e.target.value);
        changeValue(e);
      }

      const reaminingCharactersWhyOccurringFunction=(e)=>{
        setWhyOccurring(e.target.value);
        changeValue(e);
      }

      const reaminingCharactersBackoutPlanFunction=(e)=>{
        setBackoutPlan(e.target.value);
        changeValue(e);
      }
    
      const changeValue=(e)=>{
        console.log(e);

        setChangeRequests({
         ...changereqeust, [e.target.name]:e.target.value  
        });
        console.log(e.target.name + " name "  );
        console.log(e.target.value + " value " );


        console.log(changereqeust);
      }
      
      const reaminingCharactersReason = 200 - reason.length;
      const reaminingCharactersWhyOccurring = 200 - whyOccurring.length;
      const reaminingCharactersBackoutPlan = 200 - backoutPlan.length;

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
                        maxLength = {3}
                    />
                    <Form.Text id="passwordHelpBlock" muted>
                        Please enter the 3 letter identifer for the application team.
                    </Form.Text>
                    </>
                    </Col>
                </Row>

                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                        type="text"
                        id='description'
                        name = "description"
                        onChange = {changeValue}
                        maxLength={80}
                    />
                    <Form.Text>
                        80 character limit.
                    </Form.Text>

                
                <Row>
                  <Col>
                      <Form.Label>Reason</Form.Label>
                      <Form.Select defaultValue='Pick a Reason'
                            name = "reason"
                            onChange = {changeValue}
                        >
                            <option>Fix</option>
                            <option>Enhancement</option>
                        </Form.Select>
                  </Col>


                    <Col>
                        <Form.Label>Start Date & Time </Form.Label>
                        <Row>
                        <input type='datetime-local' id='startDateInput' defaultValue=''                        
                         name = "startDate"
                         onChange = {changeValue}/>
                         </Row>
                    </Col>
                    <Col>
                        <Row>
                        <Form.Label>End Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput' defaultValue=''
                            name = "endDate"
                            onChange = {changeValue}
                        />
                        </Row>
                    </Col>
                </Row>

                <Row>
                  <Form.Label>Explain why this Change is Occurring</Form.Label>
                    <Form.Control 
                        type="title"
                        id='whyoccurring'
                        name = "whyOccurring"
                        onChange = {reaminingCharactersWhyOccurringFunction}
                        maxLength = {200}
                    />

                    <Form.Text>
                        200 character limit. {reaminingCharactersWhyOccurring} characters remaining.
                    </Form.Text>

                </Row>
                  <Form.Label>Backout Plan - What it takes to revert the</Form.Label>
                    <Form.Control 
                        type="title"
                        id='backoutplan'
                        name = "backoutPlan"
                        onChange = {reaminingCharactersBackoutPlanFunction}
                        maxLength = {200}
                    />

                    <Form.Text>
                        200 character limit. {reaminingCharactersBackoutPlan} characters remaining.
                    </Form.Text>
                <Row>
                    <Col>
                    <>
                    <Form.Label>Backout Minutes - How long it will take to revert the change</Form.Label>
                    <Form.Control
                        type="number"
                        id='backoutminutes'
                        name = "backoutMinutes"
                        onChange = {changeValue}
                    />
                    </>
                    </Col>

                    <Col>
                        <Form.Label>
                            Risk Assessment
                        </Form.Label>
                        <Form.Select defaultValue='Select Risk Assessment'
                            name = "riskAssessment"
                            onChange = {changeValue}
                        >
                            <option>Low</option>
                            <option>Medium</option>
                            <option>High</option>
                        </Form.Select>
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
