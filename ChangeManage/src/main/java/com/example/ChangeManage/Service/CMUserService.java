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
        CMUser user = findUser(id);
        ChangeRequestService changeRequestService = new ChangeRequestService(cmUserRepository, changeRequestRepository);
        List<ChangeRequest> list = changeRequestService.findUserRequests(user.getId());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setUser(null);
            changeRequestRepository.save(list.get(i));
        }
        cmUserRepository.deleteById(id);
        return "ok";
    }
    //Function does not work if user owns a change request
    //Function not tested, may work or error

    //Next step want to be able to verify login
    public CMUser verifyLogin(String userId, String password) {
        CMUser user = cmUserRepository.findByUserId(userId);
        String usersPassword = user.getPassword();
        if (usersPassword.equals(password))
            return user;
        else
            return null;
    }
    //Working

}
