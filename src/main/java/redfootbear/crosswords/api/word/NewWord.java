package redfootbear.crosswords.api.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewWord {

    private String word;
    private String clue;

    public String getWord() {
        return word.toUpperCase();
    }

}
