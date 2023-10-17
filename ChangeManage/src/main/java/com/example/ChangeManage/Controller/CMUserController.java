package com.example.ChangeManage.Controller;

import com.example.ChangeManage.Service.CMUserService;
import com.example.ChangeManage.domain.CMUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CMUserController {

    private final CMUserService cmUserService;

    @PostMapping("/user")

    public ResponseEntity<?> save(@RequestBody CMUser cmuser){
        return new ResponseEntity<>(cmUserService.create(cmuser), HttpStatus.CREATED);
    }
}
