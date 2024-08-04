package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.entity.ChatMessage;
import magenga.Hachimanage.common.dao.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic, MessageRepository messageRepository) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
        this.messageRepository = messageRepository;
    }

    public ChatMessage saveMessage(ChatMessage message) {
        return messageRepository.save(message);
    }

    public List<ChatMessage> getMessagesByProjectId(String projectId) {
        return messageRepository.findByProjectId(projectId);
    }

    public List<ChatMessage> getMessagesByUserId(String userId) {
        return messageRepository.findByUserId(userId);
    }

    public void publishMessage(ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

    public List<ChatMessage> getAllMessage () {
        return messageRepository.findAll();
    }
}
