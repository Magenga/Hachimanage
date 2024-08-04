package magenga.TimeLedger.controller;

import magenga.TimeLedger.common.service.ChatService;
import magenga.TimeLedger.common.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private  ChatService chatService;

    @MessageMapping("/chat.sendMessage/{projectId}")
    @SendTo("/topic/{projectId}")
    public ChatMessage sendMessage(@PathVariable String projectId, ChatMessage chatMessage) {
        logger.info("Received message: " + chatMessage.toString());
        chatService.saveMessage(chatMessage);
        chatMessage.setTimestamp(Instant.now());
        chatService.publishMessage(chatMessage);  // Publish the message
        return chatMessage;
    }

    @GetMapping("/allMessage")
    public List<ChatMessage> getAllMessage () {
        return chatService.getAllMessage();
    }

    @GetMapping("/message/{userId}")
    public List<ChatMessage> getMessageById (@PathVariable String userId) {
        return chatService.getMessagesByUserId(userId);
    }

    @GetMapping("/message/{projectId}")
    public List<ChatMessage> getMessageByProjectId (@PathVariable String projectId) {
        return chatService.getMessagesByProjectId(projectId);
    }

//    @MessageMapping("/chat.addUser/{projectId}")
//    @SendTo("/topic/{projectId}")
//    public ChatMessage addUser(@PathVariable String projectId , ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        logger.info("User added: " + chatMessage.getUserAccount());
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getUserAccount());
//        chatMessage.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
//        chatService.publishMessage(chatMessage);  // Publish the message
//        return chatMessage;
//    }
}
