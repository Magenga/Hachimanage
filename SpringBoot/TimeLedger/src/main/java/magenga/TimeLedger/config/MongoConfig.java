package magenga.TimeLedger.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb+srv://root:21airr01@mycluster.wk4zgas.mongodb.net/?retryWrites=true&w=majority&appName=MyCluster";
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "Hachimanage");
    }

    @Bean
    public MongoTemplate createIndexes(MongoTemplate mongoTemplate) {
        IndexOperations indexOps = mongoTemplate.indexOps("messages");
        indexOps.ensureIndex(new Index().on("roomId", Sort.Direction.ASC));
        indexOps.ensureIndex(new Index().on("timestamp", Sort.Direction.ASC));
        return mongoTemplate;
    }
}
