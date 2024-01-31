package com.alibou.security.user;

import com.alibou.security.auth.UpdateRequest;
import com.alibou.security.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
   @Autowired
    private final UserRepository repository;
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



    public User updateUser(User userdetails) throws Exception
    {
//        System.out.println(userdetails +""+uid);
        User user=repository.findByEmaill(userdetails.getEmail());

        System.out.println(user);
       user.setAddress(userdetails.getAddress());
        user.setFirstname(userdetails.getFirstname());
        user.setLastname(userdetails.getLastname());
        user.setPhone(userdetails.getPhone());
        user.setBusinessPlans(userdetails.getBusinessPlans());
        user.setHomePlans(userdetails.getHomePlans());
        user.setNoPlan(userdetails.getNoPlan());
        return repository.save(user);
    }

    public User getUserbyId(String user) throws  Exception
    {
User meuser=repository.findByEmaill(user);
return meuser;
    }



    public List<User> findAllUsers() throws  Exception
    {
        List<User> meuser= (List<User>) repository.findAllUsers();
        return meuser;
    }




}
