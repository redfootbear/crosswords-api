package redfootbear.crosswords.domain.crossword.repository;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.model.CrosswordResolver;
import redfootbear.crosswords.domain.crossword.model.CrosswordResolver.FakeCrossword;
import redfootbear.crosswords.domain.crossword.repository.exception.CrosswordNotPersistedException;

@ExtendWith(CrosswordResolver.class)
class CrosswordNeo4JRepositoryTest {

    CrosswordNeo4JRepository crosswordNeo4JRepository;
    Session session;

    @BeforeEach
    void beforeEach() {
        Driver driver = Mockito.mock(Driver.class);
        session = Mockito.mock(Session.class);
        crosswordNeo4JRepository = Mockito.spy(new CrosswordNeo4JRepository(driver));
        Mockito.when(crosswordNeo4JRepository.getSession()).thenReturn(session);
        Mockito.when(session.writeTransaction(any())).thenReturn(Mockito.mock(Result.class));
    }

    @Test
    void givenValidCrossword_WhenSaveIsCalled_ThenPersistIsCalled(@FakeCrossword Crossword crossword) {
        crosswordNeo4JRepository.save(crossword);
        Mockito.verify(crosswordNeo4JRepository).persistCrossword(any());
    }

    @Test
    void givenValidCrossword_WhenSaveIsCalled_ThenSaveIndexesIsCalled(@FakeCrossword Crossword crossword) {
        crosswordNeo4JRepository.save(crossword);
        Mockito.verify(crosswordNeo4JRepository).saveIndexes(any());
    }

    @Test
    void givenValidCrossword_WhenSaveIsCalled_ThenPersistIndexIsCalledForEachIndex(@FakeCrossword Crossword crossword) {
        crosswordNeo4JRepository.save(crossword);
        Mockito.verify(crosswordNeo4JRepository, Mockito.times(crossword.getIndexes().size())).persistIndex(any(),
                any(), any());
    }

    @Test
    void givenErrorInDatabase_WhenSaveIsCalled_ThenExceptionShouldBeThrow(@FakeCrossword Crossword crossword) {
        Mockito.when(session.writeTransaction(any())).thenThrow(new CrosswordNotPersistedException());
        Assertions.assertThrows(CrosswordNotPersistedException.class, () -> crosswordNeo4JRepository.save(crossword));
    }

}