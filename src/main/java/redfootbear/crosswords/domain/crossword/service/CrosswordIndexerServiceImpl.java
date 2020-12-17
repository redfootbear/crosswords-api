package redfootbear.crosswords.domain.crossword.service;

import java.util.ArrayList;
import java.util.List;
import redfootbear.crosswords.domain.crossword.model.Crossword;

public class CrosswordIndexerServiceImpl implements CrosswordIndexerService {

    @Override
    public Crossword buildIndexedCrossword(Crossword crossword) {
        return crossword.toBuilder()
                .indexes(splitWordInCharacters(crossword.getWord()))
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
