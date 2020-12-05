package ru.croc.art.parser;

import org.junit.jupiter.api.*;
import ru.croc.art.model.DataBaseRow;
import ru.croc.art.model.xmlClasses.DischargedPatients.DischargedPatientsPerDay;
import ru.croc.art.model.xmlClasses.DischargedPatients.StatisticsPerDay;
import ru.croc.art.model.xmlClasses.dto.DischargedPatientsDTO;
import ru.croc.art.model.xmlClasses.dto.StatisticsDTO;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@DisplayName("Тест, проверяющий распарсивания данных")
public class ParserTest {
    //тестовые объекты
    private Parser parser;
    private StatisticsDTO statisticsDTO;
    private DischargedPatientsDTO dischargedPatientsDTO;
    private DataBaseRow dataBaseRow1;
    private DataBaseRow dataBaseRow2;
    private DataBaseRow dataBaseRow3;
    private List<DataBaseRow> dataBaseRows;
    private StatisticsPerDay date1;
    private StatisticsPerDay date2;
    private StatisticsPerDay date3;
    private DischargedPatientsPerDay dischargedPatientsPerDay1;
    private DischargedPatientsPerDay dischargedPatientsPerDay2;
    private DischargedPatientsPerDay dischargedPatientsPerDay3;

    @BeforeEach
    public void init() {
        dataBaseRow1 = new DataBaseRow(1, Date.valueOf("2020-11-23"), 10, 7, 5);
        dataBaseRow2 = new DataBaseRow(2, Date.valueOf("2020-11-24"), 9, 8, 2);
        dataBaseRow3 = new DataBaseRow(3, Date.valueOf("2020-11-25"), 8, 4, 1);
        dataBaseRows = Arrays.asList(dataBaseRow1, dataBaseRow2, dataBaseRow3);

        date1 = new StatisticsPerDay("2020-11-23", 10, 7);
        date2 = new StatisticsPerDay("2020-11-24", 9, 8);
        date3 = new StatisticsPerDay("2020-11-25", 8, 4);


        dischargedPatientsPerDay1 = new DischargedPatientsPerDay("2020-11-23", 5);
        dischargedPatientsPerDay2 = new DischargedPatientsPerDay("2020-11-24", 2);
        dischargedPatientsPerDay3 = new DischargedPatientsPerDay("2020-11-25", 1);

        //инициализация статистики заболевания по датам
        statisticsDTO =
                new StatisticsDTO(Arrays.asList(date1, date2, date3));

        //инициализация статистики выписанных пациентов по датам
        dischargedPatientsDTO =
                new DischargedPatientsDTO(Arrays.asList(dischargedPatientsPerDay1, dischargedPatientsPerDay2,
                        dischargedPatientsPerDay3));

        //инициализация статисти
        parser = new Parser(statisticsDTO, dischargedPatientsDTO);
    }

    @DisplayName("Проверка парсера")
    @Test
    public void testParser() {
        Assertions.assertEquals(parser.pars(), dataBaseRows);
    }

    @DisplayName("Очищаем данные")
    @AfterEach
    public void cleaning() {
        parser = null;
        statisticsDTO = null;
        dischargedPatientsDTO = null;
        dataBaseRow1 = null;
        dataBaseRow2 = null;
        dataBaseRow3 = null;
        dataBaseRows = null;
        date1 = null;
        date2 = null;
        date3 = null;
        dischargedPatientsPerDay1 = null;
        dischargedPatientsPerDay2 = null;
        dischargedPatientsPerDay3 = null;
    }

}
