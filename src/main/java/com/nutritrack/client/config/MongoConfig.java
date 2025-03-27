package com.nutritrack.client.config;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

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
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MongoMappingContext mongoMappingContext) {
        return new MongoTemplate(databaseFactory, mappingMongoConverter(databaseFactory, mongoMappingContext));
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<?> converters = Arrays.asList(
            new OffsetDateTimeWriteConverter(),
            new OffsetDateTimeReadConverter()
        );
        return new MongoCustomConversions(converters);
    }
    
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory,
                                                       MongoMappingContext mongoMappingContext) {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(databaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setCustomConversions(customConversions());
        converter.afterPropertiesSet();
        return converter;
    }
}
