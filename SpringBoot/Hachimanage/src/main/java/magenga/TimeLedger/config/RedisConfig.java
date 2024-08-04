package magenga.TimeLedger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import magenga.TimeLedger.common.service.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.sql.Timestamp;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Custom ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Timestamp.class, new com.fasterxml.jackson.databind.ser.std.DateSerializer());
        module.addDeserializer(Timestamp.class, new com.fasterxml.jackson.databind.deser.std.DateDeserializers.TimestampDeserializer());
        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return redisTemplate;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("chat");
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter, ChannelTopic topic) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, topic);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }
}
