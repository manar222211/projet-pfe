package com.example.user_service;

import com.example.user_service.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;


@Component
public class UserInterceptor implements ChannelInterceptor {


    private final JwtUtils jwtUtils;


    @Autowired
    public UserInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                try {
                    String jwt = token.substring(7);
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);
                    accessor.setUser(new UserPrincipal(username));
                    System.out.println("🔐 WebSocket connected user: " + username);


                } catch (Exception e) {
                    System.err.println("Invalid JWT during WebSocket connect: " + e.getMessage());
                }
            }
        }
        return message;
    }
}

