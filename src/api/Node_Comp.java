package api;

import java.util.Comparator;

/**
 * This class helps sort points by implementing Comparator
 */

public class Node_Comp implements Comparator<NodeData> {
    /**
     * This method gets 2 points and helps to sort the points by their weight.
     *This method gets 2 points and helps to sort the points by their weight.
     * When v1 is greater then returns 1.
     * When v1 is smaller returns 1-.
     * When v1 equals v2 returns 0.
     * @param v1
     * @param v2
     * @return 1 or -1 or 0
     */
    @Override
    public int compare(NodeData v1, NodeData v2) {
        if (v1==null)
            return -1;
        if (v2==null)
            return 1;
        if(v1.getWeight()<v2.getWeight())
            return -1;
        if(v1.getWeight()>v2.getWeight())
            return 1;
        return 0;

    }
}
