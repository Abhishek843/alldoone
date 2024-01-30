package com.alibou.security.repository;

import com.alibou.security.entity.Users;

import java.util.List;
import java.util.Optional;

import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("FROM Users u WHERE u.otp = :otp")
    List<Users> findByOtp(String otp);


}