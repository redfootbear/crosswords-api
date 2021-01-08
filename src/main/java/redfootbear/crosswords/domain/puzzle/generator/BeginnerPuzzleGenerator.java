package redfootbear.crosswords.domain.puzzle.generator;

import java.util.ArrayList;
import java.util.List;
import redfootbear.crosswords.domain.puzzle.model.PuzzleCrossword;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;

public class BeginnerPuzzleGenerator extends AbstractPuzzleGenerator {

    public BeginnerPuzzleGenerator(PuzzleCrosswordRepository puzzleCrosswordRepository) {
        super(puzzleCrosswordRepository);
    }

    @Override
    protected List<PuzzleCrossword> fetchCrosswords() {
        return new ArrayList<>();
    }

    @Override
    protected Character[][] buildGrid() {
        return new Character[0][];
    }

}
