package com.jobPortal.Job.Portal.service;

import com.jobPortal.Job.Portal.dto.NotificationDTO;
import com.jobPortal.Job.Portal.entity.Notification;
import com.jobPortal.Job.Portal.exception.JobPortalException;

import java.util.List;

public interface NotificationService {

    //send Notification
    public void sendNotification(NotificationDTO notificationDto) throws JobPortalException;

    public List<Notification> getUnreadNotifications(Long userId) throws JobPortalException;

    public void readNotification(Long id) throws JobPortalException;
}
