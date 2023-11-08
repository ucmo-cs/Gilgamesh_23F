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

    //Added
    public  static CMUser currentUser = null;


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
        cmUserEntity.setUserId(user.getUserId());
        cmUserEntity.setPassword(user.getPassword());
        cmUserRepository.save(cmUserEntity);
        return cmUserEntity;
    }

    public String delete(Integer id){
        CMUser user = cmUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid ID"));
        ChangeRequestService changeRequestService = new ChangeRequestService(changeRequestRepository, cmUserRepository);
        List<ChangeRequest> list = changeRequestService.findUserRequestsById(id); //needed the input
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setUser(null);
            changeRequestRepository.save(list.get(i));
        }
        cmUserRepository.deleteById(id);
        return "ok";
    }

    public CMUser verifyLogin(String userId, String password) {
        CMUser user = cmUserRepository.findByUserId(userId);
        String usersPassword = user.getPassword();
        if (usersPassword.equals(password)) {
            currentUser = user;
            ChangeRequestService.currentUser = user;
            return user;
        }
        else
            return null;
    }

    //logout function
    public String logout(){
        currentUser = null;
        ChangeRequestService.currentUser = null;
        return "ok";
    }

    /*
    public String fillData() {
        CMUser cmUser = new CMUser();
        cmUser.setFirstName("John");
        cmUser.setLastName("Doe");
        cmUser.setUserId("user");
        cmUser.setPassword("password");
        cmUser.setAuthorizationLevel(0);
        create(cmUser);

        cmUser.setFirstName("Harry");
        cmUser.setLastName("Jones");
        cmUser.setUserId("dep");
        cmUser.setPassword("password");
        cmUser.setAuthorizationLevel(1);
        create(cmUser);

        cmUser.setFirstName("William");
        cmUser.setLastName("England");
        cmUser.setUserId("app");
        cmUser.setPassword("password");
        cmUser.setAuthorizationLevel(2);
        create(cmUser);

        cmUser.setFirstName("Garrett");
        cmUser.setLastName("Abel");
        cmUser.setUserId("ope");
        cmUser.setPassword("password");
        cmUser.setAuthorizationLevel(3);
        create(cmUser);

        return "ok";
    }
    */
}
