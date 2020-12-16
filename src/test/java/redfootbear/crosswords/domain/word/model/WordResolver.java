package redfootbear.crosswords.domain.word.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import redfootbear.crosswords.domain.word.service.WordIndexerServiceImpl;
import redfootbear.randomizer.AlphabeticRandomizer;

public class WordResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(FakeWord.class) ||
                parameterContext.isAnnotated(FakeWordNoIndex.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        Word word = generateFakeWord();
        if (parameterContext.isAnnotated(FakeWordNoIndex.class)) {
            return word;
        } else {
            return new WordIndexerServiceImpl().buildIndexedWord(word);
        }
    }

    private Word generateFakeWord() {
        return Word.builder()
                .word(AlphabeticRandomizer.getRandomLetters(new Random().nextInt(10) + 2))
                .clue(AlphabeticRandomizer.getRandomLetters(new Random().nextInt(100) + 20))
                .build();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface FakeWord {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface FakeWordNoIndex {

    }

}
