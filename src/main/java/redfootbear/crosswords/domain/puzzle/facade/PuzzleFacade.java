package redfootbear.crosswords.domain.puzzle.facade;

import redfootbear.crosswords.api.puzzle.PuzzleRequestDTO;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;

public interface PuzzleFacade {

    Puzzle getPuzzle();

    Puzzle getPuzzle(PuzzleRequestDTO puzzleRequestDTO);

}
