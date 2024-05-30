package com.example.synchronous_B.service.Implementation;

import com.example.synchronous_B.model.Notification;
import com.example.synchronous_B.model.Student;
import com.example.synchronous_B.repository.NotificationRepository;
import com.example.synchronous_B.service.Interface.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Student student) {
        Notification notification = new Notification();
        notification.setId(student.getId());
        notification.setMessage(student);
        notification.setDateTime(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}