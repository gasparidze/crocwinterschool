package ru.croc.art.xmlDS;

import ru.croc.art.actorDS.ActorS;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
/**
 * структура класса, в котором будут храниться распарсенные и преобразованные данные
 *
 */
@XmlRootElement(name = "actors")
public class XmlS {
    private List<ActorS> actors  = new ArrayList<>();

    public List<ActorS> getActors() {
        return actors;
    }
    @XmlElement(name = "actor")
    public void setActors(List<ActorS> actors) {
        this.actors = actors;
    }

}