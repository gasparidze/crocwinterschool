package ru.croc.art.xmlClasses;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс статистики выписанных пациентов для конвертации из xml
 */
@XmlRootElement(name = "discharged")
public class StatisticDischargedPatients {
    /**
     * список статистики выписанных пациентов по датам
     */
    private List<DischargedPatients> dischargedPatientsList = new ArrayList<>();

    public StatisticDischargedPatients() {
    }

    public StatisticDischargedPatients(List<DischargedPatients> dischargedPatientsList) {
        this.dischargedPatientsList = dischargedPatientsList;
    }

    @XmlElement(name = "discharge")
    public List<DischargedPatients> getDischargedPatientsList() {
        return dischargedPatientsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDischargedPatients that = (StatisticDischargedPatients) o;
        return Objects.equals(dischargedPatientsList, that.dischargedPatientsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dischargedPatientsList);
    }

    @Override
    public String toString() {
        return "DischargedPatientsList{" +
                "dischargedPatientsList=" + dischargedPatientsList +
                '}';
    }
}
