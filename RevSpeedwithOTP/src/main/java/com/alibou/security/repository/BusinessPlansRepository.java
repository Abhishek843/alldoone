package com.alibou.security.repository;


import com.alibou.security.user.BusinessPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface BusinessPlansRepository extends JpaRepository<BusinessPlans,Long>
    {}

