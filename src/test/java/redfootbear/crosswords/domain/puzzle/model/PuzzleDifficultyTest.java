package redfootbear.crosswords.domain.puzzle.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redfootbear.crosswords.domain.puzzle.generator.PuzzleGenerator;

class PuzzleDifficultyTest {

    private PuzzleDifficulty puzzleDifficulty;

    @BeforeEach
    void beforeEach() {
        puzzleDifficulty = PuzzleDifficulty.getRandom();
    }

    @Test
    void givenPuzzleDifficulty_WhenGetRandom_ThenGetARandomPuzzleDifficulty() {
        PuzzleDifficulty puzzleDifficulty = PuzzleDifficulty.getRandom();
        Assertions.assertNotNull(puzzleDifficulty);
    }

    @Test
    void givenPuzzleDifficulty_WhenExists_ThenGetAGeneratorClass() {
        Class<? extends PuzzleGenerator> puzzleGenerator = puzzleDifficulty.getPuzzleGenerator();
        Assertions.assertNotNull(puzzleGenerator);
    }

}