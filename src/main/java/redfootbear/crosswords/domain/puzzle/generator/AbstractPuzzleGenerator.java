package redfootbear.crosswords.domain.puzzle.generator;

import java.util.List;
import redfootbear.crosswords.domain.puzzle.model.Puzzle;
import redfootbear.crosswords.domain.puzzle.model.PuzzleCrossword;
import redfootbear.crosswords.domain.puzzle.repository.PuzzleCrosswordRepository;

public abstract class AbstractPuzzleGenerator implements PuzzleGenerator {

    protected final PuzzleCrosswordRepository puzzleCrosswordRepository;
    protected List<PuzzleCrossword> crosswords;
    protected Character[][] grid;
    protected Integer lines;
    protected Integer columns;

    protected AbstractPuzzleGenerator(PuzzleCrosswordRepository puzzleCrosswordRepository) {
        this.puzzleCrosswordRepository = puzzleCrosswordRepository;
    }

    @Override
    public PuzzleGenerator setLines(Integer lines) {
        this.lines = lines;
        return this;
    }

    @Override
    public PuzzleGenerator setColumns(Integer columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public Puzzle build() {
        crosswords = fetchCrosswords();
        grid = buildGrid();
        return Puzzle.builder()
                .crosswords(crosswords)
                .grid(grid)
                .lines(lines)
                .columns(columns)
                .build();
    }

    protected abstract List<PuzzleCrossword> fetchCrosswords();

    protected abstract Character[][] buildGrid();

}
