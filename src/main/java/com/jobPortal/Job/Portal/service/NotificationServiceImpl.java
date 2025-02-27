package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.NotificationDTO;
import com.jobPortal.Job.Portal.dto.NotificationStatus;
import com.jobPortal.Job.Portal.entity.Notification;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.repository.NotificationRepository;
import com.jobPortal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public void sendNotification(NotificationDTO notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setNotificationStatus(NotificationStatus.UNREAD);
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationDto.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) throws JobPortalException{
        return notificationRepository.findByUserIdAndNotificationStatus(userId,NotificationStatus.UNREAD);
    }

    @Override
    public void readNotification(Long id) throws JobPortalException {
         Notification noti = notificationRepository.findById(id).orElseThrow(()-> new JobPortalException("No Notifications"));
         noti.setNotificationStatus(NotificationStatus.READ);
         notificationRepository.save(noti);
    }
}
