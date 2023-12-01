import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import Table from 'react-bootstrap/Table';



 
function ChangeRequestList(props) {

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


    const ChangeDetails=(id)=>{
        props.history.push('/Details/'+id)
    }



    return (
    <div>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Change Number</th>
              <th>Change Description</th>
              <th>Approval Status</th>
              <th>Change Type</th>
              <th>Change Start Time</th>
            </tr>
          </thead>
          <tbody>
            {changerequests.map((changerequest)=>(

                <tr onClick={() => ChangeDetails(changerequest.changeId)}>
                <td>{changerequest.changeId}</td>
                <td>{changerequest.description}</td>
                <td>{changerequest.status}</td>
                <td>{changerequest.changeType}</td>
                <td>{changerequest.startDate}</td>
                </tr>

            ))}

          </tbody>
        </Table>
    </div>
  );
}

export default ChangeRequestList;
