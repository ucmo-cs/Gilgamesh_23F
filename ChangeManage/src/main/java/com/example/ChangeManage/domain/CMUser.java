package com.example.ChangeManage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CMUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String userId;
    private String password;
    private Integer authorizationLevel; //changed to Integer
    //0 user, 1 Department, 2 application, 3 operations

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ChangeRequest> changeRequests = new ArrayList<>();
}
