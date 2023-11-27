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
    private Integer reasonNumber;

    private String changeType;
    private String whyOccurring;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private String backoutPlan;
    private Integer backoutMinutes;

    private String riskAssessment;

    private Integer status; //Changed to Integer
    //0 open, 1 frozen, 2 department approval, 3 application approval, 4 approved, -1 denied

    private String otherDepartments; //Just added

    @ManyToOne
    @JoinColumn(name = "id")
    private CMUser user;
}
