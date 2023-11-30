import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import Table from 'react-bootstrap/Table';



 
function OutstandingChanges() {

    const [changerequests, setChangeRequests] = useState([


    ]);
    
    
    
    const getChangeReqeusts = async() =>{
    
        const response = await fetch(
            "http://localhost:8080/request/userRequests"
        );
        const json = await response.json();
        setChangeRequests(json)
    
        console.log("data " + changerequests);
    }
    
    
    
    useEffect(()=>{
    
        getChangeReqeusts();
    
    })




    return (
    <div>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Change Number</th>
              <th>Change Description</th>
              <th>Approval Status</th>
              <th>Change Date</th>
              <th>Change Start Time</th>
              <th>View Change Request</th>
            </tr>
          </thead>
          <tbody>
            {changerequests.map((changerequest)=>(

                <tr>
                <td>{changerequest.changeId}</td>
                <td>{changerequest.description}</td>
                <td>{changerequest.status}</td>
                <td>{changerequest.startTime}</td>
                <td>{changerequest.startDate}</td>
                <td>{changerequest.changeId}</td>
                </tr>

            ))}

          </tbody>
        </Table>
    </div>
  );
}

export default OutstandingChanges;
