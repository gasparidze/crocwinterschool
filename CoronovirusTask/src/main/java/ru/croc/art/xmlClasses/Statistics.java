package ru.croc.art.xmlClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс статистики заболеваемости для конвертации из xml
 */
@XmlRootElement(name = "statistics")
public class Statistics {
    /**
     * список статистики заболеваемости, сгруппированной по датам
     */
    @XmlElement(name = "date")
    private List<Dates> dates = new ArrayList<Dates>();

    public Statistics() {
    }

    public Statistics(List<Dates> dates) {
        this.dates = dates;
    }

    public List<Dates> getStatistics() {
        return dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(dates, that.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dates);
    }

    @Override
    public String toString() {
        return "DiseasesList{" +
                "statistics=" + dates +
                '}';
    }
}
