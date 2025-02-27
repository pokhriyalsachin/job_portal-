package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.LoginDTO;
import com.jobPortal.Job.Portal.dto.ResponseDTO;
import com.jobPortal.Job.Portal.dto.UserDTO;
import com.jobPortal.Job.Portal.exception.JobPortalException;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

    public UserDTO getUserByEmail(String email) throws JobPortalException;

    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

    public Boolean sendOtp(String email) throws Exception;

    public Boolean verifyOtp(String email,String otp) throws JobPortalException;

    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;
}
