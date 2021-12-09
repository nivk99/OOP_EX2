package gui.label;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import gui.Jframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// It's a class of button RemoveEdge
public class Label_RemoveEdge extends JFrame implements ActionListener {
    JTextField tf; JLabel l; JButton b;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo;
    public  Label_RemoveEdge( DirectedWeightedGraphAlgorithms algo){
        this.algo=algo;
        this.graph=this.algo.getGraph();
        tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        l=new JLabel();
        l.setBounds(50,100, 300,20);
        b=new JButton("Remove");
        b.setBounds(50,150,95,30);
        b.addActionListener(this);
        l.setText("Insert 2 keys: Type src,dest. e.g: 5,3");
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
            this.graph.removeEdge(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
            l.setText("Edge Number: "+host+" Deleted");
            Jframe k=new Jframe(this.algo);
            k.setVisible(true);
        }catch(Exception ex){System.out.println(ex);}
    }







}
