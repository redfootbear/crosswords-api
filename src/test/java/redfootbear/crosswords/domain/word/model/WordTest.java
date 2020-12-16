package redfootbear.crosswords.domain.word.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.api.word.NewWordResolver;
import redfootbear.crosswords.api.word.NewWordResolver.FakeNewWord;

@ExtendWith(NewWordResolver.class)
class WordTest {

    @Test
    void givenValidNewWord_WhenFromNewWordIsCalled_ThenShouldReturnWordWithSameWordAndClue(
            @FakeNewWord NewWord newWord) {
        Word word = Word.from(newWord);
        Assertions.assertNotNull(word);
        Assertions.assertEquals(newWord.getWord(), word.getWord());
        Assertions.assertEquals(newWord.getClue(), word.getClue());
    }

}