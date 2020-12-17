package redfootbear.crosswords.config.crossword;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.domain.crossword.repository.CrosswordNeo4JRepository;
import redfootbear.crosswords.domain.crossword.repository.CrosswordRepository;

@Dependent
public class CrosswordRepositoryProducer {

    @Produces
    @DefaultBean
    public CrosswordRepository crosswordRepository(Driver driver) {
        CrosswordNeo4JRepository crosswordNeo4jRepository = new CrosswordNeo4JRepository(driver);
        crosswordNeo4jRepository.validateConstraints();
        return crosswordNeo4jRepository;
    }

}
