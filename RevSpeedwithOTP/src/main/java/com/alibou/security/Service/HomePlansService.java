package com.alibou.security.Service;

import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.user.HomePlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePlansService {

    @Autowired
    private HomePlansRepository homePlansRepository;

    public List<HomePlans> getHomePlans(){
        return homePlansRepository.findAll();
    }

    public HomePlans getPlanById(Long id) throws ResourceNotFoundException {
        return homePlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with Id :: " + id));
    }

    public HomePlans savePlan(HomePlans homePlans){
        return homePlansRepository.save(homePlans);
    }

    public HomePlans updateHomePlans(Long id, HomePlans homePlansDetails) throws ResourceNotFoundException {
        HomePlans homePlans = homePlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));
        homePlans.setBillingCycle(homePlansDetails.getBillingCycle());
        homePlans.setPlanName(homePlansDetails.getPlanName());
        homePlans.setPlanSpeed(homePlansDetails.getPlanSpeed());
        homePlans.setPlanPrice(homePlansDetails.getPlanPrice());
        homePlans.setPlanData(homePlansDetails.getPlanData());
        homePlans.setOtt_benefit_1(homePlansDetails.getOtt_benefit_1());
        homePlans.setOtt_benefit_2(homePlansDetails.getOtt_benefit_2());

        return homePlansRepository.save(homePlans);
    }

    public Map<String, Boolean> deletePlan(Long id) throws ResourceNotFoundException {
        HomePlans homePlans = homePlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));

        homePlansRepository.delete(homePlans);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}