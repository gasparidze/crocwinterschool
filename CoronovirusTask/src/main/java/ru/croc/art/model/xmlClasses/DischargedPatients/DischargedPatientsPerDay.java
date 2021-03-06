package ru.croc.art.model.xmlClasses.DischargedPatients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * класс выписанных больных для конвертации из xml
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DischargedPatientsPerDay {

    /**
     * дата
     */
    @XmlAttribute(name = "date")
    private String date;

    /**
     * кол-во выписанных
     */
    @XmlAttribute(name = "quantity")
    private int quantity;

    public DischargedPatientsPerDay() {
    }

    public DischargedPatientsPerDay(String date, int quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DischargedPatientsPerDay that = (DischargedPatientsPerDay) o;
        return quantity == that.quantity &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, quantity);
    }


    @Override
    public String toString() {
        return "DischargedPatients{" +
                "date='" + date + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
