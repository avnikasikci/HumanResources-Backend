package com.example.humanresources.business.abstracts;


import com.example.humanresources.core.entities.User;

public interface EmailService {
    void sendVerifyEmail(User user, String code);
    public void sendSimpleMessage(     String to, String subject, String text);
    public void sendSimpleMessageV2(String receiver, String sender, String message, String filenameAndLocation);

}
