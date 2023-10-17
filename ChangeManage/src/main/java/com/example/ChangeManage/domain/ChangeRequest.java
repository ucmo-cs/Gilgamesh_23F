package com.example.ChangeManage.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String reasonType;

    @ManyToOne
    @JoinColumn(name = "id")
    private CMUser user;
}
