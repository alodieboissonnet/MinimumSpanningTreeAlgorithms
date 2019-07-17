package trees;

import graph.Place;
import geomap.GeoMap;

import java.util.HashMap;

// Q1

public class UnionFind {
	//parent relation, parent.put(src,dst) indicates that src points to dst
    private HashMap<Place,Place> parent;

    public UnionFind( ){
        this.parent = new HashMap<Place,Place>();
        GeoMap geomap = new GeoMap("fr.txt");
        for (Place p : geomap.places())
        	this.parent.put(p, p);
    }

    // returns the oldest ancestor of src
    public Place find( Place src ){
        Place p = parent.get(src);
        if (p.equals(src))
        	return p;
        Place r = find(p);
        parent.put(src, r);
        return r;
    }

    // merge of the two sets
    public void union( Place v0, Place v1 ){
        Place p0 = find(v0);
        Place p1 = find(v1);
        if (!p0.equals(p1))
        	parent.put(p1, p0);
    }
}
