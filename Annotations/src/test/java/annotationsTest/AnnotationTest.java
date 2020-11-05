package annotationsTest;

import ru.croc.art.HashMapExtended;
import ru.croc.art.exceptions.PutMapKeyFailException;
import ru.croc.art.exceptions.PutMapValueFailException;
import ru.croc.art.keys.TheFirstKey;
import ru.croc.art.keys.TheSecondKey;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.croc.art.values.TheFirstValue;
import ru.croc.art.values.TheSecondValue;

import java.util.HashMap;

public class AnnotationTest {
    /**мап, в котором будем проверять переопределенный метод put*/
    HashMap map = new HashMapExtended();
    /**ключ, не помеченный аннотацией MapKeyFail*/
    TheFirstKey theFirstKey = new TheFirstKey();
    /**значение ключа, помеченное аннотацией MapValueFail*/
    TheFirstValue theFirstValue = new TheFirstValue();
    /**ключ, помеченный аннотацией MapKeyFail*/
    TheSecondKey theSecondKey = new TheSecondKey();
    /**значение ключа, не помеченное аннотацией MapValueFail*/
    TheSecondValue theSecondValue = new TheSecondValue();

    /**
     * тест, проверяющий выброс исключения PutMapKeyFailException
     * @throws PutMapKeyFailException
     */
    @Test
    public void annotationKeyTest() throws PutMapKeyFailException {
        try {
            map.put(theSecondKey, theSecondValue);
        } catch (PutMapKeyFailException e) {
            Assertions.assertEquals("Ключ помечен аннотацией @MapKeyFail", e.getMessage());
        }
    }

    /**
     * тест, проверяющий выброс исключения PutMapValueFailException
     * @throws PutMapValueFailException
     */
    @Test
    public void annotationValueTest() throws PutMapValueFailException {
        try {
            map.put(theFirstKey, theFirstValue);
        } catch (PutMapValueFailException e) {
            Assertions.assertEquals("Значение помечено аннотацией @MapValueFail", e.getMessage());
        }
    }

}

