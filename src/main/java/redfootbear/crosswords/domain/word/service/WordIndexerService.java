package redfootbear.crosswords.domain.word.service;

import redfootbear.crosswords.domain.word.model.Word;

public interface WordIndexerService {

    Word buildIndexedWord(Word word);

}
