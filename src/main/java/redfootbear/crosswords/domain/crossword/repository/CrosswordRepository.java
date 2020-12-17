package redfootbear.crosswords.domain.crossword.repository;

import redfootbear.crosswords.domain.crossword.model.Crossword;

public interface CrosswordRepository {

    void save(Crossword crossword);

    Crossword findByWord(String word);

    Crossword findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character);

}
