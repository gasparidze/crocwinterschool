package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * класс поиска компонент связности
 */
public class ComponentFinder {
    /**
     * список вершин
     */
    private List<Node> nodes;
    /**
     * дополнительные, необходимые в ходе работы, коллекции
     */
    private Set<Integer> firstAdditionalSet;
    private Set<Integer> secondAdditionalSet = new HashSet<Integer>();
    private List<Set<Integer>> comp = new ArrayList<Set<Integer>>();

    /**
     * конструктор, принимающий список вершин
     * @param nodes
     */
    public ComponentFinder(List<Node> nodes) {
        this.nodes = nodes;
    }


    /**
     * метод findComp ищет вершины компонент связности графа и возвращает список
     * @return nodes
     */
    public List<Set<Integer>> find() {
        for (int i = 0; i < nodes.size(); i++) {
            firstAdditionalSet = new HashSet<Integer>();
            secondAdditionalSet = dfs(nodes.get(i));
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
        firstAdditionalSet.add(v.getId());
        if (v.getColor() != 1) {
            v.setColor(1);
            for (Node node : v.getLinks()) {
                if (node.getColor() != 1) {
                    dfs(node);
                }
            }
        }
        return firstAdditionalSet;
    }
}
