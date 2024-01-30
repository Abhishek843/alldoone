package com.alibou.security.repository;


import com.alibou.security.user.NoPlan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface NoPlansRepository extends JpaRepository<NoPlan,Long> {
}
