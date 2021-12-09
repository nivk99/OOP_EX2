package gui.label;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import api.Node_Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
// It's a class of button ShortestPath
public class Label_ShortestPath  extends JFrame implements ActionListener {
    JTextField tf;
    JLabel l;
    JButton b;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo;

    public Label_ShortestPath(DirectedWeightedGraphAlgorithms algo) {
        this.algo = algo;
        this.graph = this.algo.getGraph();
        tf = new JTextField();
        tf.setBounds(50, 50, 150, 20);
        l = new JLabel();
        l.setBounds(30, 100, 350, 40);
        b = new JButton("search");
        b.setBounds(50, 150, 95, 30);
        b.addActionListener(this);
        l.setText("Insert 2 keys.Type: src,dest. e.g: 5,3");
        add(b);
        add(tf);
        add(l);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String host = tf.getText();
            String[] temp = host.split(",");
            List<NodeData> list =new LinkedList<>();
            int src =Integer.parseInt(temp[0]);
            int dest=Integer.parseInt(temp[1]);
            list=this.algo.shortestPath(src,dest);
            l.setText("The new list after Shortest Path is:");
            String massage=""+list.get(0).getKey();
            for (int i = 1; i < list.size(); i++) {
                massage+=" -->"+list.get(i).getKey();
            }
            JOptionPane.showMessageDialog(this, massage, "Shortest Path", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
