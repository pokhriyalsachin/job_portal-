package com.jobPortal.Job.Portal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Certification {
    private String title;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;

    public Certification(String title, String issuer, LocalDateTime issueDate, String certificateId) {
        this.title = title;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.certificateId = certificateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }
}
