import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

import javalib.worldimages.LineImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

//represents a visitor object
interface IVisitor<T, R> {
  R visit(Cons<T> c);
  R visit(Mt<T> m);
  R visit(TTNode<T> n);
  R visit(Leaf<T> n);
}

//represents a visitor that displays the cells in a list
class DisplayWallVisitor implements IVisitor<Edge, WorldImage> {
  DisplayWallVisitor() {
  }
  // visits an empty
  public WorldImage visit(Mt<Edge> m) {
      throw new IllegalArgumentException("IList is not a valid argument");
  }
  // visits a cons
  public WorldImage visit(Cons<Edge> c) {
      throw new IllegalArgumentException("IList is not a valid argument");
  }
  // visits a TTNode
  public WorldImage visit(TTNode<Edge> n) {
      return new OverlayImages(n.data.displayWall(), 
              new OverlayImages(n.left.accept(this), 
                      new OverlayImages(n.middle.accept(this), n.right.accept(this))));
  }
  // visits a Leaf
  public WorldImage visit(Leaf<Edge> n) {
      return new LineImage(new Posn(-1, -1), new Posn(-1, -1), new Color(255, 255, 255));
  }    
}

//represents a visitor that displays the edges in a list
class DisplayEdgeVisitor implements IVisitor<Edge, WorldImage> {
  boolean visibleEdges;
  DisplayEdgeVisitor(boolean visibleEdges) {
      this.visibleEdges = visibleEdges;
  }
  // visits an empty
  public WorldImage visit(Mt<Edge> m) {
      throw new IllegalArgumentException("IList is not a valid argument");
  }
  // visits a cons
  public WorldImage visit(Cons<Edge> c) {
      throw new IllegalArgumentException("IList is not a valid argument");
  }
  // visits a TTNode
  public WorldImage visit(TTNode<Edge> n) {
      return new OverlayImages(n.data.displayEdge(visibleEdges),
              new OverlayImages(n.left.accept(this), 
                      new OverlayImages(n.middle.accept(this), n.right.accept(this))));
  }
  // visits a Leaf
  public WorldImage visit(Leaf<Edge> n) {
      return new LineImage(new Posn(-1, -1), new Posn(-1, -1), new Color(255, 255, 255));
  }    
}

//represents a function object that takes an A and returns an R
interface IFunc<A, R> {
  // Apply the function
  R apply(A a);
}

//represents a function that converts a number to a String
class ToString implements IFunc<Integer, String> {
  public String apply(Integer i) {
      return (String)i.toString();
  }
}


//Iterator for IList<T>
class IListIterator<T> implements Iterator<T> {

  IList<T> src;

  IListIterator(IList<T> src) { this.src = src; }
  // does this iterator have an iterator?
  public boolean hasNext() {

      return !this.src.isEmpty();
  }
  // gets the next out of this Iterator
  public T next() {

      if (!this.hasNext()) {
          throw new RuntimeException("there is no next");
      }

      Cons<T> sourceAsCons = (Cons<T>)this.src;
      T result = sourceAsCons.first;
      this.src = sourceAsCons.rest;
      return result;

  }
  // does nothing
  public void remove() {

      throw new RuntimeException("Do not use this method, please");

  }

}


//this represents a comparator
interface IComp<T> {
  //== 0 : t1 == t2
  //< 0: t1 < t2
  //> 0: t1 > t2
  int compare(T t1, T t2);
}

//this compares two Edges randomly
class RandEdge implements IComp<Edge> {
  // seeded for testing
  Random r2 = new Random(10);
  // compares
  public int compare(Edge e1, Edge e2) {
      Random r = new Random();
      if (r.nextInt(1000) < 333) {
          return -1;
      }
      else if (r.nextInt(1000) > 666) {
          return 1;
      }
      else {
          return 0;
      }
  }
  // compares (TEST METHOD)
  public int compare(Edge e1, Edge e2, int seed) {
      Integer next = r2.nextInt(1000);
      if (next < 333) {
          return -1;
      }
      else if (next > 666) {
          return 1;
      }
      else {
          return 0;
      }
  }
  
}

//this compares two Edges randomly
class RandVert implements IComp<Vertex> {
  // seeded for testing
  Random r2 = new Random(10);
  // compares
  public int compare(Vertex e1, Vertex e2) {
      Random r = new Random();
      if (r.nextInt(1000) < 333) {
          return -1;
      }
      else if (r.nextInt(1000) > 666) {
          return 1;
      }
      else {
          return 0;
      }
  }
  // compares (TEST METHOD)
  public int compare(Vertex e1, Vertex e2, int seed) {
      Integer next = r2.nextInt(1000);
      if (next < 333) {
          return -1;
      }
      else if (next > 666) {
          return 1;
      }
      else {
          return 0;
      }
  }
}

//this represents a comparator of Cells
class CompVert implements IComp<Vertex> {
  // compares based on x and y (e.g. (0, 1) < (1, 1) < (1, 2) < (2, 0))
  public int compare(Vertex t1, Vertex t2) {
      if (t1.getX() > t2.getX() || (t1.getX() == t2.getX() && t1.getY() > t2.getY())) {
          return 1;
      }
      else if (t1.getX() == t2.getX() && t1.getY() == t2.getY()) {
          return 0; 
      }
      else {
          return -1;
      }
  }
}

//this represents a comparator of Cells
class CompEdge implements IComp<Edge> {
  // compares based on weight
  public int compare(Edge t1, Edge t2) {
      if (t1.weight > t2.weight) {
          return 1;
      }
      else if (t1.weight < t2.weight) {
          return -1; 
      }
      else {
          return 0;
      }
  }
}