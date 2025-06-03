package com.example.notification_service.repositories;

import com.example.notification_service.entities.AppNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<AppNotification,Long> {
    List<AppNotification> findByUsernameOrderByTimestampDesc(String username);

   // long countByUsernameAndIsReadFalse(String username);

}
