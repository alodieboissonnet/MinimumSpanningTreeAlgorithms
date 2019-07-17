package trees;

import java.util.*;


import graph.*;

public class SpanningTree {
    // returns the collection of the edges that forms the minimum connecting spanning forest
    // the UnionFind argument u is initially empty and, at return time,
    //it should contain a description of the non-trivial connected components of g
    public static Collection<Edge> kruskal(UnionFind u, EuclideanGraph g){
    	EdgeComparator comparator = new EdgeComparator();
    	List<Edge> allEdges = g.getAllEdges();
    	allEdges.sort(comparator);
    	Collection<Edge> sol = new LinkedList<Edge>();
    	for (Edge e : allEdges) {
    		Place src = e.source;
    		Place dst = e.target;
    		if (u.find(src) != u.find(dst)) {
    			sol.add(e);
    			u.union(src, dst);
    		}
    	}
    	return sol;
    }

    // returns the list of collections of edges (one collection for each non-trivial tree-component)
    // of the trees of the minimum connected spanning forest of g
    public static Collection<Collection<Edge>> kruskal(EuclideanGraph g){
    	HashMap<Place,Collection<Edge>> edgeList = new HashMap<Place,Collection<Edge>>();
    	UnionFind u = new UnionFind();
    	Collection<Edge> col = kruskal(u,g);
    	for (Edge e : col) {
    		Place p = u.find(e.target);
    		if (edgeList.containsKey(p)) {
    			Collection<Edge> a = edgeList.get(p);
    			a.add(e);
    			edgeList.put(p, a);
    		}
    		else {
    			Collection<Edge> a = new LinkedList<Edge>();
    			a.add(e);
    			edgeList.put(p, a);
    		}
    	}
    	return edgeList.values();
    }

    // return (in the form of a collection of edges) the minimum spanning tree
    // of the connected component C containing start (in the graph g)
    public static Collection<Edge> primTree(HashSet<Place> nonVisited, Place start, EuclideanGraph g){
    	EdgeComparator comparator = new EdgeComparator();
    	PriorityQueue<Edge> q = new PriorityQueue<Edge>(comparator);
    	Collection<Edge> sol = new LinkedList<Edge>();
    	for (Edge e : g.edgesOut(start)) {
    		q.add(e);
    	}
    	nonVisited.remove(start);
    	while(!q.isEmpty()) {
    		Edge a = q.poll();
    		if (nonVisited.contains(a.target)) {
    			for (Edge b : g.edgesOut(a.target))
    	    		q.add(b);
    	    	nonVisited.remove(a.target);
    	    	sol.add(a);
    		}
    	}
    	return sol;
    }

    public static Place pick(Set<Place> col) {
    	return (Place) col.toArray()[0];
    }

    // return the list of collections of edges (one collection for each tree-component)
    // of the minimum connecting spanning forest of g
    public static Collection<Collection<Edge>> primForest(EuclideanGraph g){
    	HashSet<Place> nonVisited = new HashSet<Place>();
    	Set<Place> places = g.places();
    	for (Place p : places)
    		nonVisited.add(p);

    	HashMap<Place,Collection<Edge>> sol = new HashMap<Place,Collection<Edge>>();
    	while (!nonVisited.isEmpty()) {
    		Place start = pick(nonVisited);
    		Collection<Edge> primTree = primTree(nonVisited,start,g);
    		if (primTree.size() >= 1)
    			sol.put(start, primTree);
    	}
    	return sol.values();
    }


}
