package magenga.Hachimanage.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import magenga.Hachimanage.common.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public RedisMessageSubscriber(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(message.getBody(), ChatMessage.class);
            logger.info("Received Message: " + chatMessage.toString());
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }
    }
}
