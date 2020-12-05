package ru.croc.art.model.xmlClasses.DischargedPatients;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * класс Даты для конвертации из xml
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticsPerDay {
    /**
     * дата
     */
    @XmlAttribute(name = "date")
    private String date;
    /**
     * кол-во заболевших
     */
    @XmlElement(name = "disease")
    private int disease;
    /**
     * кол-во выздоровевших
     */
    @XmlElement(name = "recovery")
    private int recovery;


    public StatisticsPerDay() {
    }

    public StatisticsPerDay(String date, int disease, int recovery) {
        this.date = date;
        this.disease = disease;
        this.recovery = recovery;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return disease;
    }

    public int getRecovery() {
        return recovery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsPerDay statisticsPerDay = (StatisticsPerDay) o;
        return disease == statisticsPerDay.disease &&
                recovery == statisticsPerDay.recovery &&
                Objects.equals(date, statisticsPerDay.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, disease, recovery);
    }

    @Override
    public String toString() {
        return "Disease{" +
                "date='" + date + '\'' +
                ", quantity=" + disease +
                ", recovery=" + recovery +
                '}';
    }
}
