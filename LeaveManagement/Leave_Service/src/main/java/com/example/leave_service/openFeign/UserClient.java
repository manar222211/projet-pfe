package com.example.leave_service.openFeign;

import com.example.leave_service.DTO.NotificationPayload;
import com.example.leave_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="UserService")
public interface UserClient {
    @GetMapping("/User/details/{id}")
    public UserDTO showdetails(@PathVariable Long id);

    @PutMapping("/User/updateNbTotal/{iduser}")
    public  UserDTO updatenbtotal(@PathVariable Long iduser, @RequestParam int newNB);

    @PostMapping("/User/send")
    public ResponseEntity<NotificationPayload> sendNotification(@RequestBody NotificationPayload payload,
                                                                @RequestHeader("Authorization") String token);


}
