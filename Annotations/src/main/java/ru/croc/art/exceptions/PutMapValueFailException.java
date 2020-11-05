package ru.croc.art.exceptions;

/**
 * исключение, которое будет выброшено, если класс значения ключа помечено аннотацией @MapValueFail
 */
public class PutMapValueFailException extends RuntimeException {
    /**
     * конструктор
     * @param message - сообщение,передаваемое родительскому классу
     */
    public PutMapValueFailException(String message) {
        super(message);
    }
}
