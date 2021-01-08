package redfootbear.crosswords.config.puzzle;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.puzzle.facade.PuzzleFacade;
import redfootbear.crosswords.domain.puzzle.facade.PuzzleFacadeImpl;
import redfootbear.crosswords.domain.puzzle.service.PuzzleGeneratorService;

@Dependent
public class PuzzleFacadeProducer {

    @Produces
    @DefaultBean
    public PuzzleFacade puzzleFacade(PuzzleGeneratorService puzzleGeneratorService) {
        return new PuzzleFacadeImpl(puzzleGeneratorService);
    }

}
