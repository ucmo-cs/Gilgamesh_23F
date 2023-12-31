package com.example.ChangeManage.Repository;

import com.example.ChangeManage.domain.CMUser;
import com.example.ChangeManage.domain.ChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, Integer> {

    ChangeRequest findByApplicationId(String applicationId);
    ChangeRequest findByUser(CMUser cmUser);

}