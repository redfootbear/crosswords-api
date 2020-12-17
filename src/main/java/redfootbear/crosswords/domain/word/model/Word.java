package redfootbear.crosswords.domain.word.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.neo4j.driver.types.Node;
import redfootbear.crosswords.api.word.NewWord;

@Getter
@Builder(toBuilder = true)
public class Word {

    public static final String PROPERTY_WORD = "word";
    public static final String PROPERTY_CLUE = "clue";

    private final String word;
    private final String clue;
    private final List<Character> indexes;

    public static Word from(NewWord newWord) {
        return Word.builder()
                .word(newWord.getWord().toUpperCase())
                .clue(newWord.getClue())
                .build();
    }

    public static Word from(Node node) {
        return Word.builder()
                .word(node.get(PROPERTY_WORD).asString())
                .clue(node.get(PROPERTY_CLUE).asString())
                .build();
    }

    public Integer getLength() {
        return word.length();
    }

}
