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


    public CMUser create(CMUser user){
        try {
            return cmUserRepository.save(user);
        } catch (Exception ex) {throw ex;}
    }

    public CMUser findUser(Integer id){
        try {
            return cmUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        } catch (Exception ex) {throw ex;}
    }

    public CMUser update(Integer id, CMUser user){
        try {
            CMUser cmUserEntity = cmUserRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
            cmUserEntity.setAuthorizationLevel(user.getAuthorizationLevel());
            cmUserEntity.setUserId(user.getUserId());
            cmUserEntity.setPassword(user.getPassword());
            cmUserRepository.save(cmUserEntity);
            return cmUserEntity;
        } catch (Exception ex) {throw ex;}
    }

    public String delete(Integer id){
        try {
            CMUser user = cmUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
            ChangeRequestService changeRequestService = new ChangeRequestService(changeRequestRepository, cmUserRepository);
            List<ChangeRequest> list = changeRequestService.findUserRequestsById(id); //needed the input
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setUser(null);
                changeRequestRepository.save(list.get(i));
            }
            cmUserRepository.deleteById(id);
            return "ok";
        } catch (Exception ex) {return "Error occurred when deleting user";}
    }

    public CMUser verifyLogin(String userId, String password) {
        try {
            CMUser user = cmUserRepository.findByUserId(userId);
            String usersPassword = user.getPassword();
            if (usersPassword.equals(password)) {
                currentUser = user;
                ChangeRequestService.currentUser = user;
                return user;
            } else
                throw new Exception("Invalid username or password");
        } catch (Exception ex) {return null;}
    }

    //logout function
    public String logout(){
        try {
            currentUser = null;
            ChangeRequestService.currentUser = null;
            return "logged out";
        } catch (Exception ex) {return "Error logging out";}
    }

}
