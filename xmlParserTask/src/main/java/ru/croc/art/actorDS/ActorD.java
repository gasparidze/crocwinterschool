package ru.croc.art.actorDS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * структура класса, который будет принимать данные из XML-файла
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActorD {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String role;

    public ActorD() {
    }

    public ActorD(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;

    }

    @Override
    public String toString() {
        return name;
    }
}

