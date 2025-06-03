package com.example.notification_service.services;

import com.example.notification_service.entities.AppNotification;
import com.example.notification_service.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepo;




    public void markAsRead(Long id) {
        AppNotification notif = notificationRepo.findById(id).orElseThrow();
        notif.setNotificationRead(true);
        notificationRepo.save(notif);
    }
}
