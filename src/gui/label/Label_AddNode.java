package gui.label;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import api.Node_Data;
import gui.Jframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// It's a class of button AddNode
public class Label_AddNode extends JFrame implements ActionListener {
    JTextField tf; JLabel l; JButton b;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo;
    public Label_AddNode( DirectedWeightedGraphAlgorithms algo){
        this.algo=algo;
        this.graph=this.algo.getGraph();
        tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        l=new JLabel();
        l.setBounds(50,100, 300,20);
        b=new JButton("Add");
        b.setBounds(50,150,95,30);
        b.addActionListener(this);
        l.setText("Insert Node key&x&y. Type key,x,y. e.g: 1.1,2.2");
        add(b);add(tf);add(l);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String host=tf.getText();
            String[] temp = host.split(",");
            NodeData node =new Node_Data(Integer.parseInt(temp[0]),Double.parseDouble(temp[1]),Double.parseDouble(temp[2]));
            this.graph.addNode(node);
            l.setText("Node Number: "+host+" Add");
            Jframe k=new Jframe(this.algo);
            k.setVisible(true);
        }catch(Exception ex){System.out.println(ex);}
    }
}
