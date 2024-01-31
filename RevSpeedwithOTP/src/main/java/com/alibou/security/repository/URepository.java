
package com.alibou.security.repository;

import com.alibou.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface URepository extends JpaRepository<User , Integer> {


//    @Modifying
//    @Query("UPDATE User u SET u.homePlan.id = :planId WHERE u.email = :email")
//    int rechargeHomePlan(@Param("email") String email, @Param("planId") Integer planId);
//
//    @Modifying
//    @Query("UPDATE User u SET u.businessPlan.id = :planId WHERE u.email = :email")
//    int rechargeBusinessPlan(@Param("email") String email, @Param("planId") Integer planId);
//
//    @Modifying
//    @Query("UPDATE User u SET u.homePlan = null WHERE u.email = :email")
//    int unsubscribeHomePlan(@Param("email") String email);
//
//    @Modifying
//    @Query("UPDATE User u SET u.businessPlan = null WHERE u.email = :email")
//    int unsubscribeBusinessPlan(@Param("email") String email);

}