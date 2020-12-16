package redfootbear.neo4j;

import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

@Neo4jEmbedded
public class Neo4JEmbeddedIT extends AbstractNeo4jEmbeddedIT {

    @Inject
    public Neo4JEmbeddedIT(Driver driver) {
        super(driver);
    }

    @Test
    void givenFakeData_WhenPersisted_ThenSuccess() {
        FakeModel fakeModel = FakeModel.builder().word("Test").build();
        Session session = driver.session();
        session.writeTransaction(tx -> tx.run("CREATE (fm:FakeModel {word: $word})",
                Values.parameters(FakeModel.PROPERTY_WORD, fakeModel.getWord())));
        List<Record> records = session.readTransaction(tx -> tx.run("MATCH (fm:FakeModel) RETURN fm").list());
        Assertions.assertEquals(1, records.size());
        FakeModel response = FakeModel.from(records.get(0).get("fm").asNode());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(fakeModel.getWord(), response.getWord());
    }

}
