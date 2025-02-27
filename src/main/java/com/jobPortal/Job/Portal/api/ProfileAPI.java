package com.jobPortal.Job.Portal.api;

import com.jobPortal.Job.Portal.dto.ProfileDTO;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@CrossOrigin
@RequestMapping("/profiles")
public class ProfileAPI {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDTO>getProfile(@PathVariable Long id) throws JobPortalException{
        return new ResponseEntity<>(profileService.getProfile(id) , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO>getProfile(@RequestBody ProfileDTO profileDTO) throws JobPortalException{
        return new ResponseEntity<>(profileService.putProfile(profileDTO) , HttpStatus.OK);
    }



}
