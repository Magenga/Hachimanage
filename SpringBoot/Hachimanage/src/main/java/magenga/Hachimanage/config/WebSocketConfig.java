package magenga.Hachimanage.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import magenga.Hachimanage.common.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtUtil jwtUtil;

    public WebSocketConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                List<String> authorization = accessor.getNativeHeader("Authorization");

                if (authorization != null && !authorization.isEmpty()) {
                    String token = authorization.get(0).replace("Bearer ", "");
                    try {
                        Claims claims = Jwts.parser()
                                .setSigningKey(jwtUtil.getKey())
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
                        String username = claims.getSubject();
                        accessor.setUser(() -> username);
                    } catch (Exception e) {
                        return null;
                    }
                } else {
                    return null;
                }
                return message;
            }
        });
    }
}


