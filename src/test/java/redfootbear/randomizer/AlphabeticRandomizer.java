package redfootbear.randomizer;

import java.util.Random;

public class AlphabeticRandomizer {

    public static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVXWYZ";

    public static String getRandomLetters(Integer length) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(getLetter());
        }

        return stringBuilder.toString();
    }

    private static Character getLetter() {
        return LETTERS.charAt(new Random().nextInt(LETTERS.length()));
    }

}
