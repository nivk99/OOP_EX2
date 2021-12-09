package gui.label;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import gui.Jframe;

import javax.swing.*;
import java.awt.event.*;
// It's a class of button RemoveNode
public class Label_RemoveNode extends JFrame implements ActionListener{
    JTextField tf; JLabel l; JButton b;
    DirectedWeightedGraph graph;
   DirectedWeightedGraphAlgorithms algo;
    public Label_RemoveNode(DirectedWeightedGraphAlgorithms algo){
        this.algo=algo;
        this.graph=this.algo.getGraph();
        tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        l=new JLabel();
        l.setBounds(50,100, 300,20);
        b=new JButton("Remove");
        b.setBounds(50,150,95,30);
        b.addActionListener(this);
        l.setText("Insert Node key. e.g: 5");
        add(b);add(tf);add(l);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String host=tf.getText();
            this.graph.removeNode(Integer.parseInt(host));
            l.setText("Node Number: "+host+" Deleted");
            Jframe k=new Jframe(this.algo);
            k.setVisible(true);
        }catch(Exception ex){System.out.println(ex);}
    }
   }