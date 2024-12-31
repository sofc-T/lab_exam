package com.hr.hr.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hr.hr.models.UserInfo;

@Repository(value = "userInfoRepository")
public interface UserInfoRepository extends JpaRepository<UserInfo, Serializable> {

    public UserInfo findByEmail(String email);

    public List<UserInfo> findAllByOrderById();

    public UserInfo findById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE userinfo SET active=false WHERE id=:id", nativeQuery = true)
    public void blockUser(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE userinfo SET active=true WHERE id=:id", nativeQuery = true)
    public void unBlockUser(int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM userinfo WHERE id=:id", nativeQuery = true)
    public void deleteUserById(int id);
}
