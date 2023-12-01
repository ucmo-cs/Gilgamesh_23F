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
    private String applicationId;
    private String description;
    private String reason;
    private String reasonNumber;

    private String changeType;
    private String whyOccurring;

    private String startDate;
    //private String startTime;
    private String endDate;
    //private String endTime;

    private String backoutPlan;
    private String backoutMinutes;

    private String riskAssessment;

    private String status; //Changed to Integer
    //0 open, 1 frozen, 2 department approval, 3 application approval, 4 approved, -1 denied

    private String otherDepartments; //Just added

    @ManyToOne
    @JoinColumn(name = "id")
    private CMUser user;
}
