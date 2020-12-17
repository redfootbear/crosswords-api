package redfootbear.crosswords.domain.word.repository;

import redfootbear.crosswords.domain.word.model.Word;

public interface WordRepository {

    void save(Word word);

    Word findByWord(String word);

    Word findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character);

}
