package com.alibou.security.auth;

import com.alibou.security.user.Role;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private long home_plan_id;
    private long business_plan_id;
    private long no_plan_id;
}
