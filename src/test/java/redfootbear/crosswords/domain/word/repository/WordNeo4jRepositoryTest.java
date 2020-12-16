package redfootbear.crosswords.domain.word.repository;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.model.WordResolver;
import redfootbear.crosswords.domain.word.model.WordResolver.FakeWord;
import redfootbear.crosswords.domain.word.repository.exception.WordNotPersistedException;

@ExtendWith(WordResolver.class)
class WordNeo4jRepositoryTest {

    WordNeo4jRepository wordNeo4jRepository;
    Session session;

    @BeforeEach
    void beforeEach() {
        Driver driver = Mockito.mock(Driver.class);
        session = Mockito.mock(Session.class);
        wordNeo4jRepository = Mockito.spy(new WordNeo4jRepository(driver));
        Mockito.when(wordNeo4jRepository.getSession()).thenReturn(session);
        Mockito.when(session.writeTransaction(any())).thenReturn(Mockito.mock(Result.class));
    }

    @Test
    void givenValidWord_WhenSaveIsCalled_ThenPersistIsCalled(@FakeWord Word word) {
        wordNeo4jRepository.save(word);
        Mockito.verify(wordNeo4jRepository).persistWord(any());
    }

    @Test
    void givenValidWord_WhenSaveIsCalled_ThenSaveIndexesIsCalled(@FakeWord Word word) {
        wordNeo4jRepository.save(word);
        Mockito.verify(wordNeo4jRepository).saveIndexes(any());
    }

    @Test
    void givenValidWord_WhenSaveIsCalled_ThenPersistIndexIsCalledForEachIndex(@FakeWord Word word) {
        wordNeo4jRepository.save(word);
        Mockito.verify(wordNeo4jRepository, Mockito.times(word.getIndexes().size())).persistIndex(any(), any(), any());
    }

    @Test
    void givenErrorInDatabase_WhenSaveIsCalled_ThenExceptionShouldBeThrow(@FakeWord Word word) {
        Mockito.when(session.writeTransaction(any())).thenThrow(new WordNotPersistedException());
        Assertions.assertThrows(WordNotPersistedException.class, () -> wordNeo4jRepository.save(word));
    }

}