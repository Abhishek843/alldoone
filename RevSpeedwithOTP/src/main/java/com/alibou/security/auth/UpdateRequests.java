package com.alibou.security.auth;

import jakarta.persistence.Entity;
import lombok.*;


@Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequests {

    private  String email;
    private long home_plan_id;
    private long business_plan_id;
    private long no_plan_id;

}