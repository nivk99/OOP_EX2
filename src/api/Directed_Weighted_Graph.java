package api;

import java.util.*;
import java.util.function.Consumer;

/**
 This Class represents a Directional Weighted Graph.
 This class uses a hash table data structure.
 The class field has 2 hash tables.
 1. For all vertices in the graph.
 2. For all the neighbors that each vertex has. (Represents the edge coming out of the vertex).
 In general this class stores all the information for the edge and vertices of the graph and allows operations to be performed on the information.
 */
public class Directed_Weighted_Graph implements DirectedWeightedGraph{
    private HashMap<Integer, NodeData> _has_node;
    private HashMap<Integer, HashMap<Integer,EdgeData>> _has_edge;
    private int size;
    private int mc;

    /**
     *constructor
     */

    public Directed_Weighted_Graph(){
        this.mc=0;
        this.size=0;
        this._has_node=new HashMap();
        this._has_edge=new HashMap();

    }

    /**
     *copy constructor
     * @param graph the new DirectedWeightedGraph
     */
    public Directed_Weighted_Graph(DirectedWeightedGraph graph) {
        this.mc=0;
        this.size=0;
        this._has_node=new HashMap();
        this._has_edge=new HashMap();

        Iterator<NodeData> itr_node=graph.nodeIter();

        Iterator<EdgeData> itr_edge=null;
        HashMap<Integer,EdgeData> has_edge = new HashMap<>();
      while (itr_node.hasNext()){
          NodeData node=itr_node.next();
          NodeData nNode=new Node_Data(node);
          this._has_node.put(nNode.getKey(),nNode);
         itr_edge=graph.edgeIter(node.getKey());
          has_edge = new HashMap<>();
          while (itr_edge.hasNext()){
              EdgeData edge=itr_edge.next();
              has_edge.put(edge.getDest(),edge);
          }
          this._has_edge.put(nNode.getKey(),has_edge);
      }

    }

    /**
     * Returns the vertex.
     * If not returns null
     * @param key - the node_id
     * @return NodeData
     */
    @Override
    public NodeData getNode(int key){
        return this._has_node.get(key);
    }

    /**
     * This method returns the data of the edge (src,dest), null if none.
     * The method checks if it exists. If it does not exist it just returns null
     * If not returns null
     * @param src = The source of the edge
     * @param dest = The target of the edge
     * @return EdgeData
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if(this.edgeSize() == 0||this.nodeSize() <=1||this.getNode(src)==null||this.getNode(dest)==null||src==dest||this._has_edge.get(src)==null)
            return null;
      return this._has_edge.get(src).get(dest);

    }


    /**
     * This method adds a new node to the graph with the given node_data.
     * The method checks if such a vertex does not exist. If it exists it just adds nothin
     * @param n What a vertex to insert
     */
    @Override
    public void addNode(NodeData n) {
        if(this._has_node.get(n.getKey()) == null) {
            this._has_node.put(n.getKey(), n);
           this.mc++;
        }

    }

    /**
     *This method  Connects an edge with weight w between node src to node dest.
     * A method that examines all the options for adding an edge
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */

    @Override
    public void connect(int src, int dest, double w) {
        if(this._has_node.size()<=1)
            return;
        if (src==dest||this._has_node.get(src)==null||this._has_node.get((dest))==null)
            return;
        NodeData v1=this._has_node.get(src);
        NodeData v2=this._has_node.get((dest));
        EdgeData new_edage= new Edge_Data(v1,v2,w);
        if(this._has_edge.get(src)==null){
            HashMap<Integer,EdgeData> new_has= new HashMap<>();
            new_has.put(dest,new_edage);
            this._has_edge.put(src,new_has);
            this.size++;
            this.mc++;
            return;
        }
        if (this._has_edge.get(src).get(dest)==null) {
            this._has_edge.get(src).put(dest, new_edage);
            this.size++;
            this.mc++;
            return;

        }
        if(this._has_edge.get(src).get(dest).getWeight()!=w){
            EdgeData old =this._has_edge.get(src).get(dest);
            this._has_edge.get(src).remove(dest,old);
            this._has_edge.get(src).put(dest,new_edage);
            this.mc++;
            return;
        }
        return;
    }

    /**
     *This method returns an Iterator for the collection representing all the nodes in the graph.
     * if the graph was changed since the iterator was constructed - a RuntimeException be thrown.
     * 	return Iterator<node_data>
     * This method implements the Iterator interface with tests of whether the graph has changed since the Iterator
     * @return  Iterator<node_data>
     */

    @Override
    public Iterator<NodeData> nodeIter() {
        return new ItrNode();
    }

    /**
     *This method returns an Iterator for all the edges in this graph.
     * if any of the edges going out of this node were changed since the iterator was constructed - a RuntimeException  be thrown.
     * * return Iterator <EdgeData>
     * This method implements the Iterator interface with tests of whether the graph has changed since the Iterator
     * @return Iterator<EdgeData>
     */

    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData> aList = new ArrayList<>();
        for (HashMap<Integer,EdgeData>has:this._has_edge.values())
            for (EdgeData edge_data : has.values())
                aList.add(edge_data);
        final int[] temp = {this.mc};
        Iterator<EdgeData> iterator= aList.iterator();
        Iterator<EdgeData> ans = new Iterator<>() {
           EdgeData e ;

            @Override
            public void remove() {
                temp[0]++;
               removeEdge(e.getSrc(), e.getDest());
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public EdgeData next() {
                if (temp[0]!=mc){
                    throw new RuntimeException();
                }
                if(hasNext()) {
                    e=iterator.next();
                    return e;
                }
                else
                    throw new NullPointerException();
            }
        };
        return new ItrEdge();
    }

    /**
     *  This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     *  if the graph was changed since the iterator - a RuntimeException  be thrown.
     * This method implements the Iterator interface with tests of whether the graph has changed since the Iterator
     * @param node_id = ID number of the vertex from which the edge come out
     * @return Iterator<EdgeData>
     */

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if(this.getNode(node_id) == null)
            return null;
        if(this._has_edge.get(node_id) == null)
            return new HashMap<Integer,EdgeData>().values().iterator() ;
        ArrayList<EdgeData> aList = new ArrayList<>();
        for(EdgeData e:this._has_edge.get(node_id).values())
            aList.add(e);
        final int[] temp = {this.mc};
        Iterator<EdgeData> iterator= aList.iterator();
        Iterator<EdgeData> ans = new Iterator<EdgeData>() {
            EdgeData v ;
            @Override
            public void remove() {
                temp[0]++;
                removeEdge(v.getSrc(), v.getDest());
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public EdgeData next() {
                if (temp[0]!=mc){
                    throw new RuntimeException();
                }
                if(hasNext()) {
                    v = iterator.next();
                    return v;
                }
                else
                    throw new NullPointerException();
            }
        };
        return ans;
    }

    /**
     * The method deletes the node (with the given ID) from the graph -
     *  and removes all edges which starts or ends at this node.
     * Time O (k), V.degree = k
     *  return the data of the removed node (null if none).
     *  key -  Vertex ID number
     * The method goes over all the ranks of the vertices and deletes them
     * @param key Vertex ID number
     * @return The deleted node
     */

    @Override
    public NodeData removeNode(int key) {
        NodeData node=this._has_node.get(key);
        HashMap<Integer,EdgeData> edge=this._has_edge.get(key);
        if(node==null) {
            return null;
        }
        this._has_node.remove(key,node);
        this.mc++;
        if(edge==null) {
            return node;
        }
        int sum=this._has_edge.get(key).size();
        this.size-=sum;
        this._has_edge.remove(key);
        for ( Integer i:edge.keySet()){
            this._has_edge.get(i).remove(key);
            size--;
        }
        return node;
    }

    /**
     *This method checks that it exists and then deletes it from the hash table.
     * Also updates size and mc
     * @param src The source of the vertex
     * @param dest Vertex target
     * @return The deleted edge
     */

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(this.size<=0)
            return null;
        EdgeData edge=this.getEdge(src,dest);
        if(edge==null||src==dest||this._has_node.get(src)==null||this._has_node.get(src)==null)
            return null;
        this._has_edge.get(src).remove(dest,edge);
        this.mc++;
        this.size--;
        return edge;
    }

    /**
     *Number of nodes
     * @return Number of nodes
     */

    @Override
    public int nodeSize() {
        return this._has_node.size();
    }

    /**
     *Number of edge.
     * Both incoming and outgoing
     * @return
     */

    @Override
    public int edgeSize() {
        //lambda expression in java
        int ans= this._has_edge.keySet().stream().mapToInt(n -> this._has_edge.get(n).size()).sum();
        return this.size;
    }

    /**
     * @return how many times the graph has changed
     */

    @Override
    public int getMC() {
        return this.mc;
    }


    /**
     *This class is private and it implements the Iterator interface.
     */

    private class ItrNode implements Iterator<NodeData> {
       private int check;
       private NodeData v;
     private   NodeData [] arr;
      private int arr_size;
        ItrNode() {
            arr_size=0;
           arr =new NodeData[nodeSize()];
            this.check=mc;
            int i=0;
            for(NodeData node:_has_node.values())
                arr[i++]=node;
        }
        @Override
        public boolean hasNext() {
            return arr_size<arr.length;
        }
        @Override
        public NodeData next() {
            if ( check !=mc){
                throw new RuntimeException();
            }
            if(hasNext()) {
                v=arr[arr_size++];
                return v;
            }
            else
                throw new NullPointerException();

        }
        @Override
        public void remove() {
            check++;
            removeNode(v.getKey());
        }

        @Override
        public void forEachRemaining(Consumer<? super NodeData> action) {
            Objects.requireNonNull(action);
                if (!(arr_size<arr.length))
                    throw new ConcurrentModificationException();
                while (arr_size<arr.length) {
                    action.accept(arr[arr_size++]);
                    checkForMc();
                }
        }

        final void checkForMc() {
            if ( check !=mc){
                throw new RuntimeException();
            }
        }

    }

    /**
     *This class is private and it implements the Iterator interface.
     */
    private class ItrEdge implements Iterator<EdgeData> {
        private int check;
        private EdgeData v;
        private   EdgeData [] arr;
        private int arr_size;
        ItrEdge() {
            arr_size=0;
            arr =new EdgeData[nodeSize()];
            this.check=mc;
            int i=0;
            for (HashMap<Integer,EdgeData>has:_has_edge.values())
                for (EdgeData edge_data : has.values())
                    arr[i++]=edge_data;
        }
        @Override
        public boolean hasNext() {
            return arr_size<arr.length;
        }
        @Override
        public EdgeData next() {
            if ( check !=mc){
                throw new RuntimeException();
            }
            if(hasNext()) {
                v=arr[arr_size++];
                return v;
            }
            else
                throw new NullPointerException();

        }
        @Override
        public void remove() {
            check++;
            removeEdge(v.getSrc(),v.getDest());
        }

        @Override
        public void forEachRemaining(Consumer<? super EdgeData> action) {
            Objects.requireNonNull(action);
            if (!(arr_size<arr.length))
                throw new ConcurrentModificationException();
            while (arr_size<arr.length) {
                action.accept(arr[arr_size++]);
                checkForMc();
            }
        }

        final void checkForMc() {
            if ( check !=mc){
                throw new RuntimeException();
            }
        }

    }




}

