package redfootbear.neo4j;

import java.nio.file.Path;
import org.junit.jupiter.api.extension.Extension;
import org.neo4j.configuration.connectors.BoltConnector;
import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;

public class Neo4jEmbeddedExtension implements Extension {

    static final String DATABASE_FOLDER = "database";

    public Neo4jEmbeddedExtension() {
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
