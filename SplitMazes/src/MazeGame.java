// Assignment 10
// Cherry Alexander
// acherry
// Davis Jack
// jdavis

import tester.*;

import java.awt.Canvas;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javalib.colors.*;
import javalib.impworld.*;
import javalib.worldimages.*;

//represents a maze cell
class Vertex {

  IList<Edge> edges;
  boolean wasSearched;
  boolean correctPath;
  boolean startVert;
  boolean endVert;
  boolean hasSearchHead;

  //Integer x;
  //Integer y;
  Posn posn;
  Vertex(int x, int y) {
      this.edges = new Mt<Edge>();
      this.wasSearched = false;
      this.correctPath = false;
      this.startVert = false;
      this.endVert= false;
      this.hasSearchHead = false;

      //this.x = x;
      //this.y = y;
      this.posn = new Posn(x, y);
  }
  // returns this Vertex's x posn
  Integer getX() {
      return this.posn.x;
  }
  // returns this Vertex's y posn
  Integer getY() {
      return this.posn.y;
  }
  // Add an Edge with an entered weight 
  void addEdge(Vertex other, int opt) {
      Edge toAdd = new Edge(this, other, opt);
      this.edges = this.edges.addToBack(toAdd);
      other.edges = other.edges.addToBack(toAdd);
  }
  // sets the neighbors of this vertex
  IList<Vertex> findNeighbors() {
      IList<Vertex> neighbors = new Mt<Vertex>();
      for (Edge e: this.edges) {
          if (e.from == this) {
              neighbors = neighbors.addToBack(e.to);
          }
          else {
              neighbors = neighbors.addToBack(e.from);
          }
      }
      return neighbors;
  }
  // displays the maze cell
  WorldImage displayCell() {
      int sideLength = 10;
      int posnShift = 5;
      Color c = new Color(205, 205, 205);
      if (this.correctPath || this.hasSearchHead) {
          c = new Color(65, 86, 197);
      }
      else if (this.startVert) {
          c = new Color(0, 160, 0);
      }
      else if (this.wasSearched) {
          c = new Color(56, 176, 222);
      }
      else if (this.endVert) {
          c = new Color(160, 0, 160);
      }
      return new RectangleImage(new Posn((this.getX() * sideLength) + posnShift, 
              (this.getY() * sideLength) + posnShift), 10, 10, c);
  }  

}

//represents an edge of the maze graph
class Edge {
  Vertex from;
  Vertex to;
  int weight;
  Edge(Vertex from, Vertex to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
  }
  // displays this edge 
  WorldImage displayEdge(boolean visibleLine) {
      int sideLength = 10;
      int posnShift = sideLength / 2;
      int toX = (this.to.getX() * sideLength) + posnShift;
      int toY = (this.to.getY() * sideLength) + posnShift;
      int fromX = (this.from.getX() * sideLength) + posnShift;
      int fromY = (this.from.getY() * sideLength) + posnShift;
      if (visibleLine) {
          return new LineImage(new Posn(fromX, fromY), new Posn(toX, toY), new Color(255, 0, 0));
      }
      else {
          return new OverlayImages(this.from.displayCell(), this.to.displayCell());
      }
  }
  WorldImage displayWall() {
      Color c = new Color(90, 100, 90);
      int sideLength = 10;
      int posnShift = sideLength / 2;
      int toX = (this.to.getX() * sideLength) + posnShift;
      int toY = (this.to.getY() * sideLength) + posnShift;
      int fromX = (this.from.getX() * sideLength) + posnShift;
      int fromY = (this.from.getY() * sideLength) + posnShift;
      // next to each other horizontally
      Posn p2 = new Posn((toX + fromX) / 2, toY + 5);
      Posn p1 = new Posn((toX + fromX) / 2, toY - 5);
      // next to each other vertically
      Posn p4 = new Posn(toX + 5, (toY + fromY) / 2);
      Posn p3 = new Posn(toX - 5, (toY + fromY) / 2);
      // connected horizontally
      if (fromY == toY) {
          return new LineImage(p1, p2, c);
      }
      // connected vertically
      else if (fromX == toX) {
          return new LineImage(p3, p4, c);
      }
      // connected otherwise
      else {
          throw new RuntimeException("There is an edge connecting two non-adjacent vertices");
      }
  }
}

//represents the gameWorld
class MazeWorld extends World {
  // Size of the game
  int gameSizeX;
  int gameSizeY;
  // GAMEMODES:
  // 0 = manual
  // 1 = depth-first search
  // 2 = breadth-first search
  int gameMode;
  boolean isPaused;
  IList<Edge> board;
  IList<Edge> unUsed;
  IList<Vertex> searchHeads;
  
  Stack<Vertex> depthList;
  Queue<Vertex> breadthList;
  HashMap<Vertex, Edge> cameFromEdge;

  MazeWorld(int gameSizeX, int gameSizeY) {
      // Basic constructor stuff
      this.gameSizeX = gameSizeX;
      this.gameSizeY = gameSizeY;
      // Default game mode is 0, or manual
      this.gameMode = 0;
      this.isPaused = false;
      // Initialize lists and hashmaps to empty
      this.board = new Mt<Edge>();
      this.searchHeads = new Mt<Vertex>();
      this.unUsed = new Mt<Edge>();
      // Create a basic grid of vertices
      ArrayList<ArrayList<Vertex>> blankCells = this.createGrid();
      // Give the grid edges
      this.addEdges(blankCells);
      // Convert the ArrayList<ArrayList<Vertex>> into an IList of Edges
      IList<Edge> b = this.vertexToEdge(blankCells);
      // Perform the algorithm
      UnionFind kruskel = new UnionFind(blankCells, b);
      //this.board = b;
      this.board = kruskel.kruskel();
      this.unUsed = kruskel.unUsed;
      
      depthList = new Stack<Vertex>(new Deque<Vertex>());
      breadthList = new Queue<Vertex>(new Deque<Vertex>());
      cameFromEdge = new HashMap<Vertex, Edge>();
      
      if (!this.searchHeads.isEmpty()) {
          Vertex first = ((Cons<Vertex>)this.searchHeads).first;
          depthList.push(first);
          breadthList.enqueue(first);
          //first.startVert = true;
      }

  }

  // gives a Vertex a SearchHead
  // EFFECTS: Updates the hasSearchHead Field of the vertex
  void addSearchHeadToFront(Vertex v) {
      v.hasSearchHead = true;
      this.searchHeads = this.searchHeads.addToFront(v);

  }
  // gives a Vertex a SearchHead
  // EFFECTS: Updates the hasSearchHead Field of the vertex
  void addSearchHeadToBack(Vertex v) {
      v.hasSearchHead = true;
      this.searchHeads = this.searchHeads.addToBack(v);
  }
  // removes a SearchHead from a Vertex
  // EFFECTS: Updates the hasSearchHead Field of the vertex
  IList<Vertex> removeSearchHead(Vertex v) {
      IList<Vertex> result = new Mt<Vertex>();
      for(Vertex v2: this.searchHeads) {
          if (!(v == v2)) {
              result = result.addToBack(v2);
          }
      }
      v.hasSearchHead = false;
      return result;
  }
  // Change an IList<T> into an ArrayList<T>
  <T> ArrayList<T> iListToArr(IList<T> toChange) {

      ArrayList<T> result = new ArrayList<T>();

      for(T t: toChange) {
          result.add(t);
      }

      return result;

  }

  // Create a grid of blank Vertices
  ArrayList<ArrayList<Vertex>> createGrid() {
      ArrayList<ArrayList<Vertex>> result = new ArrayList<ArrayList<Vertex>>();

      for(int i = 0; i < gameSizeX; i += 1) {

          result.add(new ArrayList<Vertex>());

      }

      for(int i = 0; i < gameSizeX; i += 1) {

          for(int i2 = 0; i2 < gameSizeY; i2 += 1) {

              result.get(i).add(new Vertex(i, i2));

          }

      }
      if (result.size() > 0) {
          result.get(0).get(0).startVert = true;
          result.get(this.gameSizeX - 1).get(this.gameSizeY - 1).endVert = true;
          this.addSearchHeadToFront(result.get(0).get(0));
      }
      return result;

  }

  // Add edges to the given ArrayList<ArrayList<Vertex>>
  void addEdges(ArrayList<ArrayList<Vertex>> grid) {
      Random randy = new Random();

      // Connections to the left/right
      for(int i = 1; i < grid.size(); i += 1) {

          for(int i2 = 0; i2 < grid.get(i).size(); i2 += 1) {

              grid.get(i).get(i2).addEdge(grid.get(i - 1).get(i2), 
                      randy.nextInt(10000));

          }

      }

      // Connections to the top/bottom
      for(int i3 = 0; i3 < grid.size(); i3 += 1) {

          for(int i4 = 1; i4 < grid.get(i3).size(); i4 += 1) {

              grid.get(i3).get(i4).addEdge(grid.get(i3).get(i4 - 1), 
                      randy.nextInt(10000));
          }

      }

  }

  // Add edges to the given ArrayList<ArrayList<Vertex>> (overloaded for testing)
  void addEdges(ArrayList<ArrayList<Vertex>> grid, int r) {

      // Connections to the left/right
      for(int i = 0; i < grid.size() - 1; i += 1) {

          for(int i2 = 0; i2 < grid.get(i).size(); i2 += 1) {

              grid.get(i).get(i2).addEdge(grid.get(i + 1).get(i2), 1);

          }

      }

      // Connections to the top/bottom
      for(int i3 = 0; i3 < grid.size(); i3 += 1) {

          for(int i4 = 0; i4 < grid.get(i3).size() - 1; i4 += 1) {

              grid.get(i3).get(i4).addEdge(grid.get(i3).get(i4 + 1), 1);

          }

      }

  }


  // Convert a 2D ArrayList of Vertices to a 1D IList of Edges
  IList<Edge> vertexToEdge(ArrayList<ArrayList<Vertex>> grid) {

      IList<Edge> edges = new Mt<Edge>();
      for(int i = 0; i < grid.size(); i += 1) {

          for(int i2 = 0; i2 < grid.get(i).size(); i2 += 1) {

              for (Edge e: grid.get(i).get(i2).edges) {

                  if (e.from == grid.get(i).get(i2)) {
                      edges = edges.addToFront(e);
                  }

              }

          }
      }
      return edges;
  }
  // manually moves the SearchHead in a direction
  void moveSearchHead(String s) {
      Vertex searchHead = this.searchHeads.get(0);
      if (s.equals("up") || s.equals("down") || s.equals("left") || s.equals("right")) {
          MoveVertex moveVertex = new MoveVertex(searchHead); 
          Vertex next = moveVertex.move(s);
          if (moveVertex.hasDir) {
              this.addSearchHeadToBack(next);
              this.searchHeads.get(0).wasSearched = true;
              this.searchHeads = this.removeSearchHead(this.searchHeads.get(0));
          }
          else {
              this.searchHeads = this.searchHeads;
          }
      }
      else {
          throw new IllegalArgumentException("input is not a direction");
      }
  }

  // Draws the World
  public WorldImage makeImage() {
      IComp<Edge> ranE = new RandEdge(); 
      ITST<Edge> boardTree = this.board.list2Tree(ranE);
      ITST<Edge> unUsedTree = this.unUsed.list2Tree(ranE);
      DisplayEdgeVisitor dEVisitor = 
              new DisplayEdgeVisitor(this.isPaused);
      DisplayWallVisitor dWVisitor = 
              new DisplayWallVisitor();

      if (this.isPaused) {
          return boardTree.accept(dEVisitor);
      } 
      else {
          return new OverlayImages(boardTree.accept(dEVisitor),
                  unUsedTree.accept(dWVisitor));
      }
  }

  // key handler TODO
  public void onKeyEvent(String s) {
      // reset the game
      if (s.equals("r")) {
          this.gameMode = 0;
          this.board = new Mt<Edge>();
          this.searchHeads = new Mt<Vertex>();
          this.unUsed = new Mt<Edge>();
          ArrayList<ArrayList<Vertex>> blankCells = this.createGrid();
          this.addEdges(blankCells);
          IList<Edge> b = this.vertexToEdge(blankCells);
          UnionFind kruskel = new UnionFind(blankCells, b);
          this.board = kruskel.kruskel();
          this.unUsed = kruskel.unUsed;
      }
      // display edges mode
      else if (s.equals("e")) {
          if (!this.isPaused) { 
              this.isPaused = true;
          }
          else {
              this.isPaused = false;
          }
      }
      // manual mode
      else if (s.equals("m") && !(this.gameMode == 0)) {
          this.gameMode = 0;
      }
      // depth-first search mode
      else if (s.equals("d") && !(this.gameMode == 1)) {
          depthList = new Stack<Vertex>(new Deque<Vertex>());
          cameFromEdge = new HashMap<Vertex, Edge>();
          
          if (!this.searchHeads.isEmpty()) {
              Vertex first = ((Cons<Vertex>)this.searchHeads).first;
              depthList.push(first);
              first.startVert = true;
          }
          this.gameMode = 1;
      }
      // breadth-first search mode
      else if (s.equals("b") && !(this.gameMode == 2)) {
          breadthList = new Queue<Vertex>(new Deque<Vertex>());
          cameFromEdge = new HashMap<Vertex, Edge>();
          
          if (!this.searchHeads.isEmpty()) {
              Vertex first = ((Cons<Vertex>)this.searchHeads).first;
              breadthList.enqueue(first);
              first.startVert = true;
          }
          this.gameMode = 2;
      } 
      else if (this.gameMode == 0 && (s.equals("up") || s.equals("down") || s.equals("left") || s.equals("right"))) {
          this.moveSearchHead(s);
      }
  }
  
  // Search through the tree using the Breadth-First algorithm
  // This method is called every tick, and therefore only advances
  // the search by one increment per call.
  void breadthFirstSearch() {
      
      if (!this.breadthList.isEmpty()) {
          Vertex next = this.breadthList.dequeue();
          if (!next.wasSearched && next.endVert) {
              this.searchHeads =  reconstruct(next, new Mt<Vertex>());
          }
          else {
              this.removeSearchHead(next);
              next.wasSearched = true;
              for(Edge e: next.edges) {
                  if (e.from == next && !e.to.wasSearched) {
                      this.addSearchHeadToBack(e.to);
                      this.breadthList.enqueue(e.to);
                      this.cameFromEdge.put(e.to, e);
                  }
                  else if (e.to == next && !e.from.wasSearched) {
                      this.addSearchHeadToBack(e.from);
                      this.breadthList.enqueue(e.from);
                      this.cameFromEdge.put(e.from, e);
                  }
              }
          }
      }
      
  }

  // Search through the tree using the Depth-First algorithm
  // This method is called every tick, and therefore only advances
  // the search by one increment per call.
  void depthFirstSearch() {
      
      if (!this.depthList.isEmpty()) {
          Vertex next = this.depthList.pop();
          if (!next.wasSearched && next.endVert) {
              this.searchHeads =  reconstruct(next, new Mt<Vertex>());
          }
          else {
              this.removeSearchHead(next);
              next.wasSearched = true;
              for(Edge e: next.edges) {
                  if (e.from == next && !e.to.wasSearched) {
                      this.addSearchHeadToFront(e.to);
                      this.depthList.push(e.to);
                      this.cameFromEdge.put(e.to, e);
                  }
                  else if (e.to == next && !e.from.wasSearched) {
                      this.addSearchHeadToFront(e.from);
                      depthList.push(e.from);
                      this.cameFromEdge.put(e.from, e);
                  }
              }
          }
      }
      
  }
  
  // onTick is called at regular time intervals
  public void onTick() {
      if (!this.isPaused) {
      // DF search
      if (this.gameMode == 1) {
          this.depthFirstSearch();
      }
      // BF search
      else if (this.gameMode == 2) {
          this.breadthFirstSearch();
      }
      }
  }
  
  // reconstruct the path from the end to the beginning
  IList<Vertex> reconstruct(Vertex end, IList<Vertex> finalPath) {
      if (end.startVert) {
          return finalPath;
      }
      else {
          finalPath.addToFront(end);
          return reconstruct(this.cameFromEdge.get(end).from, finalPath);
      }
  }

}