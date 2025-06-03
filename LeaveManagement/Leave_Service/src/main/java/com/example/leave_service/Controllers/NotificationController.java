package com.example.leave_service.Controllers;


import com.example.leave_service.Entities.PrivateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class NotificationController {

    //server application
    ///app/sendMessage
    ///app/sendMessage
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/notifications")
//    public String sendMessage(String message){
//        System.out.println("message : "+message);
//        return message;
//    }


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendPrivateNotification(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", message);
    }



    @MessageMapping("/sendPrivateMessage")
    public void sendPrivateMessage(@Payload PrivateMessage privateMessage) {
        String userId = privateMessage.getUserId(); // the receiver
        String message = privateMessage.getMessage();

        System.out.println("Sending private message to user " + userId + ": " + message);

        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", message);
    }



}