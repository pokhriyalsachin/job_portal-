package com.jobPortal.Job.Portal.entity;

import com.jobPortal.Job.Portal.dto.ApplicationStatus;
import com.jobPortal.Job.Portal.dto.ApplicantDTO;
import java.time.LocalDateTime;
import java.util.Base64;

public class Applicant {
    private Long applicantId;
    private String name;
    private String email;
    private String phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public Applicant(Long applicantId, String name, String email, String phone, String website, byte[] resume, String coverLetter, LocalDateTime timestamp, ApplicationStatus applicationStatus,LocalDateTime interviewTime) {
        this.applicantId = applicantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.timestamp = timestamp;
        this.applicationStatus = applicationStatus;
        this.interviewTime=interviewTime;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] Resume) {
        resume = Resume;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public ApplicantDTO toDTO(){
        return new ApplicantDTO(this.applicantId,
        this.name,
        this.email,
        this.phone,
        this.website,
                this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null,
        this.coverLetter,
        this.timestamp,
        this.applicationStatus,
                this.interviewTime);
    }

}
