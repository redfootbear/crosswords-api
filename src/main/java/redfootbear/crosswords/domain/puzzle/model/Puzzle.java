package redfootbear.crosswords.domain.puzzle.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Puzzle {

    private final Integer lines;
    private final Integer columns;
    private final Character[][] grid;
    private final List<PuzzleCrossword> crosswords;

}
