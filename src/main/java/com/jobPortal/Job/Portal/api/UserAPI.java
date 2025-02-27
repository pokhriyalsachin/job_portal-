package com.jobPortal.Job.Portal.api;


import com.jobPortal.Job.Portal.dto.LoginDTO;
import com.jobPortal.Job.Portal.dto.ResponseDTO;
import com.jobPortal.Job.Portal.dto.UserDTO;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.jobPortal.Job.Portal.service.UserService;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO>registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO= userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO>registerUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>( userService.loginUser(loginDTO) , HttpStatus.OK);
    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDTO>registerUser(@PathVariable String email) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>( new ResponseDTO("OTP sent Successfully") , HttpStatus.OK);
    }

    @PostMapping("/changePass")
    public ResponseEntity<ResponseDTO>changePassword(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException{

        return new ResponseEntity<>(userService.changePassword(loginDTO) , HttpStatus.OK);
    }

    @PostMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDTO>verifyOtp(@PathVariable String email , @PathVariable String otp) throws JobPortalException{
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("OTP has been Verified"), HttpStatus.OK);
    }
    //for expiring this OTP We can also use Reddis(open source caching platform hai) here jo ki apne app krdega

}
