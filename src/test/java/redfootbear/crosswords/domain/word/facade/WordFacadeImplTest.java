package redfootbear.crosswords.domain.word.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.api.word.NewWordResolver;
import redfootbear.crosswords.api.word.NewWordResolver.FakeNewWord;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.repository.WordRepository;
import redfootbear.crosswords.domain.word.service.WordIndexerService;


@ExtendWith(NewWordResolver.class)
class WordFacadeImplTest {

    WordFacadeImpl wordFacade;
    WordIndexerService wordIndexerService;
    WordRepository wordRepository;

    @BeforeEach
    void beforeEach() {
        wordIndexerService = mock(WordIndexerService.class);
        wordRepository = mock(WordRepository.class);
        wordFacade = new WordFacadeImpl(wordIndexerService, wordRepository);
    }

    @Test
    void givenValidNewWord_WhenCreateNewWordIsCalled_ThenWordIndexerServiceShouldBeCalledOneTime(
            @FakeNewWord NewWord newWord) {
        wordFacade.createNewWord(newWord);
        verify(wordIndexerService, times(1)).buildIndexedWord(any(Word.class));
    }

}