package com.alibou.security.auth;

import com.alibou.security.Bazservice.planService;
import com.alibou.security.Service.*;
import com.alibou.security.dto.LoginDto;
import com.alibou.security.dto.RegisterDto;
import com.alibou.security.exception.ResourceNotFoundException;
import com.alibou.security.help.Email;
import com.alibou.security.help.VerificationRequest;
import com.alibou.security.help.EmailServices;
import com.alibou.security.repository.HomePlansRepository;
import com.alibou.security.repository.UsersRepository;
import com.alibou.security.repository.URepository;
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
import java.util.List;
import java.util.Map;


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






  @PutMapping("/upCustomer")
    public ResponseEntity<User> setCustomer(@RequestBody User userdetails) throws Exception
  {
    User updateuser=uservice.updateUser(userdetails);
      return ResponseEntity.ok().body(updateuser);

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

    @Autowired
    private HomePlansService homePlansService;

    @GetMapping("/home-plans")

    public List<HomePlans> getHomePlans() {
        return homePlansService.getHomePlans();
    }

    @GetMapping("/home-plans/{id}")
    public ResponseEntity<HomePlans> getPlanById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        HomePlans homePlans = homePlansService.getPlanById(id);
        return ResponseEntity.ok().body(homePlans);
    }
    @PostMapping("/home-plans")
    public ResponseEntity<HomePlans> savePlan(@RequestBody HomePlans broadbandPlans){

        HomePlans savedPlan =  homePlansService.savePlan(broadbandPlans);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/home-plans/{id}")
    public ResponseEntity<HomePlans> updateBroadbandPlan(@PathVariable(value = "id") Long id,
                                                         @Valid @RequestBody HomePlans broadbandPlans) throws ResourceNotFoundException {
        HomePlans updatedPlan = homePlansService.updateHomePlans(id, broadbandPlans);
        return ResponseEntity.ok(updatedPlan);
    }


    @DeleteMapping("/home-plans/{id}")
    public Map<String,Boolean> deleteBroadbandPlanWithId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        return homePlansService.deletePlan(id);

    }

    @Autowired
    private BusinessPlansService businessPlansService;


    @GetMapping("/business-plans/{id}")
    public ResponseEntity<BusinessPlans> getBusinessPlanById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        BusinessPlans businessPlans = businessPlansService.getPlanById(id);
        return ResponseEntity.ok().body(businessPlans);
    }

    @PostMapping("/business-plans")
    public ResponseEntity<BusinessPlans> savePlan(@RequestBody BusinessPlans businessPlans){
        BusinessPlans savedPlan = businessPlansService.savePlan(businessPlans);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/business-plans/{id}")
    public ResponseEntity<BusinessPlans> updateBusinessPlan(@PathVariable(value = "id") Long id,
                                                            @Valid @RequestBody BusinessPlans businessPlans) throws ResourceNotFoundException {
        BusinessPlans updatedPlan = businessPlansService.updateBusinessPlans(id, businessPlans);
        return ResponseEntity.ok(updatedPlan);
    }


    @DeleteMapping("/business-plans/{id}")
    public Map<String,Boolean> deleteBusinessPlanWithId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        return businessPlansService.deletePlan(id);

    }

    @Autowired
    private UserService userService;
    @GetMapping("/udetails")
    public List<User>  getUseredetailsById() throws  Exception
    {
        List<User> udetails= userService.findAllUsers();
        return udetails;

    }

    @GetMapping("/udetails/{email}")
    public User  getUseredetailsByIdd(@PathVariable(value = "email") String email,
                                     User userdetails) throws  Exception
    {
        User udetails= uservice.getUserbyId(email);
        return udetails;

    }





    @Autowired
    private HomePlansRepository homePlansRepository;

    @Autowired
    private UService uService;

    @GetMapping("/business-plans")

    public List<BusinessPlans> getBusinessPlans() {
        return businessPlansService.getBusinessPlans();
    }



//    @Autowired
//    private UService uService;




    @GetMapping("/udetails/{email}")
    public ResponseEntity<User> getUseredetailsById(@PathVariable(value = "email") String email, @Valid User userdetails)throws Exception
    {
        final User udetails = uservice.getUserbyId(email);
        return  ResponseEntity.ok(udetails);
    }









//    @Autowired
//    private UService uService;








}
