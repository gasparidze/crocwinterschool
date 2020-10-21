package testPackage;

import hey.Sentence;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * тест предложения
 */
public class SentenceTest {
    @Test
    public void testOfSentence(){
        /**
         * создание и подготовка объектов для тестирования предложения
         */
        Sentence sentence = new Sentence("Привет,друг,это23*?:%%%%%% я, как твои дела, привет снова, мой друг");
        String expectedStr = "привет друг это я как твои дела привет снова мой друг";
        Map<String, Integer> expectedRepetition = new HashMap<String, Integer>() {{
            put("привет", 2);
            put("друг", 2);
            put("это", 1);
            put("я", 1);
            put("как", 1);
            put("твои", 1);
            put("дела", 1);
            put("снова", 1);
            put("мой", 1);
        }};

        /** тест на очищение предложения*/
        Assertions.assertEquals(expectedStr, sentence.clearSentence());
        /** тест на сравнивание количества повторений слов в предложении*/
        Assertions.assertEquals(expectedRepetition,sentence.repetitions());
    }
}
