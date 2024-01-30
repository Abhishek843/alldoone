package com.alibou.security.help;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Email {
    private String[] toMail;
    private String subject;
    private String message;
}
