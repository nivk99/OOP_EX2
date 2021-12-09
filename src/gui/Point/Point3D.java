/**
 * This class represents a 3D point in space.
 */
package gui.Point;

import api.*;
import java.io.Serializable;

/**
 * @author boaz_benmoshe
 */
public class Point3D implements GeoLocation, Serializable{
	private static final long serialVersionUID = 1L;
	/**
     * Simple set of constants - should be defined in a different class (say class Constants).*/
    public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    /**
     * This field represents the origin point:[0,0,0]
     */
    private double _x,_y,_z;
    public Point3D(double x, double y, double z) {
        _x=x;
        _y=y;
        _z=z;
    }

    @Override
    public double x() {return _x;}
    @Override
    public double y() {return _y;}
    @Override
    public double z() {return _z;}


    public String toString() { return _x+","+_y+","+_z; }
    @Override
    public double distance(GeoLocation p2) {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double dz = this.z() - p2.z();
        double t = (dx*dx+dy*dy+dz*dz);
        return Math.sqrt(t);
    }

    public boolean equals(Object p) {
        if(p==null || !(p instanceof GeoLocation)) {return false;}
        Point3D p2 = (Point3D)p;
        return ( (_x==p2._x) && (_y==p2._y) && (_z==p2._z) );
    }

}

