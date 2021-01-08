package redfootbear.crosswords.domain.puzzle.service;

import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.model.PuzzleDifficulty;

public interface PuzzleGeneratorService {

    Puzzle generatePuzzle(Integer lines, Integer columns, PuzzleDifficulty puzzleDifficulty);

}
