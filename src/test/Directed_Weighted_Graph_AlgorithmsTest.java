package test;

import api.*;
import gui.Jframe;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_Graph_AlgorithmsTest {

    private DirectedWeightedGraph grapa;
    private DirectedWeightedGraphAlgorithms algo;
    public Directed_Weighted_Graph_AlgorithmsTest(){

        algo=new Directed_Weighted_Graph_Algorithms();
        grapa=new Directed_Weighted_Graph();

        // 0...7
        grapa.addNode(new Node_Data(0,35.19589389346247,32.10152879327731,0.0));
        grapa.addNode(new Node_Data(1,35.20319591121872,32.10318254621849,0.0));
        grapa.addNode(new Node_Data(2,35.20752617756255,32.1025646605042,0.0));
        grapa.addNode(new Node_Data(3,35.21007339305892,32.10107446554622,0.0));
        grapa.addNode(new Node_Data(4,35.21310882485876,32.104636394957986,0.0));
        grapa.addNode(new Node_Data(5,35.212111165456015,32.106235628571426,0.0));
        grapa.addNode(new Node_Data(6,35.20797194027441,32.104854472268904,0.0));
        grapa.addNode(new Node_Data(7,35.205764353510894,32.106326494117646,0.0));

        // 0 <-> 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6 <-> 7 <-> 0

        grapa.connect(0,1,5);
        grapa.connect(1,0,10);

        grapa.connect(1,2,3);
        grapa.connect(2,1,2);

        grapa.connect(2,3,10);
        grapa.connect(3,2,10);

        grapa.connect(3,4,7);
        grapa.connect(4,3,1);

        grapa.connect(4,5,3);
        grapa.connect(5,4,8);

        grapa.connect(5,6,0);
        grapa.connect(6,5,11);

        grapa.connect(6,7,5);
        grapa.connect(7,6,2);

        grapa.connect(7,0,4);
        grapa.connect(0,7,5);

//        algo.init(grapa);
//        algo.save("new test.json");


    }
//This is a method of testing
    @Test
    void init() {
        for (int i = 0; i < 8; i++) {
            assertNull(algo.getGraph().getNode(i));
        }

        algo.init(grapa);
        for (int i = 0; i < 8; i++) {
            assertNotNull(algo.getGraph().getNode(i));
        }
    }
//This is a method of testing
    @Test
    void getGraph() {
        algo.init(grapa);
        assertEquals(algo.getGraph(),grapa);
        assertEquals(algo.getGraph().nodeSize(),grapa.nodeSize());
        assertEquals(algo.getGraph().edgeSize(),grapa.edgeSize());;
    }
//This is a method of testing
    @Test
    void copy() {
        algo.init(grapa);
        assertEquals(algo.getGraph(),grapa);
       grapa= algo.copy();
       assertNotEquals(algo.getGraph(),grapa);
    }
//This is a method of testing
    @Test
    void isConnected()
    {
        algo.init(grapa);
        assertTrue(algo.isConnected());
        algo.getGraph().removeNode(0);
        algo.getGraph().removeNode(2);
        assertFalse(algo.isConnected());
        grapa.addNode(new Node_Data(0,35.19589389346247,32.10152879327731,0.0));
        grapa.addNode(new Node_Data(2,35.20752617756255,32.1025646605042,0.0));

        grapa.connect(0,1,5);
        grapa.connect(1,0,10);

        grapa.connect(1,2,3);
        grapa.connect(2,1,2);

        grapa.connect(2,3,10);
        grapa.connect(3,2,10);

        grapa.connect(7,0,4);
        grapa.connect(0,7,5);

        assertTrue(algo.isConnected());


    }
//This is a method of testing
    @Test
    void shortestPathDist() {
        assertEquals(-1,algo.shortestPathDist(1,4));//null
        algo.init(grapa);
        assertEquals(20,algo.shortestPathDist(1,4));
        assertEquals(7,algo.shortestPathDist(0,6));
        assertEquals(0,algo.shortestPathDist(5,5));//src==dest
        assertEquals(-1,algo.shortestPathDist(2222,222));//null
    }
//This is a method of testing
    @Test
    void shortestPath() {
        List<NodeData> list =new LinkedList<>();
        list=algo.shortestPath(1,4);
        try {
            assertNull(list.size());
        }
        catch (NullPointerException e){
            System.err.println("shortestPath - this is null!!!");
        }
        algo.init(grapa);
        list=algo.shortestPath(1,4);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i+1,list.get(i).getKey());

        }
    }
//This is a method of testing
    @Test
    void center() {
        assertNull(algo.center());
        algo.init(grapa);
        assertEquals(4,algo.center().getKey());

    }
//This is a method of testing
    @Test
    void tsp() {
        List<NodeData> list =new LinkedList<>();
        algo.init(grapa);
        list.add(new Node_Data(0));
        list.add(new Node_Data(5));
        list.add(new Node_Data(3));
       list= algo.tsp(list);
        assertEquals(3,list.get(0).getKey());
        assertEquals(5,list.get(1).getKey());
        assertEquals(0,list.get(2).getKey());
    }
//This is a method of testing
    @Test
    void save() {
        assertFalse(algo.save("test.json"));
        algo.init(grapa);
        assertTrue(algo.save("test.json"));
        assertTrue(algo.save("test"));
        assertFalse(algo.save(""));
        assertFalse(algo.save(null));
    }
//This is a method of testing
    @Test
    void load() {
        algo.init(grapa);
        assertTrue(algo.save("test.json"));
        assertFalse(algo.save(null));
        assertFalse(algo.save(""));



    }

}