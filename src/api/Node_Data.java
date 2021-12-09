package api;

public class Node_Data implements NodeData {
    /**
     * This class describes the vertices in the graph
     * @author Niv kotek
     */

    private int _node_key;
    private int _node_tag;
    private double _node_weight;
    private String _node_info;
    private GeoLocation _node_location;

    /**
     * constructor
     */
    public Node_Data(){
        this._node_key =0;
        this._node_tag =0;
        this._node_weight =0;
        this._node_info =new String();
        this._node_location=new Geo_Location();
    }

    /**
     * constructor
     * @param key = Vertex ID number
     * @param tag = Number for help with the algorithm
     * @param weight =The weight of the vertex
     * @param info = Help with algorithm
     * @param loc = Where is the vertex located
     */
    public Node_Data(int key,int tag,double weight,String info,GeoLocation loc){
        this._node_key =key;
        this._node_tag =tag;
        this._node_weight =weight;
        this._node_info =info;
        this._node_location=loc;
    }

    /**
     * constructor
     * @param node  Vertex in the graph
     */
    public Node_Data(NodeData node){
        this._node_key =node.getKey();
        this._node_tag =node.getTag();
        this._node_weight =node.getWeight();
        this._node_info =new String(node.getInfo());
        this._node_location=new Geo_Location(node.getLocation());


    }

    /**
     * constructor
     * @param key = Vertex ID number
     */

    public Node_Data(int key) {
        this._node_key = key;
        this._node_location = new Geo_Location();
        this._node_weight = 0;
        this._node_info = "";
        this._node_tag = 0;
    }

    /**
     * constructor
     * @param key = Vertex ID number
     * @param x = Position of the vertex on the x-axis
     * @param y = Position of the vertex on the y-axis
     */
    public Node_Data(int key,double x, double y){
        this._node_key = key;
        this._node_location = new Geo_Location(x,y);
        this._node_weight = 0;
        this._node_info = "";
        this._node_tag = 0;
    }

    /**
     *  constructor
     * @param key
     * @param x
     * @param y
     */
    public Node_Data(int key,double x, double y,double z){
        this._node_key = key;
        this._node_location = new Geo_Location(x,y,z);
        this._node_weight = 0;
        this._node_info = "";
        this._node_tag = 0;
    }

    /**
     * @return Vertex ID number
     */
    @Override
    public int getKey() {
        return this._node_key;
    }

    /**
     * @return  3D point
     */
    @Override
    public GeoLocation getLocation() {
        return this._node_location;
    }

    /**
     * @param p - new new location  (position) of this node.
     */

    @Override
    public void setLocation(GeoLocation p) {
        this._node_location =p;

    }

    /**
     * @return The weight of the vertex
     */

    @Override
    public double getWeight() {
        return this._node_weight;
    }

    /**
     * @param w - the new weight
     */

    @Override
    public void setWeight(double w) {
        this._node_weight =w;

    }

    /**
     * @return Get Info
     */

    @Override
    public String getInfo() {
        return this._node_info;
    }

    /**
     * @param s = changes Info
     */

    @Override
    public void setInfo(String s) {
        this._node_info =s;

    }

    /**
     * @return Get Tag
     */

    @Override
    public int getTag() {
        return this._node_tag;
    }

    /**
     * @param t - the new value of the tag
     */

    @Override
    public void setTag(int t) {
        this._node_tag =t;

    }

    /**
     * @return A string of the whole class
     */
    public String toString(){
        return "key:"+this._node_key+",weight:"+this._node_weight+",info:"+this._node_info+",tag:"+this._node_tag+",location"+this._node_location;
    }
}
