import gui.Jframe;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import java.io.FileNotFoundException;
import api.*;
import gui.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws FileNotFoundException {
        DirectedWeightedGraph ans = getGrapgAlgo(json_file).getGraph();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws FileNotFoundException {
        DirectedWeightedGraphAlgorithms ans = new Directed_Weighted_Graph_Algorithms();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) throws FileNotFoundException {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        Jframe k=new Jframe(alg);
        k.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length >=1) {
            runGUI(args[0]);
        }
    }
}