package redfootbear.crosswords.domain.puzzle.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import redfootbear.crosswords.domain.puzzle.exception.CantBuildPuzzleGeneratorException;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.model.PuzzleDifficulty;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;

class PuzzleGeneratorServiceImplTest {

    private PuzzleGeneratorServiceImpl puzzleGeneratorService;

    @BeforeEach
    void beforeEach() {
        PuzzleCrosswordRepository puzzleCrosswordRepository = Mockito.mock(PuzzleCrosswordRepository.class);
        puzzleGeneratorService = new PuzzleGeneratorServiceImpl(puzzleCrosswordRepository);
    }

    @Test
    void givenValidParameters_WhenGeneratePuzzle_ThenAPuzzleShouldBeReturned() {
        Integer lines = 5;
        Integer columns = 10;
        PuzzleDifficulty puzzleDifficulty = PuzzleDifficulty.getRandom();
        Puzzle puzzle = puzzleGeneratorService.generatePuzzle(lines, columns, puzzleDifficulty);
        Assertions.assertNotNull(puzzle);
    }

    @Test
    @Disabled("TODO: Fix how to mock final class")
    void givenNullClassInPuzzleDifficulty_WhenGetPuzzleGeneratorInstance_ThenExceptionIsExpected() {
        PuzzleDifficulty puzzleDifficulty = Mockito.mock(PuzzleDifficulty.class);
        Mockito.when(puzzleDifficulty.getPuzzleGenerator()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(CantBuildPuzzleGeneratorException.class,
                () -> puzzleGeneratorService.getPuzzleGeneratorInstance(puzzleDifficulty));
    }

}