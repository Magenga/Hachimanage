package magenga.TimeLedger.controller;

import magenga.TimeLedger.common.service.ChatService;
import magenga.TimeLedger.common.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage/{projectId}")
    @SendTo("/topic/{projectId}")
    public ChatMessage sendMessage(@PathVariable String projectId, ChatMessage chatMessage) {
        logger.info("Received message: " + chatMessage.toString());
        chatMessage.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        chatService.publishMessage(chatMessage);  // Publish the message
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{projectId}")
    @SendTo("/topic/{projectId}")
    public ChatMessage addUser(@PathVariable String projectId , ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("User added: " + chatMessage.getUserAccount());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUserAccount());
        chatMessage.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        chatService.publishMessage(chatMessage);  // Publish the message
        return chatMessage;
    }
}
