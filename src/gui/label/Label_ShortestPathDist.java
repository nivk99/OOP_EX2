package gui.label;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// It's a class of button ShortestPathDist
public class Label_ShortestPathDist extends JFrame implements ActionListener {
    JTextField tf; JLabel l; JButton b;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo;
    public  Label_ShortestPathDist(DirectedWeightedGraphAlgorithms algo){
        this.algo=algo;
        this.graph=this.algo.getGraph();
        tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        l=new JLabel();
        l.setBounds(50,100, 300,20);
        b=new JButton("search");
        b.setBounds(50,150,95,30);
        b.addActionListener(this);
        l.setText("Insert 2 keys.Type: src,dest. e.g: 5,3");
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
            double sum=this.algo.shortestPathDist(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
            l.setText("The shortest path sum from:"+Integer.parseInt(temp[0])+" to:" + Integer.parseInt(temp[1])+" is:"+sum);
        }catch(Exception ex){System.out.println(ex);}
    }
}
