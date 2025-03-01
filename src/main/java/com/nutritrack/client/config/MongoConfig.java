package com.nutritrack.client.config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.uri}")
    private String mongodbURI;

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(mongodbURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        // "database" should match the database name in your URI if not, provide it here.
        return new MongoTemplate(mongoClient(), database);
    }
}
