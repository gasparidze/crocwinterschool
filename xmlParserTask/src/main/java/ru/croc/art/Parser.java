package ru.croc.art;


import ru.croc.art.actorDS.ActorS;
import ru.croc.art.actorDS.ActorD;
import ru.croc.art.filmDS.FilmS;
import ru.croc.art.filmDS.FilmD;
import ru.croc.art.xmlDS.XmlS;
import ru.croc.art.xmlDS.XmlD;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * класс читает данные из XML-файла и парсит данные
 */
public class Parser {

    public Parser() throws JAXBException {
    }

    /**
     * метод pars парсит данные и возвращает класс, в котором они хранятся
     * @param str - путь к исходному файлу с данными
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public XmlS pars(String str) throws JAXBException, FileNotFoundException {
        //извлечение данных
        JAXBContext context = JAXBContext.newInstance(XmlD.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlD xmlD = (XmlD) unmarshaller.unmarshal(new File(str));

        List<ActorD> all = new ArrayList<>();
        for (FilmD filmD : xmlD.getFilms()) {
            for (ActorD actor : filmD.getActors()) {
                all.add(actor);
            }
        }
        //1-й String - имя актера,2-й String - фильм,3-й String - роль
        Map<String, Map<String, String>> stringMap = new LinkedHashMap<>();
        Map<String, String> filmRole;
        //множество, в которое добавляются уже просмотренные актеры
        Set<String> actorRepeated = new HashSet<>();
        //просматриваю всех актеров
        for (ActorD actor : all) {
            //если такого актера нет, то добавляю его в список просмотренных
            if (!actorRepeated.contains(actor.getName())) {
                actorRepeated.add(actor.getName());
                //создаю объект Фильм-Роль
                filmRole = new HashMap<>();
                //просматриваю каждый фильм
                for (FilmD filmD : xmlD.getFilms()) {
                    //и актеров данного фильма с их ролями
                    for (ActorD actor1 : filmD.getActors()) {
                        /*т.к. у объектов "actor" и "actor1" с одинаковыми именами будут разные роли,
                        то проверяем: если у 2-х объектов одинаковые имена, то смотрим, были ли добавлены
                        какаие-либо фильмы. Если не было, то добавляем роль объекта "actor",если были уже фильмы
                        то добавляем новую пару ключ-значение - Фильм-Роль, роль уже будет взята у объекта "actor1"*/
                        if (actor.getName().equals(actor1.getName()) && filmRole.isEmpty()) {
                            filmRole.put(filmD.getTitle(), actor.getRole());
                        } else if (actor.getName().equals(actor1.getName()) && !filmRole.isEmpty()) {
                            filmRole.put(filmD.getTitle(), actor1.getRole());
                        }
                    }

                }
            } else break;
            //если данного актера еще не добавляли,то добавляем (Имя и мапу его фильмов с соответствующими ролями)
            if (!stringMap.containsKey(actor)) {
                //все необходимые данные хранятся в stringMap
                stringMap.put(actor.getName(), filmRole);
            }
        }
        //добавляю данные из stringMap в соответствующие заранее подготвленные классы,
        // которые необходимы нам для дальнейшей сериализации
        XmlS xmlS = new XmlS();
        List<ActorS> actorDas = new ArrayList<>();
        List<ActorS> list = new ArrayList<>();
        for (String s1 : stringMap.keySet()) {
            List<FilmS> filmS = new ArrayList<>();
            for (String s2 : stringMap.get(s1).keySet()) {
                filmS.add(new FilmS(s2, stringMap.get(s1).get(s2)));
            }
            for (ActorD actorD : all) {
                if (actorD.getName() == s1) {
                    actorDas.add(new ActorS(s1, actorD.getAge(), filmS));
                }
            }
        }

        list.addAll(actorDas);
        xmlS.setActors(list);
        return xmlS;
    }

    /**
     *  метод, который преобразует данные файла в строку
     * @param str путь к файлу, который хотим проеобразовать в StringBilder
     * @return
     * @throws FileNotFoundException
     */
    public StringBuilder toStr(String str) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(str))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
