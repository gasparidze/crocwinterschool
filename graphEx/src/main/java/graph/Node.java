package graph;

import java.util.*;

/**
 * Класс вершины
 */
public class Node {
    /*** окраска вершин: были/не были*/
    private int color = 0;
    /**
     * идентификатор
     */
    private final int id;

    /***соседи(связи)*/
    private Set<Node> links;

    /***
     * конструктор
     * @param id
     */
    public Node(int id) {
        links = new HashSet<Node>();
        this.id = id;
    }

    /**
     * метод addLinks добавляет связи к опредленной вершине vert
     * @param vert
     */
    public void addLinks(Node vert){
        this.links.add(vert);
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    /**
     * метод getLinks возвращает множество соседей определенной вершины
     *
     * @return
     */
    public Set<Node> getLinks() {
        return links;
    }

}
