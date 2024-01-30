package com.alibou.security.Service;

import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.user.HomePlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePlanService {
    @Autowired
    private HomePlansRepository homePlansRepository;
    public HomePlans getPlanById(Long id) throws Exception {
        return homePlansRepository.findById(id)
                .orElseThrow(() -> new Exception("Plan not found with Id :: " + id));
    }
}
