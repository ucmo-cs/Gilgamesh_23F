package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.Repository.ChangeRequestRepository;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    }

   //There has to be a better way to do this function, but it works
    public List<ChangeRequest> findUserRequests(Integer id){
        CMUser user = cmUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser() == user)
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    //Currently the only thing to update is the application status
    public ChangeRequest update(Integer changeId, ChangeRequest newRequest){

        System.out.println("changeId " + changeId);
        System.out.println("newRequest " + newRequest.getApplicationId());
        ChangeRequest changeRequestEntity = changeRequestRepository.findById(changeId)
                .orElseThrow(()->new IllegalArgumentException("Invalid Id"));  //Persistence Context

        changeRequestEntity.setStatus(newRequest.getStatus());
        changeRequestRepository.save(changeRequestEntity); //Added this line of code
        return changeRequestEntity;
    }

    public String delete(Integer changeId){
        changeRequestRepository.deleteById(changeId);
        return "ok";
    }
}
