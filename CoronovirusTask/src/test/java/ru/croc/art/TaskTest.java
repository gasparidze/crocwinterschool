package ru.croc.art;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.art.repository.Repository;
import ru.croc.art.conversion.Conversion;
import ru.croc.art.db.DataSourceProvider;
import ru.croc.art.model.Row;
import ru.croc.art.parser.Parser;
import ru.croc.art.xmlClasses.Dates;
import ru.croc.art.xmlClasses.DischargedPatients;
import ru.croc.art.xmlClasses.Statistics;
import ru.croc.art.xmlClasses.StatisticDischargedPatients;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * класс для тестирования задачи
 */
public class TaskTest {
    /**
     * подлкюченик к БД
     */
    private static DataSourceProvider dataSourceProvider = null;
    /**
     * репозиторий для работы с БД
     */
    private static Repository repository;
    /**
     * конвертация из xml в структуру нужного класса
     */
    private static Conversion conversion;
    /**
     * статистика заболеваемости
     */
    private static Statistics statistics;
    /**
     * статистика выписанных пациентов
     */
    private static StatisticDischargedPatients statisticDischargedPatients;
    /**
     * парсер
     */
    private static Parser parser;
    /**
     * дополнительный список для хранения распарсенных данных
     */
    private static List<Row> parsList;

    /**
     * подключение к БД и инициализация данных
     * @throws IOException
     */
    @BeforeAll
    static void init() throws IOException {
        try {
            dataSourceProvider = new DataSourceProvider();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        repository = new Repository(dataSourceProvider.getDataSource());
        conversion = new Conversion();
        statistics = new Statistics();
        statisticDischargedPatients = new StatisticDischargedPatients();
    }
    @DisplayName("тест на конвертацию данных из xml")
    @Test
    public void convertTest() throws JAXBException {
        //пути к xml файлам
        String pathStatisticXml = "src/main/resources/coronovirusStatistic/Statistics.xml";
        String pathDischargedPatientsXml = "src/main/resources/coronovirusStatistic/DischargedPatients.xml";

        Dates date1 = new Dates("2020-11-23",10,7);
        Dates date2 = new Dates("2020-11-24",9,8);
        Dates date3 = new Dates("2020-11-25",8,4);
        Dates date4 = new Dates("2020-11-26",12,14);
        Dates date5 = new Dates("2020-11-27",13,2);
        Dates date6 = new Dates("2020-11-28",15,9);
        Dates date7 = new Dates("2020-11-29",10,4);
        //инициализация статистики заболевания по датам
        Statistics statisticsExpected =
                new Statistics(Arrays.asList(date1,date2,date3,date4,date5,date6,date7));

        DischargedPatients dischargedPatients1 = new DischargedPatients("2020-11-23",5);
        DischargedPatients dischargedPatients2 = new DischargedPatients("2020-11-24",2);
        DischargedPatients dischargedPatients3 = new DischargedPatients("2020-11-25",1);
        DischargedPatients dischargedPatients4 = new DischargedPatients("2020-11-26",3);
        DischargedPatients dischargedPatients5 = new DischargedPatients("2020-11-27",6);
        DischargedPatients dischargedPatients6 = new DischargedPatients("2020-11-28",8);
        DischargedPatients dischargedPatients7 = new DischargedPatients("2020-11-29",3);

        //инициализация статистики выписанных пациентов по датам
        StatisticDischargedPatients statisticDischargedPatientsExpected =
                new StatisticDischargedPatients(Arrays.asList(dischargedPatients1,dischargedPatients2,dischargedPatients3,
                        dischargedPatients4,dischargedPatients5,dischargedPatients6,dischargedPatients7));

        //преобразование данных из xml в струткуру класса statisticDates
        statistics = (Statistics) conversion.convertFromXml(pathStatisticXml, statistics);
        //преобразование данных из xml в струткуру класса statisticDischargedPatients
        statisticDischargedPatients = (StatisticDischargedPatients) conversion.convertFromXml(pathDischargedPatientsXml,
                        statisticDischargedPatients);
        //тест для проверки конвертации статистики заболеваемости
        Assertions.assertEquals(statisticsExpected, statistics);
        //тест для проверки конвертации статистики выписанных пациентов
        Assertions.assertEquals(statisticDischargedPatientsExpected, statisticDischargedPatients);
    }

    /**
     *
     * @return список записей в формате: id-дата-кол-во заболевших-кол-во выздоровевших-кол-во выписанных
     */
    private List<Row> getRows(){
        Row row1 = new Row(1, Date.valueOf("2020-11-23"),10,7,5);
        Row row2 = new Row(2, Date.valueOf("2020-11-24"),9,8,2);
        Row row3 = new Row(3, Date.valueOf("2020-11-25"),8,4,1);
        Row row4 = new Row(4, Date.valueOf("2020-11-26"),12,14,3);
        Row row5 = new Row(5, Date.valueOf("2020-11-27"),13,2,6);
        Row row6 = new Row(6, Date.valueOf("2020-11-28"),15,9,8);
        Row row7 = new Row(7, Date.valueOf("2020-11-29"),10,4,3);
        List<Row> rowsExpected = new ArrayList<>(Arrays.asList(row1,row2,row3,row4,row5,row6,row7));
        return rowsExpected;
    }
    @DisplayName("Тест на извлечение необходимых данных")
    @Test
    public void parserTest() throws Exception {
        parser = new Parser(statistics, statisticDischargedPatients);
        // присваиваю переменной значения метода, т.к. объект parser - статический, а его требуется вызвать 2 раза:
        // первый - в данном тесте, второй - в тесте на добавление записи в БД
        // Саша мне как-то сделал замечание в одной из программ о "мутирующих методах", я думаю это как раз тот случай,
        // поэтому, чтобы не вызывать 2 раза метод pars, помещаю значение в переменную и использую сколько требуется
        parsList = parser.pars();
        Assertions.assertEquals(getRows(),parsList);
    }
    @DisplayName("Тест на добавление записи в БД")
    @Test
    public void repositoryTest(){
        repository.createNewRows(parsList);
        Assertions.assertEquals(getRows().toString(),repository.allRows().toString());
    }
}

