package com.alibou.security.auth;

import com.alibou.security.Bazservice.planService;
import com.alibou.security.Service.BazBusinessPlanService;
import com.alibou.security.Service.HomePlanService;
import com.alibou.security.Service.NoPlanService;
import com.alibou.security.Service.UsersService;
import com.alibou.security.dto.LoginDto;
import com.alibou.security.dto.RegisterDto;
import com.alibou.security.help.Email;
import com.alibou.security.help.VerificationRequest;
import com.alibou.security.help.EmailServices;
import com.alibou.security.repository.UsersRepository;
import com.alibou.security.user.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

   @Autowired
    private EmailServices emailService;
   @Autowired
   private UserService uservice;
   @Autowired
   private UsersService usersService;
   @Autowired
    private planService planService;

   @Autowired
   BazBusinessPlanService bazBusinessPlanService;

   @Autowired
    HomePlanService homePlanService;
@Autowired
   NoPlanService noPlanService;

    @PostMapping("/newregister")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(usersService.register(registerDto), HttpStatus.OK);
    }
    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request

    ) {
        uservice.changePassword(request);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestBody VerificationRequest verificationRequest) {
        String email=verificationRequest.getEmail();
        String otp=verificationRequest.getOtp();
        return new ResponseEntity<>(usersService.verifyAccount(email, otp), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(usersService.regenerateOtp(email), HttpStatus.OK);
    }
    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(usersService.login(loginDto), HttpStatus.OK);
    }
    @PostMapping(value = "/sendemail")
//    @Timed(value="sendEmail.time",description="email sending")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        System.out.println(email);
        boolean result =this.emailService.sendEmail(email.getSubject(), email.getMessage(),email.getToMail());
        if(result) {
//            registry.counter("sendEmail.counter").increment();
            return ResponseEntity.ok("Email is sent successfully.");

        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not send");
        }
    }

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }



  @GetMapping("/udetails/{email}")
public ResponseEntity<User>  getUseredetailsById(@PathVariable(value = "email") String email,
                                @Valid   User userdetails) throws  Exception
  {
      final User udetails= uservice.getUserbyId(email);
      return ResponseEntity.ok(udetails);

  }


  @PutMapping("/upCustomer")
    public ResponseEntity<User> setCustomer(@RequestBody User userdetails) throws Exception
  {
    User updateuser=uservice.updateUser(userdetails);
      return ResponseEntity.ok().body(updateuser);

  }

    @GetMapping("/ubusinessplans/{id}")
    public ResponseEntity<BusinessPlans> getBusinessPlanById(@PathVariable(value = "id") Long id) throws Exception {
       BusinessPlans businessPlans=bazBusinessPlanService.getPlanById(id);
               return ResponseEntity.ok(businessPlans);
    }

    @GetMapping("/uhomeplans/{id}")
    public ResponseEntity<HomePlans> gethomePlanById(@PathVariable(value = "id") Long id) throws Exception {
       HomePlans homePlans= homePlanService.getPlanById(id);
        return ResponseEntity.ok(homePlans);
    }

    @GetMapping("/noplans/{id}")
    public ResponseEntity<NoPlan> getNoPlanById(@PathVariable(value = "id") Long id) throws Exception {
        NoPlan noPlan= noPlanService.getPlanById(id);
        return ResponseEntity.ok(noPlan);
    }

}
