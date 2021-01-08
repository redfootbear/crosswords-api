package redfootbear.crosswords.api.puzzle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import redfootbear.crosswords.domain.puzzle.facade.PuzzleFacade;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;

@Path("puzzles")
@Produces(MediaType.APPLICATION_JSON)
public class PuzzleResourceImpl {

    private final PuzzleFacade puzzleFacade;

    @Inject
    public PuzzleResourceImpl(PuzzleFacade puzzleFacade) {
        this.puzzleFacade = puzzleFacade;
    }

    @GET
    public Response getRandomPuzzle() {
        try {
            Puzzle puzzle = puzzleFacade.getPuzzle();
            return Response.ok(puzzle).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
