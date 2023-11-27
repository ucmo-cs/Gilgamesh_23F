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
        return new ResponseEntity<>(changeRequestService.create(changeRequest), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/request/{changeId}")
    public ResponseEntity<?> findAll(@PathVariable Integer changeId){
        return new ResponseEntity<>(changeRequestService.findRequest(changeId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/request/userRequests")
    public ResponseEntity<?> findByUser(){
        return new ResponseEntity<>(changeRequestService.findUserRequests(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/request/canApprove")
    public ResponseEntity<?> canApprove(){
        return new ResponseEntity<>(changeRequestService.findApprovable(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/request/completed")
    public ResponseEntity<?> findCompleted(){
        return new ResponseEntity<>(changeRequestService.findCompleted(), HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping("/request/{changeId}")
    public ResponseEntity<?> updateStatus(@PathVariable Integer changeId, @RequestBody ChangeRequest changeRequest){
        return new ResponseEntity<>(changeRequestService.updateStatus(changeId, changeRequest), HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping("/request/approve/{changeId}")
    public ResponseEntity<?> approve(@PathVariable Integer changeId){
        return new ResponseEntity<>(changeRequestService.approve(changeId), HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping("/request/deny/{changeId}")
    public ResponseEntity<?> deny(@PathVariable Integer changeId){
        return new ResponseEntity<>(changeRequestService.deny(changeId), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/request/{changeId}")
    public ResponseEntity<?> deleteById(@PathVariable Integer changeId){
        return new ResponseEntity<>(changeRequestService.delete(changeId), HttpStatus.OK);
    }

}
