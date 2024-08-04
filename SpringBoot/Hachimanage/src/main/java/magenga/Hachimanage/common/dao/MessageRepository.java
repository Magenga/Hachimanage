package magenga.Hachimanage.common.dao;

import magenga.Hachimanage.common.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByProjectId(String projectId);
    List<ChatMessage> findByUserId(String userId);
    Optional<ChatMessage> findById(String id);
}
