package ru.croc.art.model.xmlClasses.dto;

import ru.croc.art.model.xmlClasses.DischargedPatients.StatisticsPerDay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс статистики заболеваемости для конвертации из xml
 */
@XmlRootElement(name = "statistics")
public class StatisticsDTO implements DTOCollection{
    /**
     * список статистики заболеваемости, сгруппированной по датам
     */
    @XmlElement(name = "date")
    private List<StatisticsPerDay> dates = new ArrayList<StatisticsPerDay>();

    public StatisticsDTO() {
    }

    public StatisticsDTO(List<StatisticsPerDay> dates) {
        this.dates = dates;
    }

    public List<StatisticsPerDay> getStatistics() {
        return dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsDTO that = (StatisticsDTO) o;
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
