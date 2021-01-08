package redfootbear.crosswords.domain.puzzle.generator;

import redfootbear.crosswords.domain.puzzle.model.Puzzle;

public interface PuzzleGenerator {

    PuzzleGenerator setLines(Integer lines);

    PuzzleGenerator setColumns(Integer columns);

    Puzzle build();

}
