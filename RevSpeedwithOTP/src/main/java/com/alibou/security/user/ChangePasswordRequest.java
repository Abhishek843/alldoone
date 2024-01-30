package com.alibou.security.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private String email;
    private String newPassword;
    private String confirmationPassword;
}