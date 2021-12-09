package test;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_GraphTest {
   private DirectedWeightedGraph grapa;
    private LinkedList<NodeData> link_node;
    private LinkedList<EdgeData> link_edge;

    public Directed_Weighted_GraphTest(){
        Random random=new Random();
         grapa =new Directed_Weighted_Graph();
        link_node=new LinkedList<>();
        link_edge=new LinkedList<>();
        for (int i = 0; i <1000 ; i++) {
            NodeData node =new Node_Data(i,random.nextDouble(35,36),random.nextDouble(32,33));
            grapa.addNode(node);
            link_node.add(node);
        }

        int src=0,dest=0;
        double w=0;
        for (int i = 0; i <1000-10 ; i++) {
            //1
            src=i;
            dest=i+1;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+1;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //2
            src=i;
            dest=i+2;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+2;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //3
            src=i;
            dest=i+3;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+3;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //4
            src=i;
            dest=i+4;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+4;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //5
            src=i;
            dest=i+5;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+5;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //6
            src=i;
            dest=i+6;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+6;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //7
            src=i;
            dest=i+7;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+7;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //8
            src=i;
            dest=i+8;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+8;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //9
            src=i;
            dest=i+9;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+9;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //10
            src=i;
            dest=i+10;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=i+10;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

        }


        for (int i=990, j=0;  i<1000 ; i++ ,j++) {
            //1
            src=i;
            dest=j+1;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+1;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //2
            src=i;
            dest=j+2;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+2;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //3
            src=i;
            dest=j+3;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+3;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //4
            src=i;
            dest=j+4;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+4;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //5
            src=i;
            dest=j+5;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+5;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //6
            src=i;
            dest=j+6;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+6;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //7
            src=i;
            dest=j+7;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+7;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //8
            src=i;
            dest=j+8;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+8;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //9
            src=i;
            dest=j+9;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+9;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));

            //10
            src=i;
            dest=j+10;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));
            src=j+10;
            dest=i;
            w=random.nextDouble(1,2);
            grapa.connect(src,dest,w);
            link_edge.add(new Edge_Data(new Node_Data(src),new Node_Data(dest),w));




        }

    }
///This is a method of testing
    @Test
    void getNode() {
        for (int i = 0; i <link_node.size() ; i++) {
            assertEquals(link_node.get(i).getKey(),grapa.getNode(i).getKey());
        }


    }
//This is a method of testing
    @Test
    void getEdge() {
        for (int i = 0; i <link_edge.size() ; i++) {
            int src =link_edge.get(i).getSrc();
            int dest=link_edge.get(i).getDest();
            assertEquals(link_edge.get(i).getSrc(),grapa.getEdge(src,dest).getSrc());
            assertEquals(link_edge.get(i).getDest(),grapa.getEdge(src,dest).getDest());
            assertEquals(link_edge.get(i).getWeight(),grapa.getEdge(src,dest).getWeight());
        }


    }
//This is a method of testing
    @Test
    void nodeSize() {
            assertEquals(link_node.size(),grapa.nodeSize());

    }
//This is a method of testing
    @Test
    void edgeSize() {
            assertEquals(link_edge.size(),grapa.edgeSize());

    }
//This is a method of testing
    @Test
    void getMC() {
        int mc =grapa.getMC();
        assertEquals(mc,grapa.getMC());
        NodeData node = grapa.removeNode(0);
        assertNotEquals(mc,grapa.getMC());
        grapa.addNode(node);
        assertEquals(mc+2,grapa.getMC());
    }

//This is a method of testing
    @Test
    void nodeIter() {
        Iterator<NodeData> iter_list=link_node.iterator();
        Iterator<NodeData> iter_g=grapa.nodeIter();
        while (iter_g.hasNext()){
            assertEquals(iter_list.next(),iter_g.next());
        }

    }
//This is a method of testing
    @Test
    void edgeIter() {
        Iterator<EdgeData> iter_g=grapa.edgeIter();
        while (iter_g.hasNext()){
            assertTrue(link_edge.contains(iter_g.next()));
        }

    }
//This is a method of testing
    @Test
    void testEdgeIter() {

        Iterator<EdgeData> iter_g=grapa.edgeIter(0);
        while (iter_g.hasNext()){
            assertNotNull(iter_g.next());
        }


    }
//This is a method of testing
    @Test
    void removeNode() {
        assertEquals(link_node.size(),grapa.nodeSize());
        NodeData node =grapa.removeNode(0);
        assertNotEquals(link_node.size(),grapa.nodeSize());
        grapa.addNode(node);
        assertEquals(link_node.size(),grapa.nodeSize());

    }
//This is a method of testing
    @Test
    void removeEdge() {
        assertEquals(link_edge.size(),grapa.edgeSize());
        EdgeData e= link_edge.get(0);
        grapa.removeEdge(e.getSrc(),e.getDest());
       assertEquals(link_node.size(),grapa.nodeSize());
        grapa.connect(e.getSrc(),e.getDest(),e.getWeight());
        assertEquals(link_edge.size(),grapa.edgeSize());
        for (int i = 0; i <link_edge.size(); i++) {
           EdgeData a= grapa.removeEdge(link_edge.get(i).getSrc(),link_edge.get(i).getDest());
        }
       assertEquals(0,grapa.edgeSize());
        for (int i = 0; i <link_edge.size(); i++) {
            grapa.connect(link_edge.get(i).getSrc(),link_edge.get(i).getDest(),link_edge.get(i).getWeight());
        }
       assertEquals(link_edge.size(),grapa.edgeSize());

    }

//This is a method of testing
    @Test
    void addNode() {
            assertEquals(link_node.size(),grapa.nodeSize());

        int size = grapa.nodeSize();
        for (int i = 0; i < 100; i++) {
            grapa.addNode(new Node_Data(1000+i));

        }
        assertNotEquals(link_node.size(),grapa.nodeSize());
        assertEquals(size+100,grapa.nodeSize());
    }
//This is a method of testing
    @Test
    void connect() {
        Random random=new Random();
        assertEquals(link_edge.size(),grapa.edgeSize());
        int size = grapa.edgeSize();
            for (int j = 0; j <101; j++) {
                int src=j;
                int dest=j+1;
                double w=random.nextDouble(1,2);
                grapa.connect(src,dest,w);
            }

    }



}