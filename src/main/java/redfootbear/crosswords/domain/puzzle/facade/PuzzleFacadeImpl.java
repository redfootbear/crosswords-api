package redfootbear.crosswords.domain.puzzle.facade;

import java.util.Random;
import javax.inject.Inject;
import redfootbear.crosswords.api.puzzle.PuzzleRequestDTO;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.model.PuzzleDifficulty;
import redfootbear.crosswords.domain.puzzle.service.PuzzleGeneratorService;

public class PuzzleFacadeImpl implements PuzzleFacade {

    static final Integer PUZZLE_MINIMUM_AMOUNT_OF_LINES = 5;
    static final Integer PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS = 5;
    static final Integer PUZZLE_MAXIMUM_RANDOM_AMOUNT = 10;

    final PuzzleGeneratorService puzzleGeneratorService;

    @Inject
    public PuzzleFacadeImpl(PuzzleGeneratorService puzzleGeneratorService) {
        this.puzzleGeneratorService = puzzleGeneratorService;
    }

    @Override
    public Puzzle getPuzzle() {
        return getPuzzle(getRandomPuzzleRequestDTO());
    }

    @Override
    public Puzzle getPuzzle(PuzzleRequestDTO request) {
        return puzzleGeneratorService.generatePuzzle(request.getLines(),
                request.getColumns(),
                request.getPuzzleDifficulty());
    }

    PuzzleRequestDTO getRandomPuzzleRequestDTO() {
        return PuzzleRequestDTO.builder()
                .lines(new Random().nextInt(PUZZLE_MAXIMUM_RANDOM_AMOUNT) + PUZZLE_MINIMUM_AMOUNT_OF_LINES)
                .columns(new Random().nextInt(PUZZLE_MAXIMUM_RANDOM_AMOUNT) + PUZZLE_MINIMUM_AMOUNT_OF_COLUMNS)
                .puzzleDifficulty(PuzzleDifficulty.getRandom())
                .build();
    }

}
