package redfootbear.crosswords.domain.word.service;

import java.util.ArrayList;
import java.util.List;
import redfootbear.crosswords.domain.word.model.Word;

public class WordIndexerServiceImpl implements WordIndexerService {

    @Override
    public Word buildIndexedWord(Word word) {
        return word.toBuilder()
                .indexes(splitWordInCharacters(word.getWord()))
                .build();
    }

    List<Character> splitWordInCharacters(String word) {
        List<Character> characters = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            characters.add(word.charAt(i));
        }

        return characters;
    }

}
