package redfootbear.crosswords.domain.crossword.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.driver.types.Node;
import redfootbear.crosswords.api.crossword.NewCrosswordDTO;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crossword {

    public static final String PROPERTY_WORD = "word";
    public static final String PROPERTY_CLUE = "clues";

    private String word;
    private List<String> clues;
    private List<Character> indexes;

    public static Crossword from(NewCrosswordDTO newCrossword) {
        return Crossword.builder()
                .word(newCrossword.getWord().toUpperCase())
                .build();
    }

    public static Crossword from(Node node) {
        return Crossword.builder()
                .word(node.get(PROPERTY_WORD).asString())
                .build();
    }

    public Integer getLength() {
        return word.length();
    }

}
