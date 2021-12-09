package test;

import gui.Point.Point3D;
import api.GeoLocation;
import api.Geo_Location;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class Geo_LocationTest {

    private LinkedList<GeoLocation> link;

    public Geo_LocationTest(){
        link=new LinkedList<>();
        for (int i = 0; i <10000 ; i++) {
            GeoLocation node=new Geo_Location(i,i-1,i/2);
            link.add(node);
        }
    }

//This is a method of testing
    @Test
    void x() {

        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i,link.get(i).x());
        }

    }
//This is a method of testing
    @Test
    void y() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i-1,link.get(i).y());
        }
    }
//This is a method of testing
    @Test
    void z() {
        for (int i = 0; i <link.size() ; i++) {
            assertEquals(i/2,link.get(i).z());
        }
    }
//This is a method of testing
    @Test
    void distance() {

        for (int i = 0; i <link.size()-1 ; i++) {
            GeoLocation p1=link.get(i);
            GeoLocation p2=link.get(i+1);
            assertEquals(new Point3D(p1.x(),p1.y(),p1.z()).distance(p2),p1.distance(p2));
        }



    }
}