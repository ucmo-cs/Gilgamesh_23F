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

    private String changeType;
    private String whyOccurring;

    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    private String backoutPlan; //May create backout plan as its own class
    private String backoutMinutes;

    private String riskAssessment;

    //private List<String> otherDepartments; //This may have to converted to string before going to H2 but not sure

    @ManyToOne
    @JoinColumn(name = "id")
    private CMUser user;
}
