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
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import redfootbear.crosswords.domain.crossword.facade.CrosswordFacade;
import redfootbear.crosswords.domain.crossword.model.Crossword;
import redfootbear.crosswords.domain.crossword.repository.exception.CrosswordNotPersistedException;

@Path("crosswords")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CrosswordResourceImpl {

    private final CrosswordFacade crosswordFacade;

    @POST
    public Response postNewCrossword(@RequestBody NewCrossword newCrossword) {
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
    @Path("{word}")
    public Response getCrosswordByWord(@PathParam("word") String word) {
        try {
            Crossword result = crosswordFacade.findByWord(word.toUpperCase());
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
