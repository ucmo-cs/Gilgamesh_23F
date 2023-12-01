import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import { Form, Button, Dropdown, Container, Row, Col} from 'react-bootstrap';
import Header from '../components/Header'
 
function ChangeApproval(props) {


    const[changerequest, setChangeRequest] = useState({
        applicationId:'',
        changeType:'',
        description:'',
        startDate:'',
        endDate:'',
        reason:'',
        whyOccurring:'',
        backoutPlan:'',
        backoutMinutes:'',
        riskAssessment:'',
        otherDepartments:'',
        user:''
      });
    
      const id=props.match.params.id;

    
      useEffect(()=>{ 
        fetch("http://localhost:8080/request/" + id, {method:"GET"})
        .then(res =>res.json())
        .then(res=>{
            setChangeRequest(res)});
      },[])

      const approveChange = () =>{
        fetch("http://localhost:8080/request/approve/" + id, {method:"PATCH"})
        .then(res=>{console.log(res)
            if (res!==null) { props.history.push('/outstanding'); }
            else {alert('fails')};
        })
      }

      const denyChange = () => {
        fetch("http://localhost:8080/request/deny/" + id, {method:"PATCH"})
        .then(res=>{console.log(res)
            if (res!==null) { props.history.push('/outstanding'); }
            else {alert('fails')};
        })
    }


  return (
    <div>
      <Header/>
           <Form>
            <Container>
                <Row>
                    <Col>
                        <Form.Label>
                            Change Type
                        </Form.Label>
                        <Form.Select defaultValue='Select Change Type'
                            name = "changeType"
                            value = {changerequest.changeType}
                            disabled = {true}
                        >
                            <option>Select Change Type</option>
                            <option>Planned</option>
                            <option>Unplanned</option>
                            <option>Emergency</option>
                        </Form.Select>
                    </Col>

                    <Col>
                    <>
                    <Form.Label>Team</Form.Label>
                    <Form.Control
                        value = {changerequest.applicationId}
                        type="id"
                        id='teamId'
                        name = "applicationId"
                        disabled = {true}
                    />
                    </>
                    </Col>
                </Row>

                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                        type="text"
                        id='description'
                        name = "description"
                        value = {changerequest.description}
                        disabled = {true}
                    />

                
                <Row>
                  <Col>
                      <Form.Label>Reason</Form.Label>
                      <Form.Select
                            name = "reason"
                            value = {changerequest.reason}
                            disabled = {true}
                        >
                            <option>Pick a Reason</option>
                            <option>Fix</option>
                            <option>Enhancement</option>
                        </Form.Select>
                  </Col>


                    <Col>
                        <Form.Label>Start Date & Time </Form.Label>
                        <Row>
                        <input type='datetime-local' id='startDateInput'                        
                         name = "startDate"
                         disabled = {true}
                         value = {changerequest.startDate}
                         />
                         </Row>
                    </Col>
                    <Col>
                        <Row>
                        <Form.Label>End Date & Time </Form.Label>
                        <input type='datetime-local' id='startDateInput'
                            name = "endDate"
                            value = {changerequest.endDate}
                            disabled = {true}
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
                        value = {changerequest.whyOccurring}
                        disabled = {true}
                    />

                </Row>
                  <Form.Label>Backout Plan - What it takes to revert the change</Form.Label>
                    <Form.Control 
                        type="title"
                        id='backoutplan'
                        name = "backoutPlan"
                        value = {changerequest.backoutPlan}
                        disabled = {true}
                    />
                <Row>
                    <Col>
                    <>
                    <Form.Label>Backout Minutes - How long it will take to revert the change</Form.Label>
                    <Form.Control
                        type="number"
                        id='backoutminutes'
                        name = "backoutMinutes"
                        value = {changerequest.backoutMinutes}
                        disabled = {true}
                    />
                    </>
                    </Col>

                    <Col>
                        <Form.Label>
                            Risk Assessment
                        </Form.Label>
                        <Form.Select
                            name = "riskAssessment"
                            value = {changerequest.riskAssessment}
                            disabled = {true}
                        >
                            <option>Select Risk Assessment</option>
                            <option>Low</option>
                            <option>Medium</option>
                            <option>High</option>
                        </Form.Select>
                    </Col>

                </Row>

                <Row>
                  <Form.Label>
                    Other Departments Needed
                  </Form.Label>
                  <Form.Control
                    type="text"
                    id='otherDepartments'
                    name = "otherDepartments"
                    value = {changerequest.otherDepartments}
                    disabled = {true}
                  />
                </Row>
            </Container>
            <div>
                <button type="button" onClick={approveChange}>
                    Approve
                </button>
                
                <button type="button" onClick={denyChange}>
                    Deny
                </button>
            </div>

      </Form>
    </div>
  );
}

export default ChangeApproval;
