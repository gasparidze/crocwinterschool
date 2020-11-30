package ru.croc.art.conversion;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * класс для конвертации XML в заданный классом список
 */
public class Conversion {
    /**
     * метод конвертирует xml в объект заданного класса
     *
     * @param str    - путь к xml файлу
     * @param object - объект, структура класса которого используется для конвертации
     * @return - возвращает объект, хранящий данные из xml
     * @throws JAXBException
     */
    public Object convertFromXml(String str, Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        object = (Object) unmarshaller.unmarshal(new File(str));
        return object;
    }
}
