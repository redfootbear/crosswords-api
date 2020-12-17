package redfootbear.crosswords.domain.crossword.facade;

import redfootbear.crosswords.api.crossword.NewCrossword;
import redfootbear.crosswords.domain.crossword.model.Crossword;

public interface CrosswordFacade {

    Crossword createNewCrossword(NewCrossword newCrossword);

    Crossword findByWord(String word);

    Crossword findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character);

}
