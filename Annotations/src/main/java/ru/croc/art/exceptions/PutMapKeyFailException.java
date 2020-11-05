package ru.croc.art.exceptions;

/**
 * исключение, которое будет выброшено, если класс ключа помечен аннотацией @MapKeyFail
 */
public class PutMapKeyFailException extends RuntimeException {
    /**
     * конструктор
     * @param message - сообщение,передаваемое родительскому классу
     */
    public PutMapKeyFailException(String message) {
        super(message);
    }
}
