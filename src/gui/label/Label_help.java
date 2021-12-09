package gui.label;

import api.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
// It's a class of button help
public class Label_help {
        private JFrame panel;
    public void explanation() {
            String massage=
                    "What can be seen:\n" +
                            "You can see that a graph is drawn with directions and weights. Write its key to each vertex and in addition write the source, destination and weight for each edges.\n" +
                            "In the title of the page you can see the number of vertices, the number of edges, whether the graph is  Connected and the center of the graph.\n" +
                            "Buttons can also be seen on the left side\n"+
                            "\n"+
                    "The drawing has 3 buttons:\n" +
                            "\n"+
                            "1.File - There are 3 buttons:\n" +
                            "A) Load - Opens a menu and you need to select the file and then click on open.\n" +
                            "B) Save - Opens a menu and you need to select the location to save the file. Then you need to type the file name (without the word .json) and click save.\n" +
                            "C) Exit - to exit\n" +
                            "\n"+
                            "2.Edit -There are 7 buttons:\n" +
                            "A) Remove Node - Deletes a vertex. To delete, you need to type the vertex key.\n" +
                            "B) Remove Edge - Deletes the edge. To delete you need to type the vertex of the source key and the vertex of the target key.\n For example 5,4 - delete from source 5 to destination 4.\n" +
                            "C) Connect 2 Nodes - Connects between 2 vertices. To connect, you need to type the vertex of the source key and the vertex of the target key and the weight.\n For example 1,5,4 - add from source 1 to destination 5 in weight 4.\n" +
                            "D) Add Node - Inserts a vertex. To add you need to type a new key that does not exist.\n" +
                            "E) Tsp List - This method Computes a list of consecutive nodes which go over all the nodes in cities.\n" +
                            "    the sum of the weights of all the consecutive (pairs) of nodes (directed) is the \"cost\" of the solution .\n" +
                            "To use this you need to enter keys of vertices. kay, kay, kay ... \nFor example: for 3 and 7 and 8, write 8, 3,7"+"\n"+
                            "F) Shortest path sum - Finds you the smallest amount of the route.To search you need to type in a source vertex key and a target vertex key\n" +"For example for source 5 and destination 3 you have to type 5,3.\n"+
                            "G) Shortest Path List - This method computes the shortest path between src to dest - as an ordered List of nodes: \n" +
                            "      src -> n1 -> n2 -> ... dest\n" +
                            "To use this you only need to enter 2 keys of vertices. src, dest.\n For example: for source 5 and destination 3 you have to write 5,3 "+"\n"+
                            "\n"+
                            "3.Help - There are 2 buttons:\n" +
                            "A) Vertex information - It is a button which gives information about each vertex.\n"+" To use you have to select one of the vertex keys and then it gives you a window explaining all the information that is on the vertex. "+"\n"+
                            "It is very helpful to know the indegree and outdegree of each vertex!!!.\n"+
                            "B) explanation - Opens a window to explain the drawing ";
            panel = new JFrame();
            JOptionPane.showMessageDialog(panel,
                    massage,
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE);
        }



        //label Explanationvertices;=
    public void Explanationvertices(DirectedWeightedGraphAlgorithms algo) {
        Iterator<NodeData> iter=algo.getGraph().nodeIter();
        String kay="";
        if(iter.hasNext()) {
            kay +=iter.next().getKey();
            while (iter.hasNext()){
                kay+=","+iter.next().getKey();
            }

        }
        String[] kays= kay.split(",");
        help_Explanationvertices((String) JOptionPane.showInputDialog(
                null,
                "Choose a vertex number: ",
                "Vertex information",
                JOptionPane.QUESTION_MESSAGE,
                null,
                kays,
                kays[0]
        ),algo.getGraph());
    }


    public void help_Explanationvertices(String k, DirectedWeightedGraph algo) {
        int kay=0;


        try {
            kay =Integer.parseInt(k);
        }
        catch (Exception e){
            return;
        }
        Directed_Weighted_Graph_Algorithms g=new Directed_Weighted_Graph_Algorithms();
        g.init(algo);
        HashMap<Integer,EdgeData> in = g.getParents().get(kay);
        String indegree="";
        int i=0;
        for(EdgeData e:in.values()){
            i++;
            indegree+=i+")"+" From: "+e.getSrc()+", To: "+e.getDest()+",  weight: "+e.getWeight()+".\n";

        }



        String outdegree="";
        Iterator<EdgeData> iter =algo.edgeIter(kay);
         i=0;
        while (iter.hasNext()){
            i++;
            EdgeData e=iter.next();
            outdegree+=i+")"+" From: "+e.getSrc()+", To: "+e.getDest()+",  weight: "+e.getWeight()+".\n";
        }



        String massage="";
        massage ="The node number is "+kay+"\n"+
                "His location is " +algo.getNode(kay).getLocation()+"\n"+
                "The indegree is:\n"+indegree+
                "The outdegree is:\n"+outdegree;
        panel = new JFrame();
        JOptionPane.showMessageDialog(panel,
                massage,
                "Number vertex information:"+k,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
