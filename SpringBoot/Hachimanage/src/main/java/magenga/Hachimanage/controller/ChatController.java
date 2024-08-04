package magenga.Hachimanage.controller;

import magenga.Hachimanage.common.service.ChatService;
import magenga.Hachimanage.common.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage/{projectId}")
    @SendTo("/topic/{projectId}")
    public ChatMessage sendMessage(@PathVariable String projectId, ChatMessage chatMessage) {
        logger.info("Received message: " + chatMessage.toString());
        chatService.saveMessage(chatMessage);
        chatMessage.setTimestamp(LocalDateTime.now());
        return chatMessage;
    }

    @GetMapping("/allMessage")
    public List<ChatMessage> getAllMessage() {
        return chatService.getAllMessage();
    }

    @GetMapping("/message/user/{userId}")
    public List<ChatMessage> getMessageById(@PathVariable String userId) {
        return chatService.getMessagesByUserId(userId);
    }

    @GetMapping("/message/project/{projectId}")
    public List<ChatMessage> getMessageByProjectId(@PathVariable String projectId) {
        return chatService.getMessagesByProjectId(projectId);
    }
}
