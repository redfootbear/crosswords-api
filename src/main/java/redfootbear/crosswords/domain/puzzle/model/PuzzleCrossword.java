package redfootbear.crosswords.domain.puzzle.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PuzzleCrossword {

    private final String crossword;
    private final Direction direction;
    private final String clue;

}
