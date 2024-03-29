package com.alibou.security.user;

import java.util.List;
import java.util.Optional;

import com.alibou.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Integer> {


  Optional<User> findByEmail(String email);


  @Query("FROM User u WHERE u.email = :email")
  User findByEmaill(String email);

  @Query("select u FROM User u")
  List<User> findAllUsers();


}
