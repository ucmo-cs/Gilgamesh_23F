package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.Repository.ChangeRequestRepository;
import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CMUserService {

    private final CMUserRepository cmUserRepository;
    private final ChangeRequestRepository changeRequestRepository;

    public  static CMUser currentUser = null;


    public CMUser create(CMUser user){return cmUserRepository.save(user);}

    public CMUser findUser(Integer id){
        return cmUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
    }

    public CMUser update(Integer id, CMUser user){
        CMUser cmUserEntity = cmUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        cmUserEntity.setAuthorizationLevel(user.getAuthorizationLevel());
        cmUserEntity.setUserId(user.getUserId());
        cmUserEntity.setPassword(user.getPassword());
        cmUserRepository.save(cmUserEntity);
        return cmUserEntity;
    }

    public String delete(Integer id){
        CMUser user = cmUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        ChangeRequestService changeRequestService = new ChangeRequestService(changeRequestRepository, cmUserRepository);
        List<ChangeRequest> list = changeRequestService.findUserRequestsById(id); //needed the input
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setUser(null);
            changeRequestRepository.save(list.get(i));
        }
        cmUserRepository.deleteById(id);
        return "User Deleted";
    }

    public CMUser verifyLogin(String userId, String password) {
        CMUser user = cmUserRepository.findByUserId(userId);
        String usersPassword = user.getPassword();
        if (usersPassword.equals(password)) {
            currentUser = user;
            ChangeRequestService.currentUser = user;
            return user;
        } else
            throw new RuntimeException("Invalid Username or password");
    }

    //logout function
    public String logout(){
        currentUser = null;
        ChangeRequestService.currentUser = null;
        return "logged out";
    }

}
