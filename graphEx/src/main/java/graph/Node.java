package graph;

import java.util.*;

public class Node {
    /*** окраска вершин: были/не были*/
    private int color = 0;
    /**
     * идентификатор
     */
    private int id;
    /**
     * дополнительные, нужные в ходе работы, коллекции
     */
    private Set<Integer> firstAdditionalSet;
    private Set<Integer> secondAdditionalSet = new HashSet<Integer>();
    private List<Set<Integer>> comp = new ArrayList<Set<Integer>>();
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
     * метод findComp ищет вершины компонент связности графа и возвращает список
     *
     * @param node
     * @return
     */
    public List<Set<Integer>> findComp(List<Node> node) {
        for (int i = 0; i < node.size(); i++) {
            firstAdditionalSet = new HashSet<Integer>();
            secondAdditionalSet = dfs(node.get(i));
            if (secondAdditionalSet.size() > 1)
                comp.add(secondAdditionalSet);
        }
        return comp;
    }

    /**
     * метод dfs обходит граф в глубину
     *
     * @param v
     * @return
     */
    public Set<Integer> dfs(Node v) {
        firstAdditionalSet.add(v.id);
        if (v.color != 1) {
            v.color = 1;
            for (Node node : v.getLinks()) {
                if (node.color != 1) {
                    dfs(node);
                }
            }
        }
        return firstAdditionalSet;
    }

    /**
     * метод getLinks возвращает множество соседей определенной вершины
     *
     * @return
     */
    public Set<Node> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return getLinks().toString();
    }

}
