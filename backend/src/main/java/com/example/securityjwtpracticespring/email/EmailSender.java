package com.example.securityjwtpracticespring.email;

public interface EmailSender {
    void send(String to, String email);
}
