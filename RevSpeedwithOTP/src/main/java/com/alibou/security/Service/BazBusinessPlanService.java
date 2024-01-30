package com.alibou.security.Service;

import com.alibou.security.repository.BusinessPlansRepository;
import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.user.BusinessPlans;
import com.alibou.security.user.HomePlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BazBusinessPlanService {

    @Autowired
    private BusinessPlansRepository businessPlansRepository;
    public BusinessPlans getPlanById(Long id) throws Exception {
        return businessPlansRepository.findById(id)
                .orElseThrow(() -> new Exception("Plan not found with Id :: " + id));
    }
}
