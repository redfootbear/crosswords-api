package redfootbear.crosswords.api.crossword;

import static org.mockito.ArgumentMatchers.any;

import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import redfootbear.crosswords.api.crossword.NewCrosswordResolver.FakeNewCrossword;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacade;

@ExtendWith(NewCrosswordResolver.class)
class CrosswordResourceImplTest {

    CrosswordFacade crosswordFacade;
    CrosswordResourceImpl crosswordResource;

    @BeforeEach
    void beforeEach() {
        crosswordFacade = Mockito.mock(CrosswordFacade.class);
        crosswordResource = new CrosswordResourceImpl(crosswordFacade);
    }

    @Test
    void givenAValidNewCrossword_WhenAddNewWordIsCalled_ThenCrosswordFacadeShouldBeCalled(
            @FakeNewCrossword NewCrosswordDTO newCrossword) {
        crosswordResource.postNewCrossword(newCrossword);
        Mockito.verify(crosswordFacade).createNewCrossword(any(NewCrosswordDTO.class));
    }

    @Test
    void givenAValidNewCrossword_WhenAddNewCrosswordIsCalled_ThenResponseIs201(
            @FakeNewCrossword NewCrosswordDTO newCrossword) {
        Response response = crosswordResource.postNewCrossword(newCrossword);
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

}