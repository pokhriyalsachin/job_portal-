package com.jobPortal.Job.Portal.api;


import com.jobPortal.Job.Portal.jwt.AuthenticationRequest;
import com.jobPortal.Job.Portal.jwt.AuthenticationResponse;
import com.jobPortal.Job.Portal.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthApi {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public ResponseEntity<?>createAuthenticationToken(@RequestBody AuthenticationRequest request) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        final UserDetails userDetails= userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt= jwtHelper.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }




}
