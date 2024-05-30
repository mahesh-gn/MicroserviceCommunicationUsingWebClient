package com.example.synchronous_B.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailRequest {
    private List<String> to;
    private String subject;
    private String body;
    private List<String> attachments;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to=" + to +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}