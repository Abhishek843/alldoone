package com.alibou.security.Service;

import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.repository.BusinessPlansRepository;
import com.alibou.security.user.BusinessPlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusinessPlansService {

    @Autowired
    BusinessPlansRepository businessPlansRepository;

    public List<BusinessPlans> getBusinessPlans(){
        return businessPlansRepository.findAll();
    }

    public BusinessPlans getPlanById(Long id) throws ResourceNotFoundException {
        return businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with Id :: " + id));
    }
    public BusinessPlans savePlan(BusinessPlans businessPlans){
        return businessPlansRepository.save(businessPlans);
    }

    public BusinessPlans updateBusinessPlans(Long id, BusinessPlans businessPlansDetails) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));
        businessPlans.setBillingCycle(businessPlansDetails.getBillingCycle());
        businessPlans.setPlanName(businessPlansDetails.getPlanName());
        businessPlans.setPlanSpeed(businessPlansDetails.getPlanSpeed());
        businessPlans.setPlanPrice(businessPlansDetails.getPlanPrice());
        businessPlans.setPlanData(businessPlansDetails.getPlanData());
        businessPlans.setOtt_benefit_1(businessPlansDetails.getOtt_benefit_1());
        businessPlans.setOtt_benefit_2(businessPlansDetails.getOtt_benefit_2());


        return businessPlansRepository.save(businessPlans);
    }

    public Map<String, Boolean> deletePlan(Long id) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found for this id :: " + id));

        businessPlansRepository.delete(businessPlans);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}