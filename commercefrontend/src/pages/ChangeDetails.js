import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
 
function ChangeDetails(props) {


    const[changerequest, setChangeRequest] = useState({
        applicationId:'',
        description:'',
        startTime:'',
        endDate:'',
        changeType:''
      });
    
      const id=props.match.params.id;

    
      useEffect(()=>{ 
        fetch("http://localhost:8080/request/" + id, {method:"GET"})
        .then(res =>res.json())
        .then(res=>{
            setChangeRequest(res)});
      },[])


  return (
    <div>
           {changerequest.applicationId} <br/>
           {changerequest.description} <br/>
           {changerequest.startTime} <br/>
           {changerequest.endDate} <br/> 
           {changerequest.changeType} <br/>
    </div>
  );
}

export default ChangeDetails;
