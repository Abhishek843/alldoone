package com.alibou.security.Bazservice;

import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.user.HomePlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class planService {
    @Autowired
   private HomePlansRepository homePlanServiceRepository;
public HomePlans sethplan(HomePlans homePlans)
{
    return homePlanServiceRepository.save(homePlans);
}

}
