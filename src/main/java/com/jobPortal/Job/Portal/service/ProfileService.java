package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.ProfileDTO;
import com.jobPortal.Job.Portal.entity.Profile;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.utility.Utilities;

import java.util.List;

public interface ProfileService {

    public Long createProfile(String mail) throws JobPortalException;
    public ProfileDTO getProfile(Long id) throws JobPortalException;
    public ProfileDTO putProfile(ProfileDTO profileDTO) throws JobPortalException;
}
