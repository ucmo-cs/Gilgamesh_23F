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
        //not sure if validation needs to be done here
        changeRequest.setUser(currentUser);
        if (changeRequest.getChangeType().equals("Emergency"))
            changeRequest.setStatus("Application");
        else
            changeRequest.setStatus("Frozen");
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
        CMUser user = cmUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser().getId() == user.getId())
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    public ChangeRequest updateStatus(Integer changeId, ChangeRequest newRequest){
        ChangeRequest changeRequestEntity = changeRequestRepository.findById(changeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        changeRequestEntity.setStatus(newRequest.getStatus());
        changeRequestRepository.save(changeRequestEntity); //Added this line of code
        return changeRequestEntity;
    }

    public String delete(Integer changeId){
        changeRequestRepository.deleteById(changeId);
        return "ok";
    }

    public List<ChangeRequest> findApprovable(){
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        if (currentUser.getAuthorizationLevel().equals("Operations")) { //Operations team
            for (int i = 0; i < allRequest.size(); i++) {
                if (allRequest.get(i).getUser().getId() != currentUser.getId())
                    userRequests.add(allRequest.get(i));
            }
            return userRequests;
        }
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getUser().getId() != currentUser.getId()) {
                if (currentUser.getAuthorizationLevel().equals("Department") && allRequest.get(i).getStatus().equals("Frozen"))
                    userRequests.add(allRequest.get(i));
                if (currentUser.getAuthorizationLevel().equals("Applications") && allRequest.get(i).getStatus().equals("Department"))
                    userRequests.add(allRequest.get(i));
            }
        }
        return userRequests;
    }

    //find all requests that have been completed
    public List<ChangeRequest> findCompleted(){
        List<ChangeRequest> allRequest = changeRequestRepository.findAll();
        List<ChangeRequest> userRequests = new ArrayList<>();
        for (int i = 0; i < allRequest.size(); i++) {
            if (allRequest.get(i).getStatus().equals("Approved"))
                userRequests.add(allRequest.get(i));
        }
        return userRequests;
    }

    //Approve and up status
    public String approve(Integer changeId){
        ChangeRequest changeRequest =
                changeRequestRepository.findById(changeId).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        if (changeRequest.getStatus().equals("Frozen"))
            changeRequest.setStatus("Department");
        else if (changeRequest.getStatus().equals("Department"))
            changeRequest.setStatus("Application");
        else if (changeRequest.getStatus().equals("Application"))
            changeRequest.setStatus("Approved");
        else
            return "Error approving request";
        changeRequestRepository.save(changeRequest);
        return "Request Approved";
    }

    //Deny and alter status
    public String deny(Integer changeId){
        ChangeRequest changeRequest =
                changeRequestRepository.findById(changeId).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        changeRequest.setStatus("Denied");
        changeRequestRepository.save(changeRequest);
        return "Request Denied";
    }

}
