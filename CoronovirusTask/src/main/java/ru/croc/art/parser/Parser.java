package ru.croc.art.parser;

import ru.croc.art.model.*;
import ru.croc.art.model.xmlClasses.DischargedPatients.StatisticsPerDay;
import ru.croc.art.model.xmlClasses.DischargedPatients.DischargedPatientsPerDay;
import ru.croc.art.model.xmlClasses.dto.StatisticsDTO;
import ru.croc.art.model.xmlClasses.dto.DischargedPatientsDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс слуджит для распарсивания данных
 */
public class Parser {
    /**
     * статистика
     */
    private StatisticsDTO statisticsDTO;
    /**
     * выписанные пациенты
     */
    private DischargedPatientsDTO dischargedPatientsDTO;
    /**
     * идентификатор для локального использования
     */
    private static Integer id = 0;
    /**
     * записи в таблице
     */
    private List<DataBaseRow> dataBaseRows;

    public Parser(StatisticsDTO statisticsDTO, DischargedPatientsDTO dischargedPatientsDTO) {
        this.statisticsDTO = statisticsDTO;
        this.dischargedPatientsDTO = dischargedPatientsDTO;
    }

    /**
     * метод для извлечения и формирования необходимых данных
     *
     * @return коллекция распарсенных данных
     */
    public List<DataBaseRow> pars() {
        //список записей
        dataBaseRows = new ArrayList<>();
        //проходимся по статистике, в которой хранятся дата, кол-во заболевших и выздоровевших
        for (StatisticsPerDay statisticsPerDay : statisticsDTO.getStatistics()) {
            //проходимся по статистике выписанных пациентов
            for (DischargedPatientsPerDay dischargedPatientsPerDay : dischargedPatientsDTO.getDischargedPatientsPerDayList()) {
                //т.к. нам нужно соединить данные из этих двух классов, то проверяем их даты,
                // т.к. дата является связующем звеном
                if (statisticsPerDay.getDate().equals(dischargedPatientsPerDay.getDate())) {
                    //дата в классах представлена как поле String
                    //преобразуем ее с помощью метода valueOf в формат Date(SQL)
                    Date date = Date.valueOf(statisticsPerDay.getDate());
                    //создаем запись и помещаем всю нужную информацию
                    DataBaseRow dataBaseRow = new DataBaseRow(++id, date, statisticsPerDay.getQuantity(), statisticsPerDay.getRecovery(),
                            dischargedPatientsPerDay.getQuantity());
                    //добавляем запись в исходную коллекцию
                    dataBaseRows.add(dataBaseRow);
                }
            }
        }
        return dataBaseRows;
    }
}
