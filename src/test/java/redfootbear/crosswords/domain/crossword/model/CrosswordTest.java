package redfootbear.crosswords.domain.crossword.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.api.crossword.NewCrossword;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver.FakeNewCrossword;

@ExtendWith(NewCrosswordResolver.class)
class CrosswordTest {

    @Test
    void givenValidNewCrossword_WhenFromNewCrosswordIsCalled_ThenShouldReturnCrosswordWithSameWord(
            @FakeNewCrossword NewCrossword newCrossword) {
        Crossword crossword = Crossword.from(newCrossword);
        Assertions.assertNotNull(crossword);
        Assertions.assertEquals(newCrossword.getWord(), crossword.getWord());
    }

}