package magenga.TimeLedger.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Document(collection = "messages")
public class ChatMessage implements Serializable {
    @Id
    private String id;
    @Indexed
    private String projectId;
    @Indexed
    private String userId;
    @Indexed
    private String userAccount;
    private String content;
    private String type;
    private Timestamp timestamp;

    // Default constructor
    public ChatMessage() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // Constructor with all fields
    public ChatMessage(String projectId, String userId, String userAccount, String content, String type) {
        this.userId = userId;
        this.id = UUID.randomUUID().toString();
        this.projectId = projectId;
        this.userAccount = userAccount;
        this.content = content;
        this.type = type;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roomId='" + projectId + '\'' +
                ", account='" + userAccount + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }


}
