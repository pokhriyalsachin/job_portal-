package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.ProfileDTO;
import com.jobPortal.Job.Portal.entity.Profile;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.repository.ProfileRepository;
import com.jobPortal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Long createProfile(String mail) throws JobPortalException{
        Profile profile = new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(mail);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND")).toDTO();
    }

    @Override
    public ProfileDTO putProfile(ProfileDTO profileDTO) throws JobPortalException {
         profileRepository.findById(profileDTO.getId()).orElseThrow(()-> new JobPortalException("PROFILE_NOT_FOUND"));
         profileRepository.save(profileDTO.toEntity());
         return profileDTO;
    }



}
