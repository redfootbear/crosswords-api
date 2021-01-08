package redfootbear.crosswords.domain.puzzle.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {

    ACROSS("across"),
    DOWN("down");

    private final String value;

}
