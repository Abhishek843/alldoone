package com.alibou.security.Service;

import com.alibou.security.dto.LoginDto;
import com.alibou.security.dto.RegisterDto;
import com.alibou.security.entity.Users;
import com.alibou.security.repository.UsersRepository;
import com.alibou.security.user.ChangePasswordRequest;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import com.alibou.security.util.EmailUtil;
import com.alibou.security.util.OtpUtil;
import jakarta.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UsersRepository userRepository;

    public String register(RegisterDto registerDto) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        Users user = new Users();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "User registration successful";
    }

    public String verifyAccount(String email, String otp) {
        List<Users> usersWithOtp = userRepository.findByOtp(otp);

        if (!usersWithOtp.isEmpty()) {
            // You can further refine the logic if needed (e.g., check the OTP generation time)
            return otp;
        }

        return "Invalid OTP";


    }

    public String regenerateOtp(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    public String login(LoginDto loginDto) {
        Users user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "Password is incorrect";
        } else if (!user.isActive()) {
            return "your account is not verified";
        }
        return "Login successful";
    }

    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository repository;
    public void changePassword(ChangePasswordRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // Check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // Update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Save the new password
        repository.save(user);
    }
    public  User getUserbyId(String user)throws Exception{
        User meuser = repository.findByEmaill(user);
        return meuser;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User updateUser(User userdetails) throws Exception
    {
//        System.out.println(userdetails +""+uid);
        User user=repository.findByEmaill(userdetails.getEmail());

        System.out.println(user);
        user.setEmail(userdetails.getEmail());
        user.setAddress(userdetails.getAddress());
        user.setFirstname(userdetails.getFirstname());
        user.setLastname(userdetails.getLastname());
        user.setPhone(userdetails.getPhone());
        user.setBusinessPlans(userdetails.getBusinessPlans());
        user.setHomePlans(userdetails.getHomePlans());
        user.setNoPlan(userdetails.getNoPlan());
        return repository.save(user);
    }



}