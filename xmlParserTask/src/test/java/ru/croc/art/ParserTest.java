package ru.croc.art;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import ru.croc.art.xmlDS.XmlS;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class ParserTest {
    @DisplayName("Тест парсера")
    @Test
    public void parserTest() throws JAXBException, FileNotFoundException {
        //путь к файлу, который нужно распарсить
        String str = "movie/movieD.xml";
        //файл, в котором хранятся ожидаемые распарсенные данные
        String strExpected = "movie/movieTest.xml";
        //файл, в котором хранятся оригинальные(фактические) данные
        String strActual = "movie/movieS.xml";

        Parser parser = new Parser();

        //в xmlSTest содержатся распарсенные данные
        XmlS xmlSTest =  parser.pars(str);
        JAXBContext context = JAXBContext.newInstance(XmlS.class);
        //занос распарсенных данных в файл
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlSTest, new File(strActual));
       //marshaller.marshal(xmlSTest,System.out);
        //преобразует содержимое файла в строку
        StringBuilder stringBuilderExpected = parser.toStr(strExpected);
        StringBuilder stringBuilderActual = parser.toStr(strActual);
        // проверка содержимого stringBilder
        Assertions.assertEquals(stringBuilderExpected.toString(), stringBuilderActual.toString());

    }
}
