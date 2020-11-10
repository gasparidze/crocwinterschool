package ru.croc.art.filmDS;

import ru.croc.art.actorDS.ActorD;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * структура класса, который будет принимать данные из XML-файла
 */
@XmlRootElement(name = "film")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilmD {

    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    private List<ActorD> actors = new ArrayList();

    public FilmD(){}

    public FilmD(String title, String description, List<ActorD> actors) {
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActorD> getActors() {
        return actors;
    }

    public void setActors(List<ActorD> actors) {
        this.actors = actors;
    }

}
