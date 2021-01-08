package redfootbear.crosswords.api.crossword;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCrosswordDTO {

    private String word;
    private List<String> clues;

    public String getWord() {
        return word.toUpperCase();
    }

}
