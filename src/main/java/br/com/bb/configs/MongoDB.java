package br.com.bb.configs;

import br.com.bb.entitys.GenericEntity;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Objects;

/**
 * MongoDB providing the database connection for main.
 */

@Log
@ApplicationScoped
public class MongoDB {

    @ConfigProperty(name = "mongo.host", defaultValue = "localhost")
    String DB_HOST;
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int DB_PORT;
    @ConfigProperty(name = "mongo.db", defaultValue = "morphia_demo")
    String DB_NAME;

    private Datastore datastore;

    private MongoClient mongoClient;

    @PostConstruct
    void buildMongoClient() {
        log.info("Building MongoClientFactory");
        connectMongoDB();
    }

    @Produces
    public MongoClient produceMongoClient() {
        return mongoClient;
    }

    @Produces
    public Datastore produceDataStore() {
        return datastore;
    }

    @PreDestroy
    void cleanup() {
        if (Objects.nonNull(mongoClient)) {
            mongoClient.close();
        }
    }

    private void connectMongoDB() {
        if (Objects.isNull(mongoClient)) {
            MongoClientOptions mongoOptions = MongoClientOptions.builder()
                    .socketTimeout(60000) // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
                    .connectTimeout(15000) // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
                    .maxConnectionIdleTime(600000) // Keep idle connections for 10m, so we discard failed connections quickly
                    .readPreference(ReadPreference.primaryPreferred()) // Read from the primary, if not available use a secondary
                    .build();

            mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
            log.info("Connection to database '" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "' initialized");

            Morphia morphia = new Morphia();
            new ValidationExtension(morphia);
            morphia.mapPackage(GenericEntity.class.getPackage().getName());

            datastore = morphia.createDatastore(mongoClient, DB_NAME);
            datastore.ensureIndexes();
            datastore.ensureCaps();
            log.info("Connection to database '" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "' initialized");

            new ValidationExtension(morphia);

        }

    }

}
