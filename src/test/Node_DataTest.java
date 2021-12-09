package test;

import api.Geo_Location;
import api.NodeData;
import api.Node_Data;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Node_DataTest {
    private LinkedList<NodeData> link;
//This is a method of testing
    public Node_DataTest(){
        Random random=new Random();
        link=new LinkedList<>();
        for (int i = 0; i <10000 ; i++) {
            NodeData node =new Node_Data(i,random.nextDouble(35,36),random.nextDouble(32,33));
           link.add(node);
        }
    }
//This is a method of testing
    @Test
    void getKey() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i,link.get(i).getKey());
        }
    }
//This is a method of testing
    @Test
    void getLocation() {
        for (int i = 0; i <link.size() ; i++) {
            assertNotNull(link.get(i).getLocation());
        }
    }
//This is a method of testing
    @Test
    void setLocation() {
        for (int i = 0; i <link.size() ; i++) {
           link.get(i).setLocation(new Geo_Location(0,0,0));
        }
        for (int i = 0; i <link.size() ; i++) {
          assertEquals(0,link.get(i).getLocation().x());
            assertEquals(0,link.get(i).getLocation().y());
            assertEquals(0,link.get(i).getLocation().z());
        }
    }
//This is a method of testing
    @Test
    void getWeight() {

        for (int i = 0; i <link.size() ; i++) {
            assertNotNull(link.get(i).getWeight());
        }

    }
//This is a method of testing
    @Test
    void setWeight() {

        for (int i = 0; i <link.size() ; i++) {
           link.get(i).setWeight(0);
        }
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(0, link.get(i).getWeight());
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