package ru.croc.art.conversion;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import ru.croc.art.model.xmlClasses.dto.DTOCollection;

/**
 * класс для конвертации XML в заданный классом список
 */
public class Conversion {
    /**
     * метод конвертирует xml в объект заданного класса
     *
     * @param str           - путь к xml файлу
     * @param dtoCollection - объект, структура класса которого используется для конвертации
     * @return - возвращает объект, хранящий данные из xml
     * @throws JAXBException ошибка конвертации
     */
    public Object convertFromXml(String str, DTOCollection dtoCollection) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(dtoCollection.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        dtoCollection = (DTOCollection) unmarshaller.unmarshal(new File(str));
        return dtoCollection;
    }

}
