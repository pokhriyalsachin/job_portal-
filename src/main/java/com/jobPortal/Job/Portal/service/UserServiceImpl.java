package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.LoginDTO;
import com.jobPortal.Job.Portal.dto.NotificationDTO;
import com.jobPortal.Job.Portal.dto.ResponseDTO;
import com.jobPortal.Job.Portal.dto.UserDTO;
import com.jobPortal.Job.Portal.entity.OTP;
import com.jobPortal.Job.Portal.entity.User;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.repository.NotificationRepository;
import com.jobPortal.Job.Portal.repository.OTPRepository;
import com.jobPortal.Job.Portal.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jobPortal.Job.Portal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value="UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());//it may return data of type User -> optional means it may or may not
        if(optional.isPresent()) throw new JobPortalException("USER_FOUND");
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));//hashPassword through bcyptjs
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO getUserByEmail(String email) throws JobPortalException {
        return  userRepository.findByEmail(email).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND")).toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException{
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }

    @Override
    public Boolean sendOtp(String email) throws Exception{
        User user= userRepository.findByEmail(email).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper message= new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOtp = Utilities.generateOTP();
        OTP otp = new OTP(email,genOtp, LocalDateTime.now());
        otpRepository.save(otp);//it updates the otp at that email
        message.setText("Your Code is : "+genOtp,false);//true dia to html content vrna text h bss
        javaMailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {

        OTP otpEntity = otpRepository.findById(email).orElseThrow(()-> new JobPortalException("OTP_NOT_FOUND"));

        if(!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationDTO noti = new NotificationDTO();
        noti.setId(user.getId());
//        noti.setTimestamp(LocalDateTime.now());
        noti.setMessage("Password Reset SuccessFull");
        noti.setAction("Password Reset");
        notificationService.sendNotification(noti);
        return new ResponseDTO("Password Changed Successfully");
    }

    //fix 10 sec  bad chalega scheduled way mei
    @Scheduled(fixedRate = 10000)
    public void removeExpiredOtp(){
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);// abb se 5 minute phle ka samay
        List<OTP> expiredOtps= otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOtps.isEmpty()){
            otpRepository.deleteAll(expiredOtps);
        }
    }


}
