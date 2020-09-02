package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SessionDestroyedListener
{
    @EventListener
    public void onSessionDc(SessionDisconnectEvent event)
    {
        System.out.println("Session disconnected " + event);
    }
}
