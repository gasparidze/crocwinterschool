package ru.croc.art.model.xmlClasses.dto;

import ru.croc.art.model.xmlClasses.DischargedPatients.DischargedPatientsPerDay;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс статистики выписанных пациентов для конвертации из xml
 */
@XmlRootElement(name = "discharged")
public class DischargedPatientsDTO implements DTOCollection{
    /**
     * список статистики выписанных пациентов по датам
     */
    private List<DischargedPatientsPerDay> dischargedPatientsPerDayList = new ArrayList<>();

    public DischargedPatientsDTO() {
    }

    public DischargedPatientsDTO(List<DischargedPatientsPerDay> dischargedPatientsPerDayList) {
        this.dischargedPatientsPerDayList = dischargedPatientsPerDayList;
    }

    @XmlElement(name = "discharge")
    public List<DischargedPatientsPerDay> getDischargedPatientsPerDayList() {
        return dischargedPatientsPerDayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DischargedPatientsDTO that = (DischargedPatientsDTO) o;
        return Objects.equals(dischargedPatientsPerDayList, that.dischargedPatientsPerDayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dischargedPatientsPerDayList);
    }

    @Override
    public String toString() {
        return "DischargedPatientsList{" +
                "dischargedPatientsList=" + dischargedPatientsPerDayList +
                '}';
    }
}
