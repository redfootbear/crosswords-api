package redfootbear.neo4j;

import lombok.Builder;
import lombok.Getter;
import org.neo4j.driver.types.Node;

@Getter
@Builder(toBuilder = true)
public class FakeModel {

    public static final String PROPERTY_WORD = "word";

    private final String word;

    public static FakeModel from(Node node) {
        return FakeModel.builder()
                .word(node.get(PROPERTY_WORD).asString())
                .build();
    }

}
