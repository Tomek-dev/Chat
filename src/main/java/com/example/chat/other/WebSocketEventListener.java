package com.example.chat.other;

import com.example.chat.other.dto.ChatMessage;
import com.example.chat.other.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private SimpMessageSendingOperations sendingOperations;

    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) accessor.getSessionAttributes().get("username");
        if(username != null){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(Type.LEAVE);
            chatMessage.setSender(username);
            sendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}
