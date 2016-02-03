import java.util.ArrayList;
import java.util.HashMap;

import javalib.worldimages.Posn;

//represents the function that performs Kruskels algorithm
class UnionFind {
 HashMap<Vertex, Vertex> reps;
 ArrayList<ArrayList<Vertex>> vertList;
 IList<Edge> edgeList;
 IList<Edge> unUsed;
 UnionFind(ArrayList<ArrayList<Vertex>> vertList, IList<Edge> edgeList) {
     this.reps = new HashMap<Vertex, Vertex>();
     this.vertList = vertList;
     this.edgeList = edgeList;
     this.unUsed = new Mt<Edge>();
     this.initializeHashMap();
     this.edgeList = this.edgeList.sort(new CompEdge());

 }
 // initializes the representatives of a list of Vertices
 void initializeHashMap() {
     for(ArrayList<Vertex> aV: vertList) {
         for(Vertex v: aV) {
             reps.put(v, v);
         }
     }
 }
 // find this Posn key's data
 Vertex find(Vertex v) {
     if (reps.get(v) == v) {
         return v;
     }
     else {
         return find(this.reps.get(v));
     }
 }
 // union
 void union(Vertex v1, Vertex v2) {
     this.reps.put(this.find(v1), this.find(v2));
 }
 // Checks for a cycle
 boolean formsCycle(Vertex v1, Vertex v2) {
     return this.find(v1) == this.find(v2);
 }
 // Perform Kruskels algorithms on the list of edges
 IList<Edge> kruskel() {
     IList<Edge> result = new Mt<Edge>();
     for (Edge e: this.edgeList) {
         if (!this.formsCycle(e.from, e.to)) {
             result = result.addToFront(e);
             this.union(e.from, e.to);
         }
         else {
             this.unUsed = this.unUsed.addToFront(e);
             e.from.edges = e.from.edges.remove(e);
             e.to.edges = e.to.edges.remove(e);
         }
     }
     return result;
 }
}