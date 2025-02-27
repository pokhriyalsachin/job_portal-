package com.jobPortal.Job.Portal.dto;

import com.jobPortal.Job.Portal.entity.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDTO {
    @Id
    private Long id;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Certification> certifications;

    public ProfileDTO(Long id, String email, String jobTitle, String company, String location, String about, List<String> Skills, List<Experience> experiences, List<Certification> certifications) {
        this.id = id;
        this.email = email;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.about = about;
        this.skills = Skills;
        this.experiences = experiences;
        this.certifications = certifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public Profile toEntity(){
        return new Profile( this.id,
                this.email,
                this.jobTitle,
                this.company,
                this.location,
                this.about,
                this.skills,
                this.experiences,
                this.certifications);
    }
}
