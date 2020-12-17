package redfootbear.crosswords.domain.crossword.repository;

import javax.inject.Inject;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Values;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.repository.exception.CrosswordNotPersistedException;
import redfootbear.crosswords.infrastructure.neo4j.AbstractNeo4jRepository;

public class CrosswordNeo4JRepository extends AbstractNeo4jRepository implements CrosswordRepository {

    private static final String CONSTRAINT_WORD_IS_UNIQUE = "CREATE CONSTRAINT IF NOT EXISTS ON (c:Crossword) ASSERT " +
            "c" +
            ".word IS UNIQUE";
    private static final String CONSTRAINT_LENGTH_IS_UNIQUE = "CREATE CONSTRAINT IF NOT EXISTS ON (l:Length) ASSERT l" +
            ".size IS UNIQUE";

    private static final String QUERY_MERGE_WORD_AND_LENGTH = "MERGE (l:Length {size: $length}) MERGE (c:Crossword " +
            "{word: " +
            "$word})-[:HAS_LENGTH]->(l)";
    private static final String QUERY_MERGE_WORD_AND_INDEX = "MERGE (i:Index {character: $character, position: " +
            "$position}) MERGE (c:Crossword {word: $word}) MERGE (c)-[:HAS_INDEX]->(i)";
    private static final String QUERY_MATCH_BY_LENGTH_AND_INDEX = "MATCH (i:Index)<-[:HAS_INDEX]-(c:Crossword)" +
            "-[:HAS_LENGTH]->(l:Length) WHERE l.size = $length AND i.position = $position AND i.character = " +
            "$character WITH c, rand() AS number RETURN c ORDER BY number LIMIT 1";
    private static final String QUERY_MATCH_BY_WORD = "MATCH (c:Crossword) WHERE c.word = $word RETURN c";

    private static final String PROPERTY_LENGTH = "length";
    private static final String PROPERTY_POSITION = "position";
    private static final String PROPERTY_CHARACTER = "character";

    @Inject
    public CrosswordNeo4JRepository(Driver driver) {
        super(driver);
    }

    @Override
    public void save(Crossword crossword) {
        persistCrossword(crossword);
        saveIndexes(crossword);
    }

    void persistCrossword(Crossword crossword) {
        try {
            getSession().writeTransaction(tx -> tx.run(QUERY_MERGE_WORD_AND_LENGTH,
                    Values.parameters(PROPERTY_LENGTH, crossword.getLength(), Crossword.PROPERTY_WORD,
                            crossword.getWord().toUpperCase())));
        } catch (Exception e) {
            throw new CrosswordNotPersistedException();
        }
    }

    void saveIndexes(Crossword crossword) {
        for (int i = 0; i < crossword.getLength(); i++) {
            persistIndex(crossword.getWord(), crossword.getWord().toUpperCase().charAt(i), i + 1);
        }
    }

    void persistIndex(String word, Character character, Integer position) {
        getSession().writeTransaction(tx -> tx.run(QUERY_MERGE_WORD_AND_INDEX,
                Values.parameters(PROPERTY_CHARACTER, character,
                        PROPERTY_POSITION, position,
                        Crossword.PROPERTY_WORD, word.toUpperCase())));
    }

    @Override
    public Crossword findByWord(String word) {
        Record result = getSession().readTransaction(tx -> tx.run(QUERY_MATCH_BY_WORD,
                Values.parameters(Crossword.PROPERTY_WORD, word.toUpperCase())).single());
        return Crossword.from(result.get("c").asNode());
    }

    @Override
    public Crossword findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position,
                                                                   Character character) {
        Record result = getSession().readTransaction(tx -> tx.run(QUERY_MATCH_BY_LENGTH_AND_INDEX,
                Values.parameters(PROPERTY_LENGTH, length,
                        PROPERTY_POSITION, position,
                        PROPERTY_CHARACTER, character)).single());
        return Crossword.from(result.get("c").asNode());
    }

    public void validateConstraints() {
        getSession().writeTransaction(tx -> tx.run(CONSTRAINT_WORD_IS_UNIQUE));
        getSession().writeTransaction(tx -> tx.run(CONSTRAINT_LENGTH_IS_UNIQUE));
    }

}
