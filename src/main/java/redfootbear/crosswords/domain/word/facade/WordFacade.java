package redfootbear.crosswords.domain.word.facade;

import redfootbear.crosswords.api.word.NewWord;
import redfootbear.crosswords.domain.word.model.Word;

public interface WordFacade {

    Word createNewWord(NewWord newWord);

    Word findByWord(String word);

    Word findByLengthAndIndexPositionAndIndexCharacter(Integer length, Integer position, Character character);

}
