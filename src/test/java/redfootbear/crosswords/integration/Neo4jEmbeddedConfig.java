package redfootbear.crosswords.integration;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import java.nio.file.Path;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import org.neo4j.configuration.connectors.BoltConnector;
import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;

@Startup
@Singleton
public class Neo4jEmbeddedConfig {

    static final String DATABASE_FOLDER = "database";

    void onStart(@Observes StartupEvent event) {
        DatabaseManagementService databaseManagementService =
                new DatabaseManagementServiceBuilder(Path.of(DATABASE_FOLDER))
                        .setConfig(BoltConnector.enabled, true)
                        .setConfig(BoltConnector.listen_address, new SocketAddress("localhost", 7688))
                        .build();
        registerShutdownHook(databaseManagementService);
    }

    private static void registerShutdownHook(final DatabaseManagementService managementService) {
        Runtime.getRuntime().addShutdownHook(new Thread(managementService::shutdown));
    }

}

