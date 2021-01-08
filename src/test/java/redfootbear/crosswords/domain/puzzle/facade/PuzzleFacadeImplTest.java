package redfootbear.crosswords.domain.puzzle.facade;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import redfootbear.crosswords.api.puzzle.PuzzleRequestDTO;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.service.PuzzleGeneratorService;

class PuzzleFacadeImplTest {

    private PuzzleGeneratorService puzzleGeneratorService;
    private PuzzleFacadeImpl puzzleFacade;

    @BeforeEach
    void beforeEach() {
        puzzleGeneratorService = Mockito.mock(PuzzleGeneratorService.class);
        puzzleFacade = new PuzzleFacadeImpl(puzzleGeneratorService);
    }

    @Test
    void givenPuzzleFacade_WhenGetRandomPuzzleRequestDTO_ThenShouldReturnShouldBeANotNullPuzzleRequestDTO() {
        PuzzleRequestDTO puzzleRequestDTO = puzzleFacade.getRandomPuzzleRequestDTO();
        Assertions.assertNotNull(puzzleRequestDTO);
    }

    @Test
    void givenPuzzleFacade_WhenGetRandomPuzzleRequestDTO_ThenPuzzleRequestDTOColumnsBiggerThanMinimumAndLessThanMaximum() {
        PuzzleRequestDTO puzzleRequestDTO = puzzleFacade.getRandomPuzzleRequestDTO();
        Assertions.assertTrue(puzzleRequestDTO.getColumns() >= PuzzleFacadeImpl.PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS,
                "PuzzleRequestDTO columns should have at least PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS");
        Integer maximumColumnsAmount =
                PuzzleFacadeImpl.PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS + PuzzleFacadeImpl.PUZZLE_MAXIMUM_RANDOM_AMOUNT;
        Assertions.assertTrue(puzzleRequestDTO.getColumns() <= maximumColumnsAmount,
                "PuzzleRequestDTO columns should have less than PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS + " +
                        "PUZZLE_MAXIMUM_RANDOM_AMOUNT");
    }

    @Test
    void givenPuzzleFacade_WhenGetRandomPuzzleRequestDTO_ThenPuzzleRequestDTOLinesBiggerThanMinimumAndLessThanMaximum() {
        PuzzleRequestDTO puzzleRequestDTO = puzzleFacade.getRandomPuzzleRequestDTO();
        Assertions.assertTrue(puzzleRequestDTO.getLines() >= PuzzleFacadeImpl.PUZZLE_MINIMUM_AMOUNT_OF_LINES,
                "PuzzleRequestDTO lines should have at least PUZZLE_MINIMUM_AMOUNT_OF_LINES");
        Integer maximumColumnsAmount =
                PuzzleFacadeImpl.PUZZLE_MINIMUM_AMOUNT_OF_LINES + PuzzleFacadeImpl.PUZZLE_MAXIMUM_RANDOM_AMOUNT;
        Assertions.assertTrue(puzzleRequestDTO.getColumns() <= maximumColumnsAmount,
                "PuzzleRequestDTO lines should have less than PUZZLE_MINIMUM_AMOUNT_OF_LINES + " +
                        "PUZZLE_MAXIMUM_RANDOM_AMOUNT");
    }

    @Test
    void givenPuzzleFacade_WhenGetRandomPuzzleRequestDTO_ThenPuzzleRequestDTOShouldHaveDifficulty() {
        PuzzleRequestDTO puzzleRequestDTO = puzzleFacade.getRandomPuzzleRequestDTO();
        Assertions.assertNotNull(puzzleRequestDTO.getPuzzleDifficulty());
    }

    @Test
    void givenPuzzleFacade_WhenGetPuzzle_ThenShouldReturnPuzzle() {
        Mockito.when(puzzleGeneratorService.generatePuzzle(any(), any(), any())).thenReturn(Puzzle.builder().build());
        Puzzle puzzle = puzzleFacade.getPuzzle();
        Assertions.assertNotNull(puzzle);
    }

}