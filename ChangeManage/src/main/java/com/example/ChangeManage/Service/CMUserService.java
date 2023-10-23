package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CMUserService {

    private final CMUserRepository cmUserRepository;

    public CMUser create(CMUser user){
        return cmUserRepository.save(user);
    }



    public CMUser findUser(Integer id){
        return cmUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
    }

//    public List<ChangeRequest> findAll(){
//        return changeRequestRepository.findAll();
//    }

    public CMUser update(Integer id, CMUser user){
        CMUser cmUserEntity = cmUserRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid Id"));  //Persistence Context

        cmUserEntity.setAuthorizationLevel(user.getAuthorizationLevel());
        return cmUserEntity;
    } //I am not sure if we will need to update change requests but if we do this is a working proof of concept

    public String delete(Integer id){
        cmUserRepository.deleteById(id);
        return "ok";
    }

}
