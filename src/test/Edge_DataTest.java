package test;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Edge_DataTest {
    private LinkedList<EdgeData> link;

    public Edge_DataTest(){
        Random random=new Random();
        link=new LinkedList<>();
        for (int i = 0; i <10000-1 ; i++) {
            NodeData node1 =new Node_Data(i,random.nextDouble(35,36),random.nextDouble(32,33));
            NodeData node2 =new Node_Data(i+1,random.nextDouble(35,36),random.nextDouble(32,33));
            link.add(new Edge_Data(node1,node2,i/2));
        }
    }

//This is a method of testing
    @Test
    void getSrc() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i,link.get(i).getSrc());
        }


    }
//This is a method of testing
    @Test
    void getDest() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i+1,link.get(i).getDest());
        }
    }
//This is a method of testing
    @Test
    void getWeight() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i/2,link.get(i).getWeight());
        }
    }
//This is a method of testing
    @Test
    void getInfo() {
        for (int i = 0; i <link.size() ; i++) {
            assertNotNull(link.get(i).getInfo());
        }
    }
//This is a method of testing
    @Test
    void setInfo() {
        for (int i = 0; i <link.size() ; i++) {
            link.get(i).setInfo("1");
        }
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(1,Integer.parseInt(link.get(i).getInfo()));
        }
    }
//This is a method of testing
    @Test
    void getTag() {
        for (int i = 0; i <link.size() ; i++) {
            assertNotNull(link.get(i).getTag());
        }
    }
//This is a method of testing
    @Test
    void setTag() {
        for (int i = 0; i <link.size() ; i++) {
            link.get(i).setTag(1);
        }
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(1,link.get(i).getTag());
        }
    }

}