package com.example.ChangeManage.Controller;

import com.example.ChangeManage.Service.CMUserService;
import com.example.ChangeManage.Service.ChangeRequestService;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CMUserController {

    private final CMUserService cmUserService;

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody CMUser cmuser){
        return new ResponseEntity<>(cmUserService.create(cmuser), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findAll(@PathVariable Integer id){
        return new ResponseEntity<>(cmUserService.findUser(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping("/user/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CMUser cmUser){
        return new ResponseEntity<>(cmUserService.update(id, cmUser), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        return new ResponseEntity<>(cmUserService.delete(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody CMUser cmUser){
        return new ResponseEntity<>(cmUserService.verifyLogin(cmUser.getUserId(), cmUser.getPassword()), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/logout")
    public ResponseEntity<?> logout(){
        return new ResponseEntity<>(cmUserService.logout(), HttpStatus.OK);
    }

}
