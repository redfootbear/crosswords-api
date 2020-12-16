package redfootbear.crosswords.infrastructure.neo4j;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

public abstract class AbstractNeo4jRepository {

    private final Driver driver;

    protected AbstractNeo4jRepository(Driver driver) {
        this.driver = driver;
    }

    public Session getSession() {
        driver.verifyConnectivity();
        return driver.session();
    }

}
