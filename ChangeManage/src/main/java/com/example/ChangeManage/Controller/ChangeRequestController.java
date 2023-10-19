package com.example.ChangeManage.Controller;

import com.example.ChangeManage.Service.ChangeRequestService;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ChangeRequestController {

    private final ChangeRequestService changeRequestService;

    @CrossOrigin
    @PostMapping("/request")
    public ResponseEntity<?> save(@RequestBody ChangeRequest changeRequest){

        String userId = "testid"; //This will be removed later, I believe the variable will be supplied from the site
        return new ResponseEntity<>(changeRequestService.create(changeRequest, userId), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/request/{changeId}")
    public ResponseEntity<?> findAll(@PathVariable Integer changeId){

        return new ResponseEntity<>(changeRequestService.findRequest(changeId), HttpStatus.OK);
    }


}
