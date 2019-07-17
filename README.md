# MinimumSpanningTreeAlgorithms
Implementation of Kruskal's and Prim's algorithm in Java to solve the minimum spanning tree problem

In this project, we will implement Minimum Spanning Tree algorithms over a particular type of graph, in which nodes represent cities on a map, and edges are drawn between nearby cities. Adjacencies are not determined by road connections, but instead by their distance; more precisely, two cities are considered adjacent if their geodesic distance is smaller than a certain maximal value. The weight of each edge is given by the geodesic distance between the pair of cities it connects. We will implement two classical algorithms, Kruskal’s algorithm and Prim’s algorithm, in order to find the minimum spanning tree in each of the connected component of the graph of cities, minimizing the cost of the tree with respect to the given edge weights.

# Implementing Kruskal’s algorithm: returning a spanning forest
We now move on to the problem of computing minimum spanning trees of a graph G. Since G can be made up of several connected components G1 , . . . , Gk (as with the proximity graph when the maximal value for the distances becomes small), we consider more generally the problem of computing the minimum ”connecting spanning forest” of G (a connecting spanning forest of G is a forest of trees T1 , . . . , Tk such that each Ti is a spanning tree of Gi, for each i ∈ [1..k]).

Here is a quick reminder of how Kruskal’s algorithm works:
1. the edges are sorted in increasing order of weight
2. the edges e1 , . . . , en are considered one by one in increasing order, and at each step i one decides if ei is to be selected: ei is selected if and only if it satisfies the property that its two extremities are not connected using the previously selected edges (testing this property is handled by the union-find structure).

The output (the collection of selected edges) forms the minimum connecting spanning forest of the graph. Note that this forest will not contain some trivial components which are formed from a single vertex and which are therefore without an edge.
