package redfootbear.crosswords.domain.crossword.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.model.CrosswordResolver;
import redfootbear.crosswords.domain.crossword.model.CrosswordResolver.FakeCrosswordNoIndex;

@ExtendWith(CrosswordResolver.class)
class CrosswordIndexerServiceImplTest {

    CrosswordIndexerService crosswordIndexerService;

    @BeforeEach
    void beforeEach() {
        crosswordIndexerService = new CrosswordIndexerServiceImpl();
    }

    @Test
    void givenValidCrossword_WhenBuildIndexesForIsCalled_ThenCrosswordIndexesShouldNotBeNull(
            @FakeCrosswordNoIndex Crossword crossword) {
        Assertions.assertNull(crossword.getIndexes());
        crossword = crosswordIndexerService.buildIndexedCrossword(crossword);
        Assertions.assertNotNull(crossword.getIndexes());
    }

    @Test
    void givenValidCrossword_WhenBuildIndexesForIsCalled_ThenCrosswordIndexesShouldHaveSameLength(
            @FakeCrosswordNoIndex Crossword crossword) {
        Assertions.assertNull(crossword.getIndexes());
        crossword = crosswordIndexerService.buildIndexedCrossword(crossword);
        Assertions.assertEquals(crossword.getLength(), crossword.getIndexes().size());
    }

    @Test
    void givenValidCrossword_WhenBuildIndexesForIsCalled_ThenCrosswordIndexesShouldHaveSameCharactersInOrder(
            @FakeCrosswordNoIndex Crossword crossword) {
        Assertions.assertNull(crossword.getIndexes());
        crossword = crosswordIndexerService.buildIndexedCrossword(crossword);
        for (int i = 0; i < crossword.getLength(); i++) {
            Assertions.assertEquals(crossword.getWord().charAt(i), crossword.getIndexes().get(i));
        }
    }

}