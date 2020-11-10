package ru.croc.art.filmDS;

import javax.xml.bind.annotation.*;

/**
 * структура класса, в котором будут храниться распарсенные и преобразованные данные
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FilmS {
    @XmlAttribute()
    private String title;
    @XmlAttribute
    private String role;

    public FilmS(String title, String role) {
        this.title = title;
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
