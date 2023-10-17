package com.example.ChangeManage.Controller;

import com.example.ChangeManage.Service.ChangeRequestService;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChangeRequestController {

    private final ChangeRequestService changeRequestService;
    @PostMapping("/request")
    public ResponseEntity<?> save(@RequestBody ChangeRequest changeRequest){

        String userId = "testid";

        return new ResponseEntity<>(changeRequestService.create(changeRequest, userId), HttpStatus.CREATED);
    }
}
