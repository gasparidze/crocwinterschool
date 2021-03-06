package graphTest;

import graph.ComponentFinder;
import graph.Node;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class NodeTest {
    @Test
    public void testNode() {
        //Инициализация вершин графа
        Node vert1 = new Node(1);
        Node vert2 = new Node(2);
        Node vert3 = new Node(3);
        Node vert4 = new Node(4);
        Node vert5 = new Node(5);
        Node vert6 = new Node(6);
        Node vert7 = new Node(7);
        //инициализация связей
        vert1.addLinks(vert2);
        vert2.addLinks(vert3);
        vert3.addLinks(vert4);
        vert3.addLinks(vert5);
        vert6.addLinks(vert7);
        //инициализация графа
        List<Node> graph = new ArrayList<Node>();
        graph.add(vert1);
        graph.add(vert2);
        graph.add(vert3);
        graph.add(vert4);
        graph.add(vert5);
        graph.add(vert6);
        graph.add(vert7);
        //инициализация класса для поиска компонент связности графа
        ComponentFinder componentFinder = new ComponentFinder(graph);
        //дополнительный List для проверки списка компонент связности
        List<Set<Integer>> expectedList = new ArrayList<Set<Integer>>();
        expectedList.add(new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }});
        expectedList.add(new HashSet<Integer>() {{
            add(6);
            add(7);
        }});

        //тест на количество компонент связности
        Assertions.assertEquals(2, componentFinder.find().size());
        //тест на список вершин, входящих в компоненты связности
        Assertions.assertEquals(expectedList, componentFinder.find());

    }
}
