package magenga.TimeLedger.common.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import magenga.TimeLedger.common.entity.ChatMessage;

import java.util.ArrayList;

public class MessageService {

    public void createCollectionByProjectId (ChatMessage message) {
        String uri = "mongodb+srv://root:21airr01@mycluster.wk4zgas.mongodb.net/?retryWrites=true&w=majority&appName=MyCluster";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Get database, create if not exist
            MongoDatabase database = mongoClient.getDatabase("Message");

            // Create collection options (optional)
            CreateCollectionOptions options = new CreateCollectionOptions()
                    .capped(false); // Set capped to true for capped collections

            // Create collection if it does not exist
            boolean collectionExists = database.listCollectionNames()
                    .into(new ArrayList<>())
                    .contains("Project" + message.getProjectId());

            if (!collectionExists) {
                database.createCollection("Project" + message.getProjectId(), options);
                System.out.println("Collection 'Project" + message.getProjectId() + "' created successfully");
            } else {
                System.out.println("Collection 'Project" + message.getProjectId() + " already exists");
            }
        }
    }

    public void saveMessage (ChatMessage message) {

    }

}
