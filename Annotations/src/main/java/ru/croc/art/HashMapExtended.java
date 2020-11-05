package ru.croc.art;

import ru.croc.art.annotations.Annotation;
import ru.croc.art.exceptions.PutMapKeyFailException;
import ru.croc.art.exceptions.PutMapValueFailException;

import java.util.HashMap;

/**
 * класс ru.croc.art.HashMapExtended расширяет HashMap
 * @param <K> - ключ
 * @param <V> - значение
 */
public class HashMapExtended<K,V> extends HashMap<K, V> {
    /**
     * переопределенный метод put
     * @param key - ключ
     * @param value - значение
     * @return - родительский метод put
     * @throws RuntimeException
     */
    @Override
    public V put(K key, V value) throws RuntimeException{
            if (key.getClass().getAnnotation(Annotation.MapKeyFail.class) != null) {
                throw new PutMapKeyFailException("Ключ помечен аннотацией @MapKeyFail");
            }
            if(value.getClass().getAnnotation(Annotation.MapValueFail.class) != null){
                throw new PutMapValueFailException("Значение помечено аннотацией @MapValueFail");
            }
        return super.put(key,value);
    }

}
