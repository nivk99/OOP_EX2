package api;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * In this class you can find methods that work on the graph. In this class you can see a number of different algorithms that help this class.
 * In the department you can find a data structure of hash table.
 * The fields of the class are:
 * A. hash table - of all children.
 * B. hash table - of all parents.
 * third. hash table - of all the children (of the inverted graph).
 * D. hash table - of all parents (of the inverted graph)
 */

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph _graph;
    private HashMap<Integer, HashMap<Integer, EdgeData>> _graph_childes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> _graph_parents;
    private HashMap<Integer, HashMap<Integer, EdgeData>> _Inverse_graph_childes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> _Inverse_graph_parents;

    public Directed_Weighted_Graph_Algorithms() {
        this._graph = new Directed_Weighted_Graph();
        this._graph_childes = new HashMap<>();
        this._graph_parents = new HashMap<>();
        this._Inverse_graph_childes = new HashMap<>();
        this._Inverse_graph_parents = new HashMap<>();
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getParents() {
        return this._graph_parents;
    }

    /**
     * This method Inits the graph on which this set of algorithms operates on. The method simply copies all vertices and edges into a data structure.
     * It does the same on the reverse graph
     * @param g The data structure of the graph contains everything
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this._graph = g;
        this._graph_childes = new HashMap<>();
        this._graph_parents = new HashMap<>();
        this._Inverse_graph_childes = new HashMap<>();
        this._Inverse_graph_parents = new HashMap<>();
        Iterator<NodeData> itr = _graph.nodeIter();
        while (itr.hasNext()) {
            NodeData node = itr.next();
            HashMap<Integer, EdgeData> child = new HashMap<>();
            HashMap<Integer, EdgeData> parent = new HashMap<>();
            this._graph_childes.put(node.getKey(), child);
            this._graph_parents.put(node.getKey(), parent);
            this._Inverse_graph_childes.put(node.getKey(), parent);
            this._Inverse_graph_parents.put(node.getKey(), child);
        }
        Iterator<NodeData> iterator = _graph.nodeIter();
        while (iterator.hasNext()) {
            NodeData node_itr = iterator.next();
            Iterator<EdgeData> itr_edge = _graph.edgeIter(node_itr.getKey());
            while (itr_edge.hasNext()) {
                EdgeData edge = itr_edge.next();
                this._graph_childes.get(node_itr.getKey()).put(edge.getDest(), edge);
                this._graph_parents.get(edge.getDest()).put(edge.getSrc(), edge);
                this._Inverse_graph_childes.get(edge.getDest()).put(edge.getSrc(), edge);
                this._Inverse_graph_parents.get(node_itr.getKey()).put(edge.getDest(), edge);

            }


        }
    }

    /**
     * @return This method returns the underlying graph of which this class works.
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this._graph;
    }

    /**
     *  This method computes a deep copy of this weighted graph.
     * The method simply creates a new copy
     */
    @Override
    public DirectedWeightedGraph copy() {
        return new Directed_Weighted_Graph(this._graph);
    }


    /**
     *This method Returns true if and only if there is a valid path from each node to each.
     *The method goes over the whole graph The Depth-first search method and marks each vertex when visiting it. It then checks for all the vertebrae whether they have been visited. If not then returns false.
     * Plus it does the process again but on the reverse graph. And also there checks for each vertex whether mud has been visited
     * @return is connected or not
     */
    @Override
    public boolean isConnected() {
        Iterator<NodeData> itr = _graph.nodeIter();
        int kay = 0;
        while (itr.hasNext()) {
            NodeData node = itr.next();
            node.setTag(0);
            kay = node.getKey();
            if (this._graph_parents.get(node.getKey()) == null || this._graph_childes.get(node.getKey()) == null) {
                return false;
            }
        }
        Stack<NodeData> stack = new Stack<>();
        stack.add(this._graph.getNode(kay));
        try {
            DfS(stack);
        } catch (Exception e) {
            return false;
        }

        Iterator<NodeData> after_explore = _graph.nodeIter();
        while (after_explore.hasNext()) {
            NodeData node = after_explore.next();
            if (this._graph_parents.get(node.getKey()) == null || this._graph_childes.get(node.getKey()) == null)
                return false;
            if (node.getTag() == 0) {
                return false;
            }
        }

        return InverseDfs();
    }

    private void DfS(Stack<NodeData> stack) {
        while (!stack.isEmpty()) {
            NodeData node = stack.pop();
            node.setTag(1);
            for (Integer kay : this._graph_childes.get(node.getKey()).keySet()) {
                NodeData n = this._graph.getNode(kay);
                if (n.getTag() == 0) {
                    stack.add(n);
                }

            }
        }

    }


    private boolean InverseDfs() {
        Iterator<NodeData> itr = _graph.nodeIter();
        int kay = 0;
        while (itr.hasNext()) {
            NodeData node = itr.next();
            node.setTag(0);
            kay = node.getKey();
        }
        Stack<NodeData> stack = new Stack<>();
        stack.add(this._graph.getNode(kay));
        try {
            DfS_Invers(stack);
        } catch (Exception e) {
            return false;
        }
        Iterator<NodeData> after_explore = _graph.nodeIter();
        while (after_explore.hasNext()) {
            NodeData node = after_explore.next();
            if (this._Inverse_graph_parents.get(node.getKey()) == null || this._Inverse_graph_childes.get(node.getKey()) == null)
                return false;
            if (node.getTag() == 0) {
                return false;
            }
        }
        return true;


    }

    private void DfS_Invers(Stack<NodeData> stack) {
        while (!stack.isEmpty()) {
            NodeData node = stack.pop();
            node.setTag(1);
            for (Integer kay : this._Inverse_graph_childes.get(node.getKey()).keySet()) {
                NodeData n = this._graph.getNode(kay);
                if (n.getTag() == 0) {
                    stack.add(n);
                }

            }
        }

    }


    /**
     * Dijkstra's algorithm
     * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */

    public double shortestPathDist(int src, int dest) {
        if (_graph.getNode(src) == null || _graph.getNode(dest) == null || this._graph.nodeSize() == 0 || this._graph.edgeSize() == 0)
            return -1;
        if (src == dest)
            return 0;
        LinkedList<NodeData> list_node = (LinkedList<NodeData>) shortestPath(src, dest);
        if (list_node == null)
            return -1;
        return TotalWeight(0, list_node, list_node.pop());
    }


    private double TotalWeight(double i, LinkedList<NodeData> list_node, NodeData node) {
        if (list_node.size() == 0)
            return i;
        NodeData pop = list_node.pop();
        i += this._graph.getEdge(node.getKey(), pop.getKey()).getWeight();
        return TotalWeight(i, list_node, pop);
    }

    /**
     * This method computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     *  src - start node
     *  dest - end (target) node
     * Method uses Dijkstra's algorithm The explanation of the algorithm can be seen on
     * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     * @param src  - start node
     * @param dest - end (target) node
     * @return list
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (this._graph.nodeSize() == 0 || this._graph.edgeSize() < 1 || this._graph.getNode(src) == null || this._graph.getNode(dest) == null || src == dest)
            return null;
        List<NodeData> ans = new LinkedList<>();
        PriorityQueue<NodeData> min_heap = new PriorityQueue<>(new Node_Comp());
        NodeData src_node = this._graph.getNode(src);
        NodeData dest_node = this._graph.getNode(dest);
        Iterator<NodeData> itr_node = _graph.nodeIter();
        while (itr_node.hasNext()) {
            NodeData node = itr_node.next();
            node.setWeight(Integer.MAX_VALUE);
            node.setInfo("");
        }
        src_node.setWeight(0);
        min_heap.add(src_node);
        while (!min_heap.isEmpty()) {
            NodeData node_pq = min_heap.poll();
            Iterator<EdgeData> itr_edge = _graph.edgeIter(node_pq.getKey());
            while (itr_edge.hasNext()) {
                EdgeData e = itr_edge.next();
                NodeData node_dest = this._graph.getNode(e.getDest());
                Double weight = node_pq.getWeight() + e.getWeight();
                if (node_dest.getWeight() > weight) {
                    node_dest.setWeight(weight);
                    if (node_pq == null)
                        return null;
                    node_dest.setInfo("" + node_pq.getKey());
                    min_heap.add(node_dest);
                }

            }

        }
        Stack<NodeData> st = new Stack<>();
        st.add(dest_node);
        while (dest_node != src_node) {
            try {
                dest_node = this._graph.getNode(Integer.parseInt(dest_node.getInfo()));
            } catch (Exception e) {
                return null;
            }
            st.add(dest_node);
        }
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
        return ans;
    }

    /**
     * This method Finds the NodeData which minimizes the max distance to all the other nodes.
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     * The method goes through each and every vertex and checks which distance is the shortest
     */
    @Override
    public NodeData center() {
        if (!this.isConnected())
            return null;
        NodeData ans = null;
        double min_w = Double.MAX_VALUE;
        Iterator<NodeData> itr_node1 = _graph.nodeIter();
        while (itr_node1.hasNext()) {
            Iterator<NodeData> itr_node2 = _graph.nodeIter();
            NodeData node1 = itr_node1.next();
            double temp = 0;
            while (itr_node2.hasNext()) {
                NodeData node2 = itr_node2.next();
                if (temp > min_w) {
                    break;
                }
                if (temp < this.shortestPathDist(node1.getKey(), node2.getKey()))
                    temp = this.shortestPathDist(node1.getKey(), node2.getKey());
            }
            if (min_w > temp) {
                min_w = temp;
                ans = node1;
            }

        }
        return ans;
    }


    /**
     * This method Computes a list of consecutive nodes which go over all the nodes in cities.
     *  the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution .
     * The function checks for each vertex where it is best to go. This is a greedy method
     * @param cities list
     * @return The method returns an orderly list of vertices
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData>[] temp = new LinkedList[cities.size()];
        NodeData[] arr = new Node_Data[cities.size()];
        //the new LinkedList for all the temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new LinkedList<>();
            temp[i].add(cities.get(i));//add from node(i) to temp[i]
        }
        for (int i = 0; i < temp.length; i++) {
            List<NodeData> list_cities = new LinkedList<>();
            //the list for all the nodes
            for (int j = 0; j < cities.size(); j++) {
                list_cities.add(cities.get(j));
            }
            //remove the temp[i] from list_cities(i)
            NodeData id = list_cities.get(i);
            list_cities.remove(i);
            double total = 0;//the sum of weights
            for (int j = 0; j < list_cities.size(); j++) {
                double sort = Double.MAX_VALUE;
                int kay = list_cities.get(j).getKey();
                int koko = kay;
                NodeData remov = list_cities.get(j);
                for (int k = 0; k < list_cities.size(); k++) {
                    double w = this.shortestPathDist(id.getKey(), list_cities.get(k).getKey());
                    if (sort > w) {
                        sort = w;
                        kay = list_cities.get(k).getKey();
                        remov = list_cities.get(k);
                    }
                }
                temp[i].add(this._graph.getNode(kay));
                list_cities.remove(remov);
                total += sort;
                id = remov;
                j = -1;
                if (id == null)
                    break;

            }
            cities.get(i).setWeight(total);
            arr[i] = cities.get(i);

        }
        int ind = 0;
        double w = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getWeight());
            double t = arr[i].getWeight();
            if (w > t) {
                w = t;
                ind = i;
            }


        }


        return temp[ind];
    }


    /**
     * The function saves the graph
     *The idea for the code was taken from the Internet
     * @param file - the file name (may include a relative path).
     * @return Whether saved or not
     */

    @Override
    public boolean save(String file) {
        if(file=="")
            return false;
        if(file==null)
            return false;
        if(this._graph.nodeSize()<=0)
            return false;
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonArray nodes = new JsonArray();
        JsonArray edges = new JsonArray();
        Iterator<NodeData> iter_node = this._graph.nodeIter();
        while (iter_node.hasNext()) {
            NodeData node = iter_node.next();
            JsonObject object = new JsonObject();
            if (node.getLocation() != null) {
                String str = node.getLocation().x() + ", " + node.getLocation().y() + ", " + node.getLocation().z();
                object.addProperty("pos", str);
            } else object.addProperty("pos", "null");
            object.addProperty("id", node.getKey());
            nodes.add(object);
            Iterator<EdgeData> iter = this._graph.edgeIter(node.getKey());
            while (iter.hasNext()) {
                EdgeData edge = iter.next();
                JsonObject obj = new JsonObject();
                obj.addProperty("src", edge.getSrc());
                obj.addProperty("w", edge.getWeight());
                obj.addProperty("dest", edge.getDest());
                edges.add(obj);
            }
        }
        json.add("Edges", edges);
        json.add("Nodes", nodes);
        try {
            PrintWriter writer = new PrintWriter(new File(file));
            writer.write(gson.toJson(json));
            writer.flush();
            writer.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return false;

    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     *of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return Whether  loaded or not
     * The idea for the code was taken from the Internet
     */
    @Override
    public boolean load(String file) {
        if (this._graph == null)
            return false;
        if (file==null)
            return false;
        if(file=="")
            return false;
        try {
            this._graph = new Directed_Weighted_Graph();
            JsonReader reader = new JsonReader(new FileReader(file));
            Gson gsonObject = new Gson();
            Type t = new TypeToken<JsonObject>() {
            }.getType();
            JsonObject graph = gsonObject.fromJson(reader, t);
            JsonArray nodes = graph.get("Nodes").getAsJsonArray();
            JsonArray edges = graph.get("Edges").getAsJsonArray();
            for (JsonElement node : nodes) {
                NodeData n = new Node_Data(node.getAsJsonObject().get("id").getAsInt());
                if (node.getAsJsonObject().get("pos") != null) {
                    String[] arr = node.getAsJsonObject().get("pos").getAsString().split(",");
                    n.setLocation(new Geo_Location(Double.parseDouble(arr[0]),
                            Double.parseDouble(arr[1]),
                            Double.parseDouble(arr[2])));
                }

                this._graph.addNode(n);
            }
            for (JsonElement edge : edges) {
                this._graph.connect(edge.getAsJsonObject().get("src").getAsInt(),
                        edge.getAsJsonObject().get("dest").getAsInt(),
                        edge.getAsJsonObject().get("w").getAsDouble());
            }

            init(this._graph);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
