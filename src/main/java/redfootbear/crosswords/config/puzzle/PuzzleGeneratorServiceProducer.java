package redfootbear.crosswords.config.puzzle;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;
import redfootbear.crosswords.domain.puzzle.service.PuzzleGeneratorService;
import redfootbear.crosswords.domain.puzzle.service.PuzzleGeneratorServiceImpl;

@Dependent
public class PuzzleGeneratorServiceProducer {

    @Produces
    @DefaultBean
    public PuzzleGeneratorService puzzleGeneratorService(PuzzleCrosswordRepository puzzleCrosswordRepository) {
        return new PuzzleGeneratorServiceImpl(puzzleCrosswordRepository);
    }

}
