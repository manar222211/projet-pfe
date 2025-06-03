package com.example.leave_service.DTO;



import java.time.LocalDateTime;


public class NotificationPayload {
    private Long id;
    private String username;


    private String type; // "sent" or "received"
    private String message;
    private String redirectUrl;
    private boolean notificationRead;
    private LocalDateTime timestamp;




//    public NotificationPayload(String type, String message, String redirectUrl) {
//        this.type = type;
//        this.message = message;
//        this.redirectUrl = redirectUrl;
//    }


    public NotificationPayload() {
    }


    public NotificationPayload(String username, String type, String message, String redirectUrl) {
        this.username = username;
        this.type = type;
        this.message = message;
        this.redirectUrl = redirectUrl;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getRedirectUrl() {
        return redirectUrl;
    }


    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isNotificationRead() {
        return notificationRead;
    }


    public void setNotificationRead(boolean notificationRead) {
        this.notificationRead = notificationRead;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}



