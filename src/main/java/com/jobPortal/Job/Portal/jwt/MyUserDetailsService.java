package com.jobPortal.Job.Portal.jwt;

import com.jobPortal.Job.Portal.dto.UserDTO;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       try{
           //email as a username use karenge hum (email , password)
           UserDTO dto= userService.getUserByEmail(email);
           return new CustomUserDetails(dto.getId(),dto.getEmail(),dto.getName(),dto.getPassword(),dto.getAccountType(),new ArrayList<>());
       }catch(JobPortalException e){
           e.printStackTrace();
       }
       return null;

    }
}
