package redfootbear.crosswords.domain.puzzle.model;

import java.util.Random;
import lombok.Getter;
import redfootbear.crosswords.domain.puzzle.generator.BeginnerPuzzleGenerator;
import redfootbear.crosswords.domain.puzzle.generator.PuzzleGenerator;

@Getter
public enum PuzzleDifficulty {

    BEGINNER(BeginnerPuzzleGenerator.class);

    private final Class<? extends PuzzleGenerator> puzzleGenerator;

    PuzzleDifficulty(Class<? extends PuzzleGenerator> puzzleGenerator) {
        this.puzzleGenerator = puzzleGenerator;
    }

    public static PuzzleDifficulty getRandom() {
        return values()[new Random().nextInt(values().length)];
    }

}
