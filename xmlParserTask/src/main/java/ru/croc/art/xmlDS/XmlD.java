package ru.croc.art.xmlDS;

import ru.croc.art.filmDS.FilmD;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
/**
 * структура класса, который будет принимать данные из XML-файла
 */
@XmlRootElement(name = "films")
public class XmlD {

    private List<FilmD> films = new ArrayList<>();

    public List<FilmD> getFilms() {
        return films;
    }
    @XmlElement(name = "film")
    public void setFilms(List<FilmD> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "XmlS{" +
                "films=" + films +
                '}';
    }
}
