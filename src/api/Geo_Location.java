package api;

/**
 * This class describes a point in 3 dimensions
 * @author Niv kotek
 */
public class Geo_Location implements GeoLocation {

    private double x;
    private double y;
    private double z;

    /**
     *constructor
     */
    public Geo_Location(){
        this.x=0;
        this.y=0;
        this.z=0;
    }

    /**
     *constructor
     * @param x = axis x
     * @param y axis y
     * @param z axis z
     */
    public Geo_Location(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     *constructor
     * @param x = axis x
     * @param y = axis y
     */
    public Geo_Location(double x, double y){
        this.x=x;
        this.y=y;
        this.z=0;
    }

    /**
     *constructor
     * @param geo = The new vertex
     */
    public Geo_Location(GeoLocation geo){
        this.x=geo.x();
        this.y=geo.y();
        this.z=geo.z();
    }

    /**
     * @return Get x
     */
    @Override
    public double x() {
        return this.x;
    }

    /**
     * @return Get y
     */
    @Override
    public double y() {
        return this.y;
    }

    /**
     * @return Get z
     */
    @Override
    public double z() {
        return this.z;
    }

    /**
     * @param g The second point for calculating the distance
     * @return Distance between 2 vertices with 3 values
     */
    @Override
    public double distance(GeoLocation g) {
        double dis_x=Math.pow(this.x-g.x(),2);
        double dis_y=Math.pow(this.y-g.y(),2);
        double dis_z=Math.pow(this.z-g.z(),2);
        return Math.sqrt(dis_x+dis_y+dis_z);
    }

    /**
     * @return A string of the whole class
     */

    public String toString() {
        return "("+this.x()+","+this.y()+"," +this.z()+")";
    }


}
