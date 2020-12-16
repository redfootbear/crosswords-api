package redfootbear.crosswords.api.word;

import static org.mockito.ArgumentMatchers.any;

import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import redfootbear.crosswords.api.word.NewWordResolver.FakeNewWord;
import redfootbear.crosswords.domain.word.facade.WordFacade;

@ExtendWith(NewWordResolver.class)
class WordResourceImplTest {

    WordFacade wordFacade;
    WordResourceImpl categoryResource;

    @BeforeEach
    void beforeEach() {
        wordFacade = Mockito.mock(WordFacade.class);
        categoryResource = new WordResourceImpl(wordFacade);
    }

    @Test
    void givenAValidNewWord_WhenAddNewWordIsCalled_ThenWordFacadeShouldBeCalled(@FakeNewWord NewWord newWord) {
        categoryResource.addNewWord(newWord);
        Mockito.verify(wordFacade).createNewWord(any(NewWord.class));
    }

    @Test
    void givenAValidNewWord_WhenAddNewWordIsCalled_ThenResponseIs201(@FakeNewWord NewWord newWord) {
        Response response = categoryResource.addNewWord(newWord);
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

}