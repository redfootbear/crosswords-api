package redfootbear.crosswords.domain.word.repository;

import javax.inject.Inject;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.repository.exception.WordNotPersistedException;
import redfootbear.crosswords.infrastructure.neo4j.AbstractNeo4jRepository;

public class WordNeo4jRepository extends AbstractNeo4jRepository implements WordRepository {

    private static final String CONSTRAINT_WORD_IS_UNIQUE = "CREATE CONSTRAINT IF NOT EXISTS ON (w:Word) ASSERT w" +
            ".word IS UNIQUE";
    private static final String CONSTRAINT_LENGTH_IS_UNIQUE = "CREATE CONSTRAINT IF NOT EXISTS ON (l:Length) ASSERT l" +
            ".size IS UNIQUE";

    private static final String QUERY_MERGE_WORD_AND_LENGTH = "MERGE (l:Length {size: $length}) MERGE (w:Word {word: " +
            "$word})-[:HAS_LENGTH]->(l)";
    private static final String QUERY_MERGE_WORD_AND_INDEX = "MERGE (i:Index {character: $character, position: " +
            "$position}) MERGE (w:Word {word: $word}) MERGE (w)-[:HAS_INDEX]->(i)";

    private static final String PROPERTY_WORD = "word";
    private static final String PROPERTY_LENGTH = "length";
    private static final String PROPERTY_POSITION = "position";
    private static final String PROPERTY_CHARACTER = "character";

    @Inject
    public WordNeo4jRepository(Driver driver) {
        super(driver);
    }

    @Override
    public void save(Word word) {
        persistWord(word);
        saveIndexes(word);
    }

    void persistWord(Word word) {
        try {
            getSession().writeTransaction(tx -> tx.run(QUERY_MERGE_WORD_AND_LENGTH,
                    Values.parameters(PROPERTY_LENGTH, word.getLength(), PROPERTY_WORD, word.getWord())));
        } catch (Exception e) {
            throw new WordNotPersistedException();
        }
    }

    void saveIndexes(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            persistIndex(word.getWord(), word.getWord().charAt(i), i + 1);
        }
    }

    void persistIndex(String word, Character character, Integer position) {
        getSession().writeTransaction(tx -> tx.run(QUERY_MERGE_WORD_AND_INDEX,
                Values.parameters(PROPERTY_CHARACTER, character,
                        PROPERTY_POSITION, position,
                        PROPERTY_WORD, word)));
    }

    public void validateConstraints() {
        getSession().writeTransaction(tx -> tx.run(CONSTRAINT_WORD_IS_UNIQUE));
        getSession().writeTransaction(tx -> tx.run(CONSTRAINT_LENGTH_IS_UNIQUE));
    }

}
