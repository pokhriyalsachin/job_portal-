package com.jobPortal.Job.Portal.repository;

import com.jobPortal.Job.Portal.dto.NotificationDTO;
import com.jobPortal.Job.Portal.dto.NotificationStatus;
import com.jobPortal.Job.Portal.entity.Notification;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,Long> {
    public List<Notification> findByUserIdAndNotificationStatus(Long userId, NotificationStatus status) throws JobPortalException;
}
