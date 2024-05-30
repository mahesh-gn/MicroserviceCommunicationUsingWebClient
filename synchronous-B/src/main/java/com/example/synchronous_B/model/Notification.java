package com.example.synchronous_B.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("SynchronousNotification")
public class Notification {
    private String id;
    private Student message;
    private LocalDateTime dateTime;
}