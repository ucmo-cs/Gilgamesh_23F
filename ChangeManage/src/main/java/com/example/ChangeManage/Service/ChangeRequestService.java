package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.Repository.ChangeRequestRepository;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ChangeRequestService {

    private final CMUserRepository cmUserRepository;
    private final ChangeRequestRepository changeRequestRepository;

    public ChangeRequest create(ChangeRequest changeRequest, String userId){

        CMUser cmUser = cmUserRepository.findByUserId(userId);
        changeRequest.setUser(cmUser);
        return changeRequestRepository.save(changeRequest);
    }

    public ChangeRequest findRequest(Integer changeId){
        return changeRequestRepository.findById(changeId).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
    }

    public ChangeRequest findApp(Integer appId){
        return changeRequestRepository.findByApplicationId(appId);
//        return changeRequestRepository.findAll();
    }

    public ChangeRequest update(Integer changeId, ChangeRequest newRequest){

        System.out.println("changeId " + changeId);
        System.out.println("newRequest " + newRequest.getApplicationId());
        ChangeRequest changeRequestEntity = changeRequestRepository.findById(changeId)
                .orElseThrow(()->new IllegalArgumentException("Invalid Id"));  //Persistence Context

        changeRequestEntity.setApplicationId(newRequest.getApplicationId());
        changeRequestRepository.save(changeRequestEntity); //Added this line of code
        return changeRequestEntity;
    } //I am not sure if we will need to update change requests but if we do this is a working proof of concept

    public String delete(Integer changeId){
        changeRequestRepository.deleteById(changeId);
        return "ok";
    }
}
