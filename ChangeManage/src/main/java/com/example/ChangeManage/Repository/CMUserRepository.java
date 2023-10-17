package com.example.ChangeManage.Repository;

import com.example.ChangeManage.domain.CMUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMUserRepository extends JpaRepository<CMUser, Integer> {

    CMUser findByUserId(String userId);
}
