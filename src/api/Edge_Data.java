package api;

import api.EdgeData;
import api.NodeData;

/**
 * This class describes the edge in the graph
 * @author Niv kotek
 */
public class Edge_Data implements EdgeData {

    private double _edge_weight;
    private int _edge_tag;
    private String _edge_info;
    private NodeData _edge_src;
    private NodeData _edge_dest;

    /**
     *
     */
    public Edge_Data(){
        this._edge_weight=0;
        this._edge_tag=0;
        this._edge_info=new String();
        this._edge_src=null;
        this._edge_dest=null;
    }

    /**
     *
     * @param src
     * @param dest
     * @param weight
     */
    public Edge_Data(NodeData src, NodeData dest,double weight){
        this._edge_weight=weight;
        this._edge_tag=0;
        this._edge_info=new String();
        this._edge_src=src;
        this._edge_dest=dest;

    }

    /**
     *
     * @return
     */

    @Override
    public int getSrc() {
        return this._edge_src.getKey();
    }

    /**
     *
     * @return
     */
    @Override
    public int getDest() {
        return this._edge_dest.getKey();
    }

    /**
     *
     * @return
     */
    @Override
    public double getWeight() {
        return this._edge_weight;
    }

    /**
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this._edge_info;
    }

    /**
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this._edge_info=s;

    }

    /**
     *
     * @return
     */
    @Override
    public int getTag() {
        return this._edge_tag;
    }

    /**
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this._edge_tag=t;

    }

    /**
     *
     * @return
     */
    public String toString(){
        return "src:"+this._edge_src.getKey()+",dest:"+this._edge_dest.getKey()+",weight:"+this._edge_weight+",tag:"+this._edge_tag+",info"+this._edge_info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EdgeData)) {
            return false;
        }
        EdgeData e = (EdgeData) o;
        boolean bo=this.getSrc()==e.getSrc()&&this.getDest()==e.getDest()&&this.getWeight()==e.getWeight();
        return bo;
    }



}
