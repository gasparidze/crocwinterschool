package ru.croc.art.model;

import java.sql.Date;
import java.util.Objects;

public class Row {
    /**
     * идентификатор
     */
    private Integer id;
    /**
     * дата
     */
    private Date date;
    /**
     * кол-во заболевших
     */
    private Integer diseaseQuantity;
    /**
     * кол-во выздоровевших
     */
    private Integer recoveryQuantity;
    /**
     * кол-во выписанных
     */
    private Integer dischargedQuantity;

    public Row(Integer id, Date date, Integer diseaseQuantity, Integer recoveryQuantity, Integer dischargedQuantity) {
        this.id = id;
        this.date = date;
        this.diseaseQuantity = diseaseQuantity;
        this.recoveryQuantity = recoveryQuantity;
        this.dischargedQuantity = dischargedQuantity;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDiseaseQuantity() {
        return diseaseQuantity;
    }

    public Integer getRecoveryQuantity() {
        return recoveryQuantity;
    }

    public Integer getDischargedQuantity() {
        return dischargedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Objects.equals(id, row.id) &&
                Objects.equals(date, row.date) &&
                Objects.equals(diseaseQuantity, row.diseaseQuantity) &&
                Objects.equals(recoveryQuantity, row.recoveryQuantity) &&
                Objects.equals(dischargedQuantity, row.dischargedQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, diseaseQuantity, recoveryQuantity, dischargedQuantity);
    }


    @Override
    public String toString() {
        return "Row{" +
                "date=" + date +
                ", diseaseQuantity=" + diseaseQuantity +
                ", recoveryQuantity=" + recoveryQuantity +
                ", dischargedQuantity=" + dischargedQuantity +
                '}';
    }
}
