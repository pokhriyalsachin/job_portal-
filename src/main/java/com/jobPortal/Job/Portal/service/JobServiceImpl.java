package com.jobPortal.Job.Portal.service;


import com.jobPortal.Job.Portal.dto.*;
import com.jobPortal.Job.Portal.entity.Applicant;
import com.jobPortal.Job.Portal.entity.Job;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.repository.JobRepository;
import com.jobPortal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        if(jobDTO.getId()==null){
        jobDTO.setId(Utilities.getNextSequence("jobs"));
        jobDTO.setPostTime(LocalDateTime.now());
            NotificationDTO noti= new NotificationDTO();
            noti.setAction("JobPosted SuccessFully");
            noti.setMessage("Job Posted SuccessFully for "+jobDTO.getJobTitle()+" at "+jobDTO.getCompany());
            noti.setUserId(jobDTO.getPostedBy());//is bnde ne post ki
            noti.setRoute("/posted-jobs/"+jobDTO.getId());
            try {
                notificationService.sendNotification(noti);
            }catch(JobPortalException e){
                e.printStackTrace();
            }
        }else{
            Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
            if(job.getJobStatus().equals(JobStatus.DRAFT) ||job.getJobStatus().equals(JobStatus.CLOSED)){
                jobDTO.setPostTime(LocalDateTime.now());
            }
        }
        return jobRepository.save(jobDTO.toEntity()).toDTO();
    }

    @Override
    public List<JobDTO> getAllJobs(){
        //stream is Async way of travelling on a list
        return jobRepository.findAll().stream().map((x)->x.toDTO()).toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalException {
        return jobRepository.findById(id).orElseThrow(()-> new JobPortalException("JOB_NOT_FOUND")).toDTO();
    }

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants= job.getApplicants();
        if(applicants.isEmpty()){
            applicants= new ArrayList<>();
        }
        if(!applicants.stream().filter((x)->x.getApplicantId() == applicantDTO.getApplicantId()).toList().isEmpty()){
            throw new JobPortalException("JOB_APPLIED_ALREADY");
        }
        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantDTO.toEntity());
        job.setApplicants(applicants);
        jobRepository.save(job);

    }

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException {
        return jobRepository.findByPostedBy(id).stream().map((x)->x.toDTO()).toList();//all jobs postedBy this id vala Banda
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        Job job = jobRepository.findById(application.getId()).orElseThrow(()-> new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants= job.getApplicants().stream().map((x)->{
            if(application.getApplicantId().equals(x.getApplicantId())){
                x.setApplicationStatus(application.getApplicationStatus());
                if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)){
                    //isme date and time bhi set krna goha varna accept reject m no need
                    x.setInterviewTime(application.getInterviewTime());
                    NotificationDTO noti= new NotificationDTO();
                    noti.setAction("Interview Scheduled");
                    noti.setMessage("Interview Scheduled for job id+ "+application.getId());
                    noti.setUserId(application.getApplicantId());
                    noti.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(noti);
                    }catch(JobPortalException e){
                        e.printStackTrace();
                    }
                }
            }
            return x;

        }).toList();

        job.setApplicants(applicants);
        jobRepository.save(job);
    }


}
