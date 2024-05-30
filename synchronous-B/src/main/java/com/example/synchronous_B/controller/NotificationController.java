package com.example.synchronous_B.controller;

import com.example.synchronous_B.model.Student;
import com.example.synchronous_B.service.Interface.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody Student notification) {
        notificationService.sendNotification(notification);
        System.out.println("Received Notification: " + notification.toString());
        System.out.println("Ending object "+LocalTime.now());
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody String message) {
        System.out.println("Received Notification: " + message);
        System.out.println("Ending message "+ LocalTime.now());
        return "Notification sent successfully";
    }
}