package com.jobPortal.Job.Portal.repository;

import com.jobPortal.Job.Portal.dto.JobDTO;
import com.jobPortal.Job.Portal.entity.Job;
import jakarta.validation.Valid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, Long> {
    public List<Job>findByPostedBy(Long PostedBy);
}
