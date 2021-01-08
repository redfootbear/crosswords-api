package redfootbear.crosswords.domain.puzzle.service;

import javax.inject.Inject;
import redfootbear.crosswords.domain.puzzle.exception.CantBuildPuzzleGeneratorException;
import redfootbear.crosswords.domain.puzzle.generator.PuzzleGenerator;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.model.PuzzleDifficulty;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;

public class PuzzleGeneratorServiceImpl implements PuzzleGeneratorService {

    private final PuzzleCrosswordRepository puzzleCrosswordRepository;

    @Inject
    public PuzzleGeneratorServiceImpl(PuzzleCrosswordRepository puzzleCrosswordRepository) {
        this.puzzleCrosswordRepository = puzzleCrosswordRepository;
    }

    @Override
    public Puzzle generatePuzzle(Integer lines, Integer columns, PuzzleDifficulty puzzleDifficulty) {
        return getPuzzleGeneratorInstance(puzzleDifficulty)
                .setLines(lines)
                .setColumns(columns)
                .build();
    }

    PuzzleGenerator getPuzzleGeneratorInstance(PuzzleDifficulty puzzleDifficulty) {
        try {
            return puzzleDifficulty.getPuzzleGenerator()
                    .getDeclaredConstructor(PuzzleCrosswordRepository.class)
                    .newInstance(puzzleCrosswordRepository);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CantBuildPuzzleGeneratorException();
        }
    }

}
