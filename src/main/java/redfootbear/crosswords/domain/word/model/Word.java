package redfootbear.crosswords.domain.word.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import redfootbear.crosswords.api.word.NewWord;

@Getter
@Builder(toBuilder = true)
public class Word {

    private final String word;
    private final String clue;
    private final List<Character> indexes;

    public static Word from(NewWord newWord) {
        return Word.builder()
                .word(newWord.getWord().toUpperCase())
                .clue(newWord.getClue())
                .build();
    }

    public Integer getLength() {
        return word.length();
    }

}
