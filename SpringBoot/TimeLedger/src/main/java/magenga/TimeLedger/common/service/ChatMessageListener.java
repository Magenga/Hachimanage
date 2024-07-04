package magenga.TimeLedger.common.service;
import magenga.TimeLedger.common.entity.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void receiveMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
