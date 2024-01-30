package com.alibou.security.repository;

import com.alibou.security.user.HomePlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomePlansRepository extends JpaRepository<HomePlans,Long> {
}
