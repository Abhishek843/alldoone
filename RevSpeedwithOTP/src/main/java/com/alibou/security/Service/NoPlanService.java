package com.alibou.security.Service;

import com.alibou.security.repository.NoPlansRepository;
import com.alibou.security.user.HomePlans;
import com.alibou.security.user.NoPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class NoPlanService {
@Autowired
    private NoPlansRepository noPlansRepository;

    public NoPlan getPlanById(Long id) throws Exception {
        return noPlansRepository.findById(id)
                .orElseThrow(() -> new Exception("Plan not found with Id :: " + id));
    }

}
