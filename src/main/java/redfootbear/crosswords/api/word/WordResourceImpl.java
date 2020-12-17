package redfootbear.crosswords.api.word;

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
import redfootbear.crosswords.domain.word.facade.WordFacade;
import redfootbear.crosswords.domain.word.model.Word;
import redfootbear.crosswords.domain.word.repository.exception.WordNotPersistedException;

@Path("words")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class WordResourceImpl {

    private final WordFacade wordFacade;

    @POST
    public Response addNewWord(@RequestBody NewWord newWord) {
        try {
            wordFacade.createNewWord(newWord);
            return Response.status(Response.Status.CREATED).build();
        } catch (WordNotPersistedException e) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{word}")
    public Response findWord(@PathParam("word") String word) {
        try {
            Word result = wordFacade.findByWord(word);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
