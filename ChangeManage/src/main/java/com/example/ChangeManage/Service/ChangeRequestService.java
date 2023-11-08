package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.Repository.ChangeRequestRepository;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ChangeRequestService {

    private final ChangeRequestRepository changeRequestRepository;
    private final CMUserRepository cmUserRepository;
    public static CMUser currentUser = null;

    public ChangeRequest create(ChangeRequest changeRequest){
        changeRequest.setUser(currentUser);
        return changeRequestRepository.save(changeRequest);
    } //Currently front end is in charge of setting status

    public ChangeRequest findRequest(Integer changeId){
        return changeRequestRepository.findById(changeId).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
    }

    public List<ChangeRequest> findUserRequests(){
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser().getId() == currentUser.getId())
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    public List<ChangeRequest> findUserRequestsById(Integer id){
        CMUser user = cmUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser().getId() == user.getId())
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    //Currently the only thing to update is the application status
    public ChangeRequest updateStatus(Integer changeId, ChangeRequest newRequest){

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

    //Want to add find all requests you have access to approve
    public List<ChangeRequest> findApprovable(){
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        if (currentUser.getAuthorizationLevel() == 3) //Operations team
            for (int i = 0; i < allRequest.size(); i++) {
                if (allRequest.get(i).getUser().getId() != currentUser.getId())
                    userRequests.add(allRequest.get(i));
            }
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser().getId() != currentUser.getId() &&
                    currentUser.getAuthorizationLevel() == allRequest.get(i).getStatus())
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    //Want to add find all requests that have been completed
    public List<ChangeRequest> findCompleted(){
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getStatus() == 4)
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    //Approve and up status
    public String approve(Integer changeId){
        ChangeRequest changeRequest =
                changeRequestRepository.findById(changeId).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
        changeRequest.setStatus(changeRequest.getStatus() + 1);
        changeRequestRepository.save(changeRequest);
        return "ok";
    }

    //Deny and alter status
    public String deny(Integer changeId){
        ChangeRequest changeRequest =
                changeRequestRepository.findById(changeId).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
        changeRequest.setStatus(5);
        changeRequestRepository.save(changeRequest);
        return "ok";
    }

    /*
    public String fillData(){
        ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.setApplicationId(123);
        changeRequest.setDescription("This is a description");
        changeRequest.setStatus(4);
        changeRequest.setChangeType("Planned");
        changeRequest.setReason("Fix");
        changeRequest.setReasonNumber(1);
        changeRequest.setBackoutMinutes(120);
        changeRequest.setWhyOccurring("Something broke");
        changeRequest.setBackoutPlan("Revert changes");
        changeRequest.setStartTime("10:00");
        changeRequest.setStartDate("10/23/2023");
        changeRequest.setEndDate("10/23/2023");
        changeRequest.setEndTime("12:00");

        currentUser = cmUserRepository.findByUserId("user");
        create(changeRequest);

        changeRequest.setApplicationId(444);
        changeRequest.setStatus(1);
        changeRequest.setChangeType("Unplanned");
        changeRequest.setReasonNumber(2);
        changeRequest.setBackoutMinutes(50);
        create(changeRequest);

        changeRequest.setApplicationId(567);
        changeRequest.setStatus(2);
        changeRequest.setChangeType("Emergency");
        changeRequest.setReasonNumber(2);
        changeRequest.setBackoutMinutes(50);
        currentUser = cmUserRepository.findByUserId("app");
        create(changeRequest);

        changeRequest.setApplicationId(999);
        changeRequest.setStatus(5);
        changeRequest.setChangeType("Emergency");
        changeRequest.setReasonNumber(2);
        changeRequest.setBackoutMinutes(50);
        currentUser = cmUserRepository.findByUserId("ope");
        create(changeRequest);

        currentUser = null;

        return "ok";
    }
     */
}
