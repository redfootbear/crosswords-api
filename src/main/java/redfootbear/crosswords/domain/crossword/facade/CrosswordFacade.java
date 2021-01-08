package redfootbear.crosswords.domain.crossword.facade;

import redfootbear.crosswords.api.crossword.NewCrosswordDTO;
import redfootbear.crosswords.domain.crossword.model.Crossword;

public interface CrosswordFacade {

    Crossword createNewCrossword(NewCrosswordDTO newCrossword);

    Crossword findByWord(String word);

    Crossword findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character);

}
