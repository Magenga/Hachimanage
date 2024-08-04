package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public void publish(ChatMessage message) {
        System.out.println("Publishing message: " + message);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}