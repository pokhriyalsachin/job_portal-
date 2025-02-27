package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.*;
import com.jobPortal.Job.Portal.exception.JobPortalException;

import java.util.List;

public interface JobService {
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;
    public List<JobDTO> getAllJobs() throws JobPortalException;
    public JobDTO getJob(Long id) throws JobPortalException;
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException;
    public void changeAppStatus(Application application) throws JobPortalException;
}
