package com.example;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MessageHandler
{
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendCurrentTime(String incomingMessage)
    {
        String reply = LocalDateTime.now().toString();
        System.out.println("Got message from client '" + incomingMessage + "' replying with: " + reply);
        return LocalDateTime.now().toString();
    }
}
