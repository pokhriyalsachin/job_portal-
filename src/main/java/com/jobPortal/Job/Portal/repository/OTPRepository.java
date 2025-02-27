package com.jobPortal.Job.Portal.repository;

import com.jobPortal.Job.Portal.dto.ProfileDTO;
import com.jobPortal.Job.Portal.entity.OTP;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OTPRepository extends MongoRepository<OTP,String> {
     List<OTP> findByCreationTimeBefore(LocalDateTime expiry);

}
