package ru.croc.art.annotations;

import java.lang.annotation.*;

/**
 * Класс, в котором описаны две аннотации
 */
public class Annotation {
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface MapKeyFail{

    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface MapValueFail{

    }
}


