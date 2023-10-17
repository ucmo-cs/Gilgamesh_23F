package com.example.ChangeManage.Service;

import com.example.ChangeManage.Repository.CMUserRepository;
import com.example.ChangeManage.domain.CMUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CMUserService {

    private final CMUserRepository cmUserRepository;

    public CMUser create(CMUser user){
        return cmUserRepository.save(user);
    }


}
