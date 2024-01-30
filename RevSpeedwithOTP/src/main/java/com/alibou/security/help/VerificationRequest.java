package com.alibou.security.help;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class VerificationRequest {
    private String email;
    private String otp;
}
