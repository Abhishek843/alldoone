package com.alibou.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "business_plans")
public class BusinessPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "billing_cycle")
    private String billingCycle;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_speed")
    private String planSpeed;

    @Column(name = "plan_price")
    private double planPrice;

    @Column(name = "plan_type")
    private String planType;
    @Column(name = "plan_data")
    private String planData;

    @Column(name = "ott_benefit_1")
    private String ott_benefit_1;

    @Column(name = "ott_benefit_2")
    private String ott_benefit_2;

//    @OneToMany(mappedBy = "businessPlan")
//    private List<User> UserList;

    // Other fields and methods

//    @ManyToOne
//    @JoinColumn(name = "business_plan_id")
//    private List<User> userList;

}
