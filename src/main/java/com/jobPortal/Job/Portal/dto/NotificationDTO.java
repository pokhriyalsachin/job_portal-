package com.jobPortal.Job.Portal.dto;

import com.jobPortal.Job.Portal.entity.Notification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus notificationStatus;
    private LocalDateTime timestamp;

    public NotificationDTO(Long id, Long userId, String message, String action, String route, NotificationStatus notificationStatus, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.action = action;
        this.route = route;
        this.notificationStatus = notificationStatus;
        this.timestamp = timestamp;
    }

    public NotificationDTO() {
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Notification toEntity(){
        return new Notification(this.id,
        this.userId,
        this.message,
        this.action,
        this.route,
        this.notificationStatus,
        this.timestamp);
    }
}
