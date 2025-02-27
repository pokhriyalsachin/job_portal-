package com.jobPortal.Job.Portal.api;

import com.jobPortal.Job.Portal.dto.NotificationStatus;
import com.jobPortal.Job.Portal.dto.ResponseDTO;
import com.jobPortal.Job.Portal.entity.Notification;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import com.jobPortal.Job.Portal.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/notifications")
public class NotificationApi {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>>getNotification(@PathVariable Long userId) throws JobPortalException {
        return new ResponseEntity<>(notificationService.getUnreadNotifications(userId), HttpStatus.OK);
    }

    @GetMapping("/read/{userId}")
    public ResponseEntity<ResponseDTO>readNotification(@PathVariable Long userId) throws JobPortalException {
        notificationService.readNotification(userId);
        return new ResponseEntity<>(new ResponseDTO("Success"), HttpStatus.OK);
    }


}
