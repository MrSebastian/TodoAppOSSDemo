package de.mrsebastian.todoappdemo.backend.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile(Profiles.PROFILE_MONGO)
@EnableMongoRepositories(
        basePackages = { "de.mrsebastian.todoappdemo.backend.person.dataaccess.document", "de.mrsebastian.todoappdemo.backend.task.dataaccess.document" }
)
public class MongoDBAppConfiguration {

    @Value("${app.mongodb.username}")
    public String username;

    @Value("${app.mongodb.password}")
    public String password;

    @Value("${app.mongodb.url}")
    public String url;

    @Value("${app.mongodb.databasename}")
    public String databasename;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(url);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .credential(mongoCredential())
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoCredential mongoCredential() {
        return MongoCredential.createCredential(username, databasename, password.toCharArray());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), databasename);
    }
}
