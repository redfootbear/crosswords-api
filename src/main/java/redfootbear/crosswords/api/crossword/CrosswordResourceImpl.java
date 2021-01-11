package redfootbear.crosswords.api.crossword;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import redfootbear.crosswords.domain.crossword.exception.CrosswordNotPersistedException;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacade;
import redfootbear.crosswords.domain.crossword.model.Crossword;

@Path("crosswords")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CrosswordResourceImpl {

    private final CrosswordFacade crosswordFacade;

    @POST
    public Response postNewCrossword(NewCrosswordDTO newCrossword) {
        try {
            Crossword crossword = crosswordFacade.createNewCrossword(newCrossword);
            return Response.status(Response.Status.CREATED).entity(crossword).build();
        } catch (CrosswordNotPersistedException e) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{crossword}")
    public Response getCrosswordByWord(@PathParam("crossword") String crossword) {
        try {
            Crossword result = crosswordFacade.findByWord(crossword.toUpperCase());
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
