package com.example.notification_service.Controllers;

import com.example.notification_service.dto.NotificationPayload;
import com.example.notification_service.entities.AppNotification;
import com.example.notification_service.repositories.NotificationRepository;
import com.example.notification_service.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/save")
//    public ResponseEntity<Void> saveNotification(@RequestBody NotificationPayload request) {
    public ResponseEntity<NotificationPayload> saveNotification(@RequestBody NotificationPayload request) {

        System.out.println("in save notif controller ");
        AppNotification notification = new AppNotification();

        notification.setUsername(request.getUsername());
        notification.setType(request.getType());
        notification.setMessage(request.getMessage());
        notification.setRedirectUrl(request.getRedirectUrl());
        notification.setNotificationRead(false);
        notification.setTimestamp(LocalDateTime.now());

        AppNotification saved = notificationRepo.save(notification);

        // Construct response payload with ID
        NotificationPayload payload = new NotificationPayload();
        payload.setId(saved.getId());
        payload.setUsername(saved.getUsername());
        payload.setType(saved.getType());
        payload.setMessage(saved.getMessage());
        payload.setRedirectUrl(saved.getRedirectUrl());
        payload.setTimestamp(saved.getTimestamp());
        payload.setNotificationRead(saved.isNotificationRead());

        return ResponseEntity.ok(payload);

//        notificationRepo.save(notification);
//        return ResponseEntity.ok().build();
    }

    // Optional: Endpoint to fetch all user notifications
    @GetMapping("/{username}")
    public ResponseEntity<List<AppNotification>> getUserNotifications(@PathVariable String username) {
        return ResponseEntity.ok(notificationRepo.findByUsernameOrderByTimestampDesc(username));
    }


    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

}
