package ru.croc.art.model;
import java.sql.Date;

public class Student {
    /**
     * id студента
     */
    private Integer id;
    /**
     * имя студента
     */
    private String name;
    /**
     * средняя оценка студента
     */
    private Float averageMark;
    /**
     * поле, отражающее состояние сессии: закрыта или нет
     */
    private Boolean closedSession;
    /**
     * дата поступления студента в университет
     */
    private Date startEducation;

    public Student(){}

    public Student(Integer id, String name, Float averageMark, Boolean closedSession, Date startEducation) {
        this.id = id;
        this.name = name;
        this.averageMark = averageMark;
        this.closedSession = closedSession;
        this.startEducation = startEducation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getAverageMark() {
        return averageMark;
    }

    public Boolean isClosedSession() {
        return closedSession;
    }

    public Date getStartEducation() {
        return startEducation;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id= " + id +
                ", имя: '" + name + '\'' +
                ", средняя оценка = " + averageMark +
                ", сессия: " + closedSession +
                ", начало обучения: " + startEducation +
                '}';
    }
}
