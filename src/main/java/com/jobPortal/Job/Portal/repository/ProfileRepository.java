package com.jobPortal.Job.Portal.repository;

import com.jobPortal.Job.Portal.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile , Long> {

}
