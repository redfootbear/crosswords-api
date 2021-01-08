package redfootbear.crosswords.api.puzzle;

import java.util.concurrent.atomic.AtomicReference;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import redfootbear.crosswords.domain.puzzle.facade.PuzzleFacade;

class PuzzleResourceImplTest {

    private PuzzleResourceImpl puzzleResource;

    @BeforeEach
    void beforeEach() {
        PuzzleFacade puzzleFacade = Mockito.mock(PuzzleFacade.class);
        puzzleResource = new PuzzleResourceImpl(puzzleFacade);
    }

    @Test
    void givenNoErrors_WhenGetRandomPuzzleIsCalled_ThenShouldReturn200() {
        AtomicReference<Response> response = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> response.set(puzzleResource.getRandomPuzzle()));
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.get().getStatus());
    }

}