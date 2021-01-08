package redfootbear.crosswords.config.puzzle;

import io.quarkus.arc.DefaultBean;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepositoryImpl;

@Dependent
public class PuzzleCrosswordRepositoryProducer {

    @Produces
    @DefaultBean
    public PuzzleCrosswordRepository puzzleCrosswordRepository() {
        return new PuzzleCrosswordRepositoryImpl();
    }

}
