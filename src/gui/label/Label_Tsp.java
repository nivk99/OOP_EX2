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
// It's a class of button Tsp
public class Label_Tsp  extends JFrame implements ActionListener {
    JTextField tf;
    JLabel l;
    JButton b;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo;

    public Label_Tsp(DirectedWeightedGraphAlgorithms algo) {
        this.algo = algo;
        this.graph = this.algo.getGraph();
        tf = new JTextField();
        tf.setBounds(50, 50, 150, 20);
        l = new JLabel();
        l.setBounds(30, 100, 350, 40);
        b = new JButton("search");
        b.setBounds(50, 150, 95, 30);
        b.addActionListener(this);
        l.setText("Insert keys.  Type: kay,kay,kay... e.g: 5,3,8");
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
            for (int i = 0; i < temp.length; i++) {
                list.add(new Node_Data(Integer.parseInt(temp[i])));
            }
            list=this.algo.tsp(list);
            l.setText("The new list after tsp is:");
            String massage=""+list.get(0).getKey();
            for (int i = 1; i < temp.length; i++) {
                massage+=" -->"+list.get(i).getKey();
            }
            JOptionPane.showMessageDialog(this, massage, "TSP", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

