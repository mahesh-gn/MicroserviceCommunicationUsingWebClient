package com.example.synchronous_B.repository;

import com.example.synchronous_B.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}