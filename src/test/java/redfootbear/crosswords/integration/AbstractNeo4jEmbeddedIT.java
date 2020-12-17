package redfootbear.crosswords.integration;

import org.junit.jupiter.api.BeforeEach;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

public abstract class AbstractNeo4jEmbeddedIT {

    protected final Driver driver;

    public AbstractNeo4jEmbeddedIT(Driver driver) {
        this.driver = driver;
    }

    @BeforeEach
    public void beforeEach() {
        emptyDatabase();
    }

    void emptyDatabase() {
        Session session = driver.session();
        session.writeTransaction(tx -> tx.run("MATCH (all) DETACH DELETE all"));
    }

}
