package redfootbear.crosswords.api.puzzle;

import lombok.Builder;
import lombok.Getter;
import redfootbear.crosswords.domain.puzzle.model.PuzzleDifficulty;

@Getter
@Builder
public class PuzzleRequestDTO {

    private final Integer lines;
    private final Integer columns;
    private final PuzzleDifficulty puzzleDifficulty;

}
