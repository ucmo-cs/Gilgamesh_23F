import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
 
function ChangeDetails(props) {


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


  return (
    <div>
           {changerequest.applicationId} <br/>
           {changerequest.description} <br/>
           {changerequest.startDate} <br/>
           {changerequest.endDate} <br/> 
           {changerequest.changeType} <br/>
           {changerequest.reason} <br/>
           {changerequest.whyOccurring} <br/>
           {changerequest.backoutPlan} <br/>
           {changerequest.backoutMinutes} <br/> 
           {changerequest.riskAssessment} <br/>
           {changerequest.otherDepartments} <br/> 
           {changerequest.user.firstName} <br/>
           {changerequest.user.lastName} <br/>
    </div>
  );
}

export default ChangeDetails;
