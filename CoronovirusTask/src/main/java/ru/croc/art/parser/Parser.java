package ru.croc.art.parser;

import ru.croc.art.model.*;
import ru.croc.art.xmlClasses.Dates;
import ru.croc.art.xmlClasses.DischargedPatients;
import ru.croc.art.xmlClasses.Statistics;
import ru.croc.art.xmlClasses.StatisticDischargedPatients;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * статистика
     */
    private Statistics statistics;
    /**
     * выписанные пациенты
     */
    private StatisticDischargedPatients statisticDischargedPatients;
    /**
     * идентификатор для локального использования
     */
    private static Integer id = 0;
    /**
     * записи в таблице
     */
    private List<Row> rows;

    public Parser(Statistics statistics, StatisticDischargedPatients statisticDischargedPatients) {
        this.statistics = statistics;
        this.statisticDischargedPatients = statisticDischargedPatients;
    }

    /**
     * метод для извлечения и формирования необходимых данных
     *
     * @return коллекция распарсенных данных
     */
    public List<Row> pars() {
        //список записей
        rows = new ArrayList<>();
        //проходимся по статистике, в которой хранятся дата, кол-во заболевших и выздоровевших
        for (Dates dates : statistics.getStatistics()) {
            //проходимся по статистике выписанных пациентов
            for (DischargedPatients dischargedPatients : statisticDischargedPatients.getDischargedPatientsList()) {
                //т.к. нам нужно соединить данные из этих двух классов, то проверяем их даты,
                // т.к. дата является связующем звеном
                if (dates.getDate().equals(dischargedPatients.getDate())) {
                    //дата в классах представлена как поле String
                    //преобразуем ее с помощью метода valueOf в формат Date(SQL)
                    Date date = Date.valueOf(dates.getDate());
                    //создаем запись и помещаем всю нужную информацию
                    Row row = new Row(++id, date, dates.getQuantity(), dates.getRecovery(),
                            dischargedPatients.getQuantity());
                    //добавляем запись в исходную коллекцию
                    rows.add(row);
                }
            }
        }
        return rows;
    }
}
