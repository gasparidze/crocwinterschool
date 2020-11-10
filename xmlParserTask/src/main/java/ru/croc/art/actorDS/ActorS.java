package ru.croc.art.actorDS;

import ru.croc.art.filmDS.FilmS;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * структура класса, в котором будут храниться распарсенные и преобразованные данные
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActorS {
    @XmlElement
    private String name;
    @XmlElement
    private int age;
    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    private List<FilmS> filmRole;

    public ActorS() {
    }

    public ActorS(String name, int age, List<FilmS> filmRole) {
        this.name = name;
        this.age = age;
        this.filmRole = filmRole;
    }

    public List<FilmS> getFilmRole() {
        return filmRole;
    }

    public void setFilmRole(List<FilmS> filmRole) {
        this.filmRole = filmRole;
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


    @Override
    public String toString() {
        return name;
    }
}
