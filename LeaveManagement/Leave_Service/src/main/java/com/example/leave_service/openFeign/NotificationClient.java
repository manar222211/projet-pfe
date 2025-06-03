package com.example.leave_service.openFeign;

import com.example.leave_service.DTO.NotificationPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;


@FeignClient(name = "notification-service", path = "/notifservice/api/notifications")
public interface NotificationClient {


    @PostMapping("/save")
    public ResponseEntity<NotificationPayload> saveNotification(@RequestBody NotificationPayload request);




    @GetMapping("/{username}")
    public ResponseEntity<List<?>> getUserNotifications(@PathVariable String username);






}




