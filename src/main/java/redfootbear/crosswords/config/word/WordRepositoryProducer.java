package redfootbear.crosswords.config.word;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import org.neo4j.driver.Driver;
import redfootbear.crosswords.domain.word.repository.WordNeo4jRepository;
import redfootbear.crosswords.domain.word.repository.WordRepository;

@Dependent
public class WordRepositoryProducer {

    @Produces
    @DefaultBean
    public WordRepository wordRepository(Driver driver) {
        WordNeo4jRepository wordNeo4jRepository = new WordNeo4jRepository(driver);
        wordNeo4jRepository.validateConstraints();
        return wordNeo4jRepository;
    }

}
