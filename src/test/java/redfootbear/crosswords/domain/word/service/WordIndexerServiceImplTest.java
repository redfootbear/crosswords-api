package redfootbear.crosswords.domain.word.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.model.WordResolver;
import redfootbear.crosswords.domain.word.model.WordResolver.FakeWordNoIndex;

@ExtendWith(WordResolver.class)
class WordIndexerServiceImplTest {

    WordIndexerService wordIndexerService;

    @BeforeEach
    void beforeEach() {
        wordIndexerService = new WordIndexerServiceImpl();
    }

    @Test
    void givenValidWord_WhenBuildIndexesForIsCalled_ThenWordIndexesShouldNotBeNull(@FakeWordNoIndex Word word) {
        Assertions.assertNull(word.getIndexes());
        word = wordIndexerService.buildIndexedWord(word);
        Assertions.assertNotNull(word.getIndexes());
    }

    @Test
    void givenValidWord_WhenBuildIndexesForIsCalled_ThenWordIndexesShouldHaveSameLength(@FakeWordNoIndex Word word) {
        Assertions.assertNull(word.getIndexes());
        word = wordIndexerService.buildIndexedWord(word);
        Assertions.assertEquals(word.getLength(), word.getIndexes().size());
    }

    @Test
    void givenValidWord_WhenBuildIndexesForIsCalled_ThenWordIndexesShouldHaveSameCharactersInOrder(
            @FakeWordNoIndex Word word) {
        Assertions.assertNull(word.getIndexes());
        word = wordIndexerService.buildIndexedWord(word);
        for (int i = 0; i < word.getLength(); i++) {
            Assertions.assertEquals(word.getWord().charAt(i), word.getIndexes().get(i));
        }
    }

}