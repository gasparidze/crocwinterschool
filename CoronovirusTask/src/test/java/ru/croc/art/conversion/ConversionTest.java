package ru.croc.art.conversion;

import org.junit.jupiter.api.*;
import ru.croc.art.model.xmlClasses.DischargedPatients.DischargedPatientsPerDay;
import ru.croc.art.model.xmlClasses.DischargedPatients.StatisticsPerDay;
import ru.croc.art.model.xmlClasses.dto.DischargedPatientsDTO;
import ru.croc.art.model.xmlClasses.dto.StatisticsDTO;

import javax.xml.bind.JAXBException;
import java.util.Arrays;

@DisplayName("Тест на проверку корректного конвертирования данных")
public class ConversionTest {

    //пути к xml файлам
    private String pathStatisticXml = "src/main/resources/coronovirusStatistic/Statistics.xml";
    private String pathDischargedPatientsXml = "src/main/resources/coronovirusStatistic/DischargedPatients.xml";

    //тестовые объекты
    private StatisticsDTO statisticsDTOExpected;
    private DischargedPatientsDTO dischargedPatientsDTOExpected;
    private Conversion conversion;
    private StatisticsDTO statisticsDTO;
    private DischargedPatientsDTO dischargedPatientsDTO;
    private StatisticsPerDay date1;
    private StatisticsPerDay date2;
    private StatisticsPerDay date3;
    private StatisticsPerDay date4;
    private StatisticsPerDay date5;
    private StatisticsPerDay date6;
    private StatisticsPerDay date7;
    private DischargedPatientsPerDay dischargedPatientsPerDay1;
    private DischargedPatientsPerDay dischargedPatientsPerDay2;
    private DischargedPatientsPerDay dischargedPatientsPerDay3;
    private DischargedPatientsPerDay dischargedPatientsPerDay4;
    private DischargedPatientsPerDay dischargedPatientsPerDay5;
    private DischargedPatientsPerDay dischargedPatientsPerDay6;
    private DischargedPatientsPerDay dischargedPatientsPerDay7;

    @BeforeEach
    public void init() {
        conversion = new Conversion();
        statisticsDTO = new StatisticsDTO();
        dischargedPatientsDTO = new DischargedPatientsDTO();

        date1 = new StatisticsPerDay("2020-11-23", 10, 7);
        date2 = new StatisticsPerDay("2020-11-24", 9, 8);
        date3 = new StatisticsPerDay("2020-11-25", 8, 4);
        date4 = new StatisticsPerDay("2020-11-26", 12, 14);
        date5 = new StatisticsPerDay("2020-11-27", 13, 2);
        date6 = new StatisticsPerDay("2020-11-28", 15, 9);
        date7 = new StatisticsPerDay("2020-11-29", 10, 4);

        dischargedPatientsPerDay1 = new DischargedPatientsPerDay("2020-11-23", 5);
        dischargedPatientsPerDay2 = new DischargedPatientsPerDay("2020-11-24", 2);
        dischargedPatientsPerDay3 = new DischargedPatientsPerDay("2020-11-25", 1);
        dischargedPatientsPerDay4 = new DischargedPatientsPerDay("2020-11-26", 3);
        dischargedPatientsPerDay5 = new DischargedPatientsPerDay("2020-11-27", 6);
        dischargedPatientsPerDay6 = new DischargedPatientsPerDay("2020-11-28", 8);
        dischargedPatientsPerDay7 = new DischargedPatientsPerDay("2020-11-29", 3);

        //инициализация статистики заболевания по датам
        statisticsDTOExpected =
                new StatisticsDTO(Arrays.asList(date1, date2, date3, date4, date5, date6, date7));

        //инициализация статистики выписанных пациентов по датам
        dischargedPatientsDTOExpected =
                new DischargedPatientsDTO(Arrays.asList(dischargedPatientsPerDay1, dischargedPatientsPerDay2,
                        dischargedPatientsPerDay3, dischargedPatientsPerDay4, dischargedPatientsPerDay5, dischargedPatientsPerDay6, dischargedPatientsPerDay7));

    }

    @DisplayName("тест на конвертацию данных из xml")
    @Test
    public void testConvertFromXml() throws JAXBException {
        statisticsDTO = (StatisticsDTO) conversion.convertFromXml(pathStatisticXml, statisticsDTO);
        dischargedPatientsDTO = (DischargedPatientsDTO) conversion.convertFromXml(pathDischargedPatientsXml,
                dischargedPatientsDTO);
        //тест для проверки конвертации статистики заболеваемости
        Assertions.assertEquals(statisticsDTOExpected, statisticsDTO);
        //тест для проверки конвертации статистики выписанных пациентов
        Assertions.assertEquals(dischargedPatientsDTOExpected, dischargedPatientsDTO);
    }

    @DisplayName("Очищаем данные")
    @AfterEach
    public void cleaning() {
        statisticsDTOExpected = null;
        dischargedPatientsDTOExpected = null;
        conversion = null;
        statisticsDTO = null;
        dischargedPatientsDTO = null;
        date1 = null;
        date2 = null;
        date3 = null;
        date4 = null;
        date5 = null;
        date6 = null;
        date7 = null;
        dischargedPatientsPerDay1 = null;
        dischargedPatientsPerDay2 = null;
        dischargedPatientsPerDay3 = null;
        dischargedPatientsPerDay4 = null;
        dischargedPatientsPerDay5 = null;
        dischargedPatientsPerDay6 = null;
        dischargedPatientsPerDay7 = null;
    }
}

