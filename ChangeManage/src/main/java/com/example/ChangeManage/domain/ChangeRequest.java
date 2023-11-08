package com.example.ChangeManage.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer changeId;
    private Integer applicationId;
    private String description;
    private String reason;
    private Integer reasonNumber; //ticket/service request, reason may need to become own class for the generated value

    private String changeType; //Do i need to set status on backend using current status or changeType?
    private String whyOccurring;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private String backoutPlan;
    private Integer backoutMinutes;

    private String riskAssessment;

    private Integer status; //Changed to Integer
    //0 open, 1 frozen, 2 department approval, 3 application approval, 4 approved, 5 denied

    //private List<String> otherDepartments; //Not sure how i want to implement
    //Do we want to add the ability to archive requests for the frontend (what happens after denial)

    @ManyToOne
    @JoinColumn(name = "id")
    private CMUser user;
}
