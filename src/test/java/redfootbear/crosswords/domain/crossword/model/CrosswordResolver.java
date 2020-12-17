package redfootbear.crosswords.domain.crossword.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import redfootbear.crosswords.domain.crossword.service.CrosswordIndexerServiceImpl;
import redfootbear.randomizer.AlphabeticRandomizer;

public class CrosswordResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(FakeCrossword.class) ||
                parameterContext.isAnnotated(FakeCrosswordNoIndex.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        Crossword crossword = generateFakeCrossword();
        if (parameterContext.isAnnotated(FakeCrosswordNoIndex.class)) {
            return crossword;
        } else {
            return new CrosswordIndexerServiceImpl().buildIndexedCrossword(crossword);
        }
    }

    private Crossword generateFakeCrossword() {
        return Crossword.builder()
                .word(AlphabeticRandomizer.getRandomLetters(new Random().nextInt(10) + 2))
                .build();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface FakeCrossword {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface FakeCrosswordNoIndex {

    }

}
