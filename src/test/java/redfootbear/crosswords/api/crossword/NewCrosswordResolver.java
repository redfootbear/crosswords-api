package redfootbear.crosswords.api.crossword;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import redfootbear.randomizer.AlphabeticRandomizer;

public class NewCrosswordResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(FakeNewCrossword.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        return NewCrosswordDTO.builder()
                .word(AlphabeticRandomizer.getRandomLetters(new Random().nextInt(10) + 2))
                .build();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface FakeNewCrossword {

    }

}
