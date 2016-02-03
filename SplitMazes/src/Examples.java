import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javalib.worldimages.LineImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

//examples and tests for the MazeWorld
class ExamplesMaze {

  Vertex A;
  Vertex B;
  Vertex C;
  Vertex D;
  Vertex E;
  Vertex F;

  Edge ec;
  Edge cd;
  Edge ab;
  Edge be;
  Edge bc;
  Edge fd;
  Edge ae;
  Edge bf;

  MazeWorld maze0 = new MazeWorld(0, 0);
  MazeWorld maze5 = new MazeWorld(5, 5);
  MazeWorld maze3 = new MazeWorld(3, 3);
  MazeWorld maze2 = new MazeWorld(2, 2);


  ArrayList<Vertex> aV0 = new ArrayList<Vertex>();
  ArrayList<Vertex> aV1 = new ArrayList<Vertex>();
  ArrayList<Vertex> aV2 = new ArrayList<Vertex>();
  ArrayList<Vertex> aV3 = new ArrayList<Vertex>();
  ArrayList<Vertex> aV4 = new ArrayList<Vertex>();
  ArrayList<ArrayList<Vertex>> aVFinal = new ArrayList<ArrayList<Vertex>>();
  ArrayList<ArrayList<Vertex>> aVCopy = new ArrayList<ArrayList<Vertex>>();

  // for neighbors
  ArrayList<Vertex> aVN0 = new ArrayList<Vertex>();
  ArrayList<Vertex> aVN1 = new ArrayList<Vertex>();
  ArrayList<Vertex> aVN2 = new ArrayList<Vertex>();
  ArrayList<ArrayList<Vertex>> aVN = new ArrayList<ArrayList<Vertex>>();
  // for neighbors 2
  ArrayList<Vertex> aVN00 = new ArrayList<Vertex>();
  ArrayList<Vertex> aVN01 = new ArrayList<Vertex>();
  ArrayList<Vertex> aVN02 = new ArrayList<Vertex>();
  ArrayList<ArrayList<Vertex>> aVNB = new ArrayList<ArrayList<Vertex>>();

  // List test lists
  IList<Integer> mTI = new Mt<Integer>();
  IList<Integer> listI1 = new Cons<Integer>(1, new Cons<Integer>(2, 
          new Cons<Integer>(3, new Cons<Integer>(4, mTI))));
  IList<Integer> listI2 = new Cons<Integer>(5, this.listI1);
  IList<Integer> listI2B = new Cons<Integer>(1, new Cons<Integer>(2, 
          new Cons<Integer>(3, new Cons<Integer>(4, new Cons<Integer>(5, mTI)))));

  // Function objects
  ToString tS = new ToString();

  Vertex v1 = new Vertex(0, 0);
  Vertex v2 = new Vertex(0, 0);
  Vertex v3 = new Vertex(0, 0);
  Vertex v4 = new Vertex(0, 0);
  Vertex v5 = new Vertex(0, 0);
  Vertex v6 = new Vertex(0, 0);
  Vertex v7 = new Vertex(0, 0);
  Vertex v8 = new Vertex(0, 0);
  Vertex v9 = new Vertex(0, 0);

  Vertex v01 = new Vertex(0, 0);
  Vertex v02 = new Vertex(0, 0);
  Vertex v03 = new Vertex(0, 0);
  Vertex v04 = new Vertex(0, 0);
  Vertex v05 = new Vertex(0, 0);
  Vertex v06 = new Vertex(0, 0);
  Vertex v07 = new Vertex(0, 0);
  Vertex v08 = new Vertex(0, 0);
  Vertex v09 = new Vertex(0, 0);

  // v1 
  Edge e1to4 = new Edge(v1, v4, 1);
  Edge e1to2 = new Edge(v1, v2, 1);
  // v2
  Edge e2to5 = new Edge(v2, v5, 1);
  Edge e2to3 = new Edge(v2, v3, 1);
  // v3
  Edge e3to6 = new Edge(v3, v6, 1);
  // v4
  Edge e4to7 = new Edge(v4, v7, 1);
  Edge e4to5 = new Edge(v4, v5, 1);
  // v5 
  Edge e5to8 = new Edge(v5, v8, 1);
  Edge e5to6 = new Edge(v5, v6, 1);
  // v6 
  Edge e6to9 = new Edge(v6, v9, 1);
  // v7
  Edge e7to8 = new Edge(v7, v8, 1);
  // v8 
  Edge e8to9 = new Edge(v8, v9, 1);
  IList<Edge> mTE = new Mt<Edge>();
  // column 1
  IList<Edge> l1 = new Mt<Edge>();
  IList<Edge> l2 = new Mt<Edge>();
  IList<Edge> l3 = new Mt<Edge>();
  // column 2
  IList<Edge> l4 = new Mt<Edge>();
  IList<Edge> l5 = new Mt<Edge>();
  IList<Edge> l6 = new Mt<Edge>();
  // column 3
  IList<Edge> l7 = new Mt<Edge>();
  IList<Edge> l8 = new Mt<Edge>();
  IList<Edge> l9 = new Mt<Edge>();



  Edge edgy0 = new Edge(v1, v2, 0);
  Edge edgy1 = new Edge(v1, v2, 1);
  Edge edgy1a = new Edge(v1, v2, 1);
  Edge edgy3 = new Edge(v1, v2, 3);
  Edge edgy4 = new Edge(v1, v2, 4);
  Edge edgy5 = new Edge(v1, v2, 5);

  IList<Edge> unSorted = new Cons<Edge>(edgy3, 
          new Cons<Edge>(edgy1, new Cons<Edge>(edgy0,
                  new Cons<Edge>(edgy1a, new Cons<Edge>(edgy4,
                          new Cons<Edge>(edgy5, new Mt<Edge>()))))));

  IList<Edge> sortedL = new Cons<Edge>(edgy0, 
          new Cons<Edge>(edgy1a, new Cons<Edge>(edgy1,
                  new Cons<Edge>(edgy3, new Cons<Edge>(edgy4,
                          new Cons<Edge>(edgy5, new Mt<Edge>()))))));

  ITST<Edge> lE = new Leaf<Edge>();
  ITST<Edge> bot1 = new TTNode<Edge>(edgy0, lE, lE, lE); 
  ITST<Edge> bot2 = new TTNode<Edge>(edgy1a, lE, lE, lE); 
  ITST<Edge> bot3 = new TTNode<Edge>(edgy5, lE, lE, lE); 
  ITST<Edge> bot4 = new TTNode<Edge>(edgy1, bot1, bot2, lE);
  ITST<Edge> bot5 = new TTNode<Edge>(edgy4, lE, lE, bot3);
  ITST<Edge> bot6 = new TTNode<Edge>(edgy3, bot4, lE, bot5);


  void initialize() {

      this.aV0.clear();
      this.aV0.add(new Vertex(0, 0));
      aV0.get(0).startVert = true;
      aV0.get(0).hasSearchHead = true;
      this.aV0.add(new Vertex(0, 1));
      this.aV0.add(new Vertex(0, 2));
      this.aV0.add(new Vertex(0, 3));
      this.aV0.add(new Vertex(0, 4));
      this.aV1.clear();
      this.aV1.add(new Vertex(1, 0));
      this.aV1.add(new Vertex(1, 1));
      this.aV1.add(new Vertex(1, 2));
      this.aV1.add(new Vertex(1, 3));
      this.aV1.add(new Vertex(1, 4));
      this.aV2.clear();
      this.aV2.add(new Vertex(2, 0));
      this.aV2.add(new Vertex(2, 1));
      this.aV2.add(new Vertex(2, 2));
      this.aV2.add(new Vertex(2, 3));
      this.aV2.add(new Vertex(2, 4));
      this.aV3.clear();
      this.aV3.add(new Vertex(3, 0));
      this.aV3.add(new Vertex(3, 1));
      this.aV3.add(new Vertex(3, 2));
      this.aV3.add(new Vertex(3, 3));
      this.aV3.add(new Vertex(3, 4));
      this.aV4.clear();
      this.aV4.add(new Vertex(4, 0));
      this.aV4.add(new Vertex(4, 1));
      this.aV4.add(new Vertex(4, 2));
      this.aV4.add(new Vertex(4, 3));
      this.aV4.add(new Vertex(4, 4));
      aV4.get(4).endVert = true;
      this.aVFinal.clear();
      this.aVFinal.add(aV0);
      this.aVFinal.add(aV1);
      this.aVFinal.add(aV2);
      this.aVFinal.add(aV3);
      this.aVFinal.add(aV4);



      v1 = new Vertex(0, 0);
      v2 = new Vertex(0, 1);
      v3 = new Vertex(0, 2);
      v4 = new Vertex(1, 0);
      v5 = new Vertex(1, 1); 
      v6 = new Vertex(1, 2);
      v7 = new Vertex(2, 0);
      v8 = new Vertex(2, 1);
      v9 = new Vertex(2, 2);

      // for neighbors
      aVN0.clear();
      aVN0.add(v1);
      aVN0.add(v2);
      aVN0.add(v3);
      aVN1.clear();
      aVN1.add(v4);
      aVN1.add(v5);
      aVN1.add(v6);
      aVN2.clear();
      aVN2.add(v7);
      aVN2.add(v8);
      aVN2.add(v9);
      aVN.clear();
      aVN.add(aVN0);
      aVN.add(aVN1);
      aVN.add(aVN2);

      v01 = new Vertex(0, 0);
      v02 = new Vertex(0, 1);
      v03 = new Vertex(0, 2);
      v04 = new Vertex(1, 0);
      v05 = new Vertex(1, 1);
      v06 = new Vertex(1, 2); 
      v07 = new Vertex(2, 0);
      v08 = new Vertex(2, 1);
      v09 = new Vertex(2, 2);

      // for neighbors
      aVN00.clear();
      aVN00.add(v01);
      aVN00.add(v02);
      aVN00.add(v03);
      aVN01.clear();
      aVN01.add(v04);
      aVN01.add(v05);
      aVN01.add(v06);
      aVN02.clear();
      aVN02.add(v07);
      aVN02.add(v08);
      aVN02.add(v09);
      aVNB.clear();
      aVNB.add(aVN00);
      aVNB.add(aVN01);
      aVNB.add(aVN02);

      this.aVCopy.clear();
      for(int i = 0; i < aVFinal.size(); i += 1) {
          aVCopy.add(aVFinal.get(i)); 
      }
  }

  // initializes Vertices in aVCopy 
  void initializeV() {
      this.initialize();
      // v1 
      e1to4 = new Edge(v1, v4, 1);
      e1to2 = new Edge(v1, v2, 1);
      // v2
      e2to5 = new Edge(v2, v5, 1);
      e2to3 = new Edge(v2, v3, 1);
      // v3
      e3to6 = new Edge(v3, v6, 1);
      // v4
      e4to7 = new Edge(v4, v7, 1);
      e4to5 = new Edge(v4, v5, 1);
      // v5 
      e5to8 = new Edge(v5, v8, 1);
      e5to6 = new Edge(v5, v6, 1);
      // v6 
      e6to9 = new Edge(v6, v9, 1);
      // v7
      e7to8 = new Edge(v7, v8, 1);
      // v8 
      e8to9 = new Edge(v8, v9, 1);
      mTE = new Mt<Edge>();
      // row 1
      l1 = new Cons<Edge>(e1to4, new Cons<Edge>(e1to2 , mTE));
      l2 = new Cons<Edge>(e2to5, new Cons<Edge>(e1to2, new Cons<Edge>(e2to3, mTE)));
      l3 = new Cons<Edge>(e3to6, new Cons<Edge>(e2to3, mTE));
      // row 2
      l4 = new Cons<Edge>(e1to4, new Cons<Edge>(e4to7, new Cons<Edge>(e4to5, mTE)));
      l5 = new Cons<Edge>(e2to5, new Cons<Edge>(e5to8, new Cons<Edge>(e4to5, 
              new Cons<Edge>(e5to6, mTE))));
      l6 = new Cons<Edge>(e3to6, new Cons<Edge>(e6to9, new Cons<Edge>(e5to6, mTE)));
      // row 3
      l7 = new Cons<Edge>(e4to7, new Cons<Edge>(e7to8, mTE));
      l8 = new Cons<Edge>(e5to8, new Cons<Edge>(e7to8, new Cons<Edge>(e8to9, mTE)));
      l9 = new Cons<Edge>(e6to9, new Cons<Edge>(e8to9, mTE));

      this.v1.edges = l1;
      this.v2.edges = l2;
      this.v3.edges = l3;

      this.v4.edges = l4;
      this.v5.edges = l5;
      this.v6.edges = l6;

      this.v7.edges = l7;
      this.v8.edges = l8;
      this.v9.edges = l9;
  }
  // tests length for the interface IList<T>  
  void testLength(Tester t) {
      t.checkExpect(mTI.length(), 0);
      t.checkExpect(listI1.length(), 4);
      t.checkExpect(listI2.length(), 5);
  }
  // tests addToFront for the interface IList<T> 
  void testAddToFront(Tester t) {
      t.checkExpect(mTI.addToFront(2), new Cons<Integer>(2, mTI));
      t.checkExpect(listI1.addToFront(5), listI2);
  }
  // tests addToBack for the interface IList<T>
  void testAddToBack(Tester t) {
      t.checkExpect(mTI.addToBack(2), new Cons<Integer>(2, mTI));
      t.checkExpect(listI1.addToBack(5), listI2B);
  }
  // tests append for the interface IList<T>
  void testAppend(Tester t) {
      IList<Integer> iz = new Cons<Integer>(1, new Cons<Integer>(3, 
              new Mt<Integer>()));
      t.checkExpect(mTI.append(iz), iz);
      t.checkExpect(listI1.append(iz), new Cons<Integer>(1, new Cons<Integer>(2,
              new Cons<Integer>(3, new Cons<Integer>(4, iz)))));
  }
  // tests isEmpty for the interface IList<T>
  void isEmpty(Tester t) {
      t.checkExpect(this.mTI.isEmpty(), true);
      t.checkExpect(this.listI1.isEmpty(), false);
  }
  // tests contains for the IList interface 
  void testContains(Tester t) {
      IList<Integer> lII = new Cons<Integer>(10, new Cons<Integer>(3, new Cons<Integer>(5, 
              new Cons<Integer>(6, new Cons<Integer>(8, new Cons<Integer>(9, new Mt<Integer>()))))));
      t.checkExpect(new Mt<Integer>().contains(2), false);
      t.checkExpect(lII.contains(11), false);
      t.checkExpect(lII.contains(8), true);
      t.checkExpect(lII.contains(6), true);
      t.checkExpect(lII.contains(9), true);
  }
  // tests list2tree for the interface IList
  void testList2Tree(Tester t) {
      IList<Edge> mTV = new Mt<Edge>();
      Vertex ver1 = new Vertex(0, 0);
      Vertex ver2 = new Vertex(0, 1);
      Edge edy1 = new Edge(ver1, ver2, 2);
      Edge edy2 = new Edge(ver1, ver2, 2);
      Edge edy3 = new Edge(ver1, ver2, 1);
      Edge edy4 = new Edge(ver1, ver2, 3);
      IList<Edge> listest1 = new Cons<Edge>(edy1, new Cons<Edge>(edy2, 
              new Cons<Edge>(edy3, new Cons<Edge>(edy4, mTV))));
      ITST<Edge> l = new Leaf<Edge>();
      ITST<Edge> n1 = new TTNode<Edge>(edy2, l, l, l);
      ITST<Edge> n2 = new TTNode<Edge>(edy3, l, l, l);
      ITST<Edge> n3 = new TTNode<Edge>(edy4, l, l, l);
      ITST<Edge> n4 = new TTNode<Edge>(edy1, n2, n1, n3);
      t.checkExpect(mTV.list2Tree(new CompEdge()), l);
      t.checkExpect(listest1.list2Tree(new CompEdge()), n4);
  }
  // tests sort for the IList interface
  void testSort(Tester t) {
      t.checkExpect(this.unSorted.sort(new CompEdge()), this.sortedL);
  }
  // tests tree2List for the IList interface
  void testTree2(Tester t) {
      /*MazeWorld maze100x60Edge = new MazeWorld(60, 60); TODO uncomment 
 t.checkExpect(this.bot6.tree2List(), this.sortedL);
 ITST<Edge> tree1 = maze100x60Edge.board.list2Tree(new RandEdge());
 Cons<Edge> lister1 = (Cons<Edge>) tree1.tree2List();
 t.checkExpect(lister1.first.from.x, lister1.first.from.x);
 t.checkExpect(lister1.first.from.y, lister1.first.from.y);
 t.checkExpect(lister1.first.to.x, lister1.first.to.x);
 t.checkExpect(lister1.first.to.y, lister1.first.to.y);
 t.checkExpect(this.bot6.tree2List(), this.sortedL);*/
  }
  // tests apply for the function ToString
  void testToString(Tester t) {

      t.checkExpect(tS.apply(2), "2");
      t.checkExpect(tS.apply(-3), "-3");
  }
  // tests next for the IListIterator 
  void testNext(Tester t) {
      IList<Integer> eM = new Mt<Integer>();
      IList<Integer> nonEm = new Cons<Integer>(2, new Cons<Integer>(3, eM));
      IListIterator<Integer> iI = new IListIterator<Integer>(eM);
      IListIterator<Integer> iI2 = new IListIterator<Integer>(nonEm);
      t.checkException(new RuntimeException("there is no next"), iI, "next");
      t.checkExpect(iI2.next(), 2);
      t.checkExpect(iI2.next(), 3);
      t.checkException(new RuntimeException("there is no next"), iI2, "next");
  }
  // tests hasNext for the IListIterator 
  void testHasNext(Tester t) {
      IList<Integer> eM = new Mt<Integer>();
      IList<Integer> nonEm = new Cons<Integer>(2, new Cons<Integer>(3, eM));
      IListIterator<Integer> iI = new IListIterator<Integer>(eM);
      IListIterator<Integer> iI2 = new IListIterator<Integer>(nonEm);
      t.checkExpect(iI.hasNext(), false);
      t.checkExpect(iI2.hasNext(), true);
      iI2.next();
      t.checkExpect(iI2.hasNext(), true);
      iI2.next();
      t.checkExpect(iI2.hasNext(), false);
  }

  // tests remove for the IListIterator 
  void testRemove(Tester t) {
      IList<Integer> eM = new Mt<Integer>();
      IList<Integer> nonEm = new Cons<Integer>(2, new Cons<Integer>(3, eM));
      IListIterator<Integer> iI = new IListIterator<Integer>(eM);
      IListIterator<Integer> iI2 = new IListIterator<Integer>(nonEm);
      t.checkException(new RuntimeException("Do not use this method, please"), iI2, "remove");
      t.checkException(new RuntimeException("Do not use this method, please"), iI, "remove");
  }
  // tests iterator for the IList interface 
  void testIterator(Tester t) {
      IList<Integer> mTTT = new Mt<Integer>();
      IList<Integer> non = new Cons<Integer>(2, new Cons<Integer>(3, mTTT));
      IListIterator<Integer> iII = new IListIterator<Integer>(mTTT);
      IListIterator<Integer> iIII = new IListIterator<Integer>(non);
      t.checkExpect(mTTT.iterator(), iII);
      t.checkExpect(non.iterator(), iIII);
  }
  // tests get for the IList class
  void testGet(Tester t) {
      IList<Integer> mT = new Mt<Integer>();
      IList<Integer> non = new Cons<Integer>(0, 
              new Cons<Integer>(1, new Cons<Integer>(2, mT)));
      t.checkExpect(non.get(0), 0);
      t.checkExpect(non.get(1), 1);
      t.checkExpect(non.get(2), 2);
      t.checkException(new RuntimeException("get cannot be called on Mt"), mT, "get", 1);
  }
  // tests the CompEdge Comparator 
  void testCompEdge(Tester t) {
      
      Vertex v1 = new Vertex(0, 0);
      Vertex v2 = new Vertex(1, 0);
      
      Edge e1 = new Edge(v1, v2, 10);
      Edge e2 = new Edge(v1, v2, 50);
      
      CompEdge cE = new CompEdge();
      
      t.checkExpect(cE.compare(e1, e2), -1);
      t.checkExpect(cE.compare(e2, e1), 1);
      t.checkExpect(cE.compare(e1, e1), 0);
      
  }
  // tests the CompVert Comparator 
  void testCompVert(Tester t) {
      
      this.initialize();
      
      CompVert cV = new CompVert();
      
      t.checkExpect(cV.compare(v1, v2), -1);
      t.checkExpect(cV.compare(v2, v3), -1);
      t.checkExpect(cV.compare(v3, v4), -1);
      
      t.checkExpect(cV.compare(v1, v1), 0);
      t.checkExpect(cV.compare(v2, v2), 0);
      t.checkExpect(cV.compare(v5, v5), 0);
      
      t.checkExpect(cV.compare(v6, v5), 1);
      t.checkExpect(cV.compare(v6, v1), 1);
      
  }
  // tests the RandVert Comparator
  void testRandVert(Tester t) {
      
      this.initialize();
      
      RandVert rV = new RandVert();
      
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      
  }
  // tests the RandEdge Comparator 
  void testRandEdge(Tester t) {
      
      this.initialize();
      
      RandVert rV = new RandVert();
      
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 0);
      t.checkExpect(rV.compare(v1, v2, 10), -1);
      t.checkExpect(rV.compare(v1, v2, 10), 1);
      
  }
  // tests DisplayEdgeVisitor 
  void testDisplayEdgeVisitor(Tester t) {
      
      Vertex v1 = new Vertex(0, 0);
      Vertex v2 = new Vertex(0, 1);
      
      Edge e1 = new Edge(v1, v2, 2);
      
      Mt<Edge> mt = new Mt<Edge>();
      Cons<Edge> cons = new Cons<Edge>(e1, mt );
      
      Leaf<Edge> l = new Leaf<Edge>();
      TTNode<Edge> n = new TTNode<Edge>(e1, l, l, l);
      
      WorldImage leafImg = new LineImage(new Posn(-1, -1),
              new Posn(-1, -1), new Color(255, 255, 255));
      WorldImage nodeImg = new OverlayImages(n.data.displayEdge(false),
              new OverlayImages(leafImg, 
                      new OverlayImages(leafImg, leafImg)));
      WorldImage nodeImg2 = new OverlayImages(n.data.displayEdge(true),
              new OverlayImages(leafImg, 
                      new OverlayImages(leafImg, leafImg)));
      
      
      DisplayEdgeVisitor dEV = new DisplayEdgeVisitor(false);
      DisplayEdgeVisitor dEV2 = new DisplayEdgeVisitor(true);
      
      t.checkException(new IllegalArgumentException("IList is not a valid argument"),
              dEV, "visit", mt);
      t.checkException(new IllegalArgumentException("IList is not a valid argument"),
              dEV, "visit", cons);
      t.checkExpect(dEV.visit(l), leafImg);
      t.checkExpect(dEV.visit(n), nodeImg);
      t.checkExpect(dEV2.visit(n), nodeImg2);

  }
  // tests DisplayWallVisitor 
  void testDisplayWallVisitor(Tester t) {
      
      Vertex v1 = new Vertex(0, 0);
      Vertex v2 = new Vertex(0, 1);
      
      Edge e1 = new Edge(v1, v2, 2);
      
      Mt<Edge> mt = new Mt<Edge>();
      Cons<Edge> cons = new Cons<Edge>(e1, mt );
      
      Leaf<Edge> l = new Leaf<Edge>();
      TTNode<Edge> n = new TTNode<Edge>(e1, l, l, l);
      
      WorldImage leafImg = new LineImage(new Posn(-1, -1),
              new Posn(-1, -1), new Color(255, 255, 255));
      WorldImage nodeImg = new OverlayImages(n.data.displayWall(),
              new OverlayImages(leafImg, 
                      new OverlayImages(leafImg, leafImg)));
      
      DisplayWallVisitor dWV = new DisplayWallVisitor();
      
      t.checkException(new IllegalArgumentException("IList is not a valid argument"),
              dWV, "visit", mt);
      t.checkException(new IllegalArgumentException("IList is not a valid argument"),
              dWV, "visit", cons);
      t.checkExpect(dWV.visit(l), leafImg);
      t.checkExpect(dWV.visit(n), nodeImg);
      
  }
  // tests insert in the ITST interface
  void testInsert(Tester t) {
      IComp<Vertex> comp = new CompVert();
      Vertex c1 = new Vertex(0, 0);
      Vertex c2 = new Vertex(0, 1);
      Vertex c3 = new Vertex(1, 0);
      Vertex c4 = new Vertex(1, 1);
      ITST<Vertex> sC = new Leaf<Vertex>();
      ITST<Vertex> n0 = new TTNode<Vertex>(c4, sC, sC, sC);
      ITST<Vertex> n1 = new TTNode<Vertex>(c2, sC, sC, sC);
      ITST<Vertex> n2 = new TTNode<Vertex>(c3, sC, sC, sC);
      ITST<Vertex> n3 = new TTNode<Vertex>(c1, n1, sC, n2);
      ITST<Vertex> n2a = new TTNode<Vertex>(c3, sC, sC, n0);
      ITST<Vertex> n3a = new TTNode<Vertex>(c1, n1, sC, n2a);
      t.checkExpect(n3.insert(comp, c4), n3a);
  }
  // tests accept for the interfaces IList<T> and ITST<T> 
  void testAccept(Tester t) {
      Vertex v1 = new Vertex(0, 0);
      Vertex v2 = new Vertex(0, 1);
      Vertex v3 = new Vertex(1, 0);
      Edge e1 = new Edge(v1, v2, 0);
      Edge e2 = new Edge(v1, v3, 0);
      Mt<Edge> mT = new Mt<Edge>();
      Cons<Edge> cons = new Cons<Edge>(e1, mT);
      Leaf<Edge> leaf = new Leaf<Edge>();
      TTNode<Edge> node1 = new TTNode<Edge>(e1, leaf, leaf, leaf);
      TTNode<Edge> node2 = new TTNode<Edge>(e2, node1, leaf, leaf);
      DisplayEdgeVisitor dEV = new DisplayEdgeVisitor(true);
      t.checkExpect(leaf.accept(dEV), dEV.visit(leaf));
      t.checkExpect(node2.accept(dEV), dEV.visit(node2));
      t.checkException(
              new IllegalArgumentException("IList is not a valid argument"), cons, "accept", dEV);
      t.checkException(
              new IllegalArgumentException("IList is not a valid argument"), mT, "accept", dEV);
  }  
  // tests displayEdge in the class Edge 
  void testDisplayEdge(Tester t) {
      Vertex vA = new Vertex(0, 0);
      Vertex vB = new Vertex(1, 0);
      Vertex vC = new Vertex(1, 1);
      Edge eA = new Edge(vA, vB,  0);
      Edge eB = new Edge(vB, vC, 3);
      // horizontally connected
      t.checkExpect(eA.displayEdge(true), new LineImage(new Posn(5, 5), 
              new Posn(15, 5), new Color(255, 0, 0)));
      t.checkExpect(eA.displayEdge(false), new OverlayImages(vA.displayCell(), vB.displayCell()));
      // vertically connected
      t.checkExpect(eB.displayEdge(true), new LineImage(new Posn(15, 5), 
              new Posn(15, 15), new Color(255, 0, 0)));
      t.checkExpect(eB.displayEdge(false), new OverlayImages(vB.displayCell(), vC.displayCell()));
  }
  // tests displayWall in the class Edge 
  void testDisplayWall(Tester t) {
      Vertex vA = new Vertex(0, 0);
      Vertex vB = new Vertex(1, 0);
      Vertex vC = new Vertex(1, 1);
      Edge eA = new Edge(vA, vB,  0);
      Edge eB = new Edge(vB, vC, 3);
      // horizontally connected
      t.checkExpect(eA.displayWall(), new LineImage(new Posn(10, 0), new Posn(10, 10),
              new Color(90, 100, 90)));
      // vertically connected
      t.checkExpect(eB.displayWall(), new LineImage(new Posn(10, 10), new Posn(20, 10),
              new Color(90, 100, 90)));
  }  
  // tests displayCell in the class Vertex
  void testDisplayCell(Tester t) {
      Vertex vA = new Vertex(0, 0);
      Vertex vB = new Vertex(0, 1);
      vB.correctPath = true;
      Vertex vC = new Vertex(1, 1);
      vC.wasSearched = true;
      t.checkExpect(vA.displayCell(), new RectangleImage(new Posn(5, 5), 10, 10, new Color(205, 205, 205)));
      t.checkExpect(vB.displayCell(), new RectangleImage(new Posn(5, 15), 10, 10, new Color(65, 86, 197)));
      t.checkExpect(vC.displayCell(), new RectangleImage(new Posn(15, 15), 10, 10, new Color(56, 176, 222)));
  }
  // initializes the testSearch method
  void initializeN() {

      this.A = new Vertex(0, 0);
      this.B = new Vertex(1, 0);
      this.C = new Vertex(2, 0);
      this.D = new Vertex(0, 1);
      this.E = new Vertex(1, 1);
      this.F = new Vertex(2, 1);

      this.E.addEdge(C, 15);
      this.C.addEdge(D, 25);
      this.A.addEdge(B, 30);
      this.B.addEdge(E, 35);
      this.F.addEdge(D, 50);
      this.A.addEdge(E, 50);

  }
  // tests findNeighbors in the class Vertex
  void testFindNeighbors(Tester t) {
      this.initializeN();
      t.checkExpect(this.A.findNeighbors(), new Cons<Vertex>(this.B, new Cons<Vertex>(this.E, new Mt<Vertex>())));
      t.checkExpect(this.B.findNeighbors(), new Cons<Vertex>(this.A, new Cons<Vertex>(this.E, new Mt<Vertex>())));
      t.checkExpect(this.C.findNeighbors(), new Cons<Vertex>(this.E, new Cons<Vertex>(this.D, new Mt<Vertex>())));
  }
  // tests addEdge for the class Vertex
  void testAddEdge(Tester t) {
      this.initialize();
      t.checkExpect(v01.edges, new Mt<Edge>());
      v01.addEdge(v02, 1);
      t.checkExpect(v01.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), new Mt<Edge>()));
      t.checkExpect(v02.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), new Mt<Edge>()));
      t.checkExpect(v03.edges, new Mt<Edge>());
      v01.addEdge(v03, 1);
      t.checkExpect(v02.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), new Mt<Edge>()));
      t.checkExpect(v01.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), 
              new Cons<Edge>(new Edge(v01, v03, 1), new Mt<Edge>())));
      t.checkExpect(v03.edges, new Cons<Edge>(new Edge(v01, v03, 1), new Mt<Edge>()));
      v03.addEdge(v02, 1);
      t.checkExpect(v01.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), new Cons<Edge>(new Edge(v01, v03, 1), 
                      new Mt<Edge>())));
      t.checkExpect(v02.edges, new Cons<Edge>(
              new Edge(v01, v02, 1), new Cons<Edge>(new Edge(v03, v02, 1),
                      new Mt<Edge>())));
      t.checkExpect(v03.edges, new Cons<Edge>(
              new Edge(v01, v03, 1), new Cons<Edge>(new Edge(v03, v02, 1),
                      new Mt<Edge>())));
      t.checkExpect(v01.edges.length(), 2);
      t.checkExpect(v01.edges.length(), 2);
      t.checkExpect(v01.edges.length(), 2);
  }
  // tests createGrid for the class MazeWorld
  void testCreateGrid(Tester t) {
      this.initialize();
      t.checkExpect(maze5.createGrid(), this.aVFinal);
      t.checkExpect(maze0.createGrid(), new ArrayList<ArrayList<Vertex>>());
  }
  // tests addEdges for the class MazeWorld 
  void testAddEdges(Tester t) {
      this.initialize();
      this.initializeV();
      this.maze2.addEdges(aVNB, 1);
      t.checkExpect(this.aVNB.get(0).get(0).edges.length(), 2);
      t.checkExpect(this.aVNB.get(0).get(1).edges.length(), 3);
      t.checkExpect(this.aVNB.get(0).get(2).edges.length(), 2);
      t.checkExpect(this.aVNB.get(1).get(0).edges.length(), 3);
      t.checkExpect(this.aVNB.get(1).get(1).edges.length(), 4);
      t.checkExpect(this.aVNB.get(1).get(2).edges.length(), 3);
      t.checkExpect(this.aVNB.get(2).get(0).edges.length(), 2);
      t.checkExpect(this.aVNB.get(2).get(1).edges.length(), 3);
      t.checkExpect(this.aVNB.get(2).get(2).edges.length(), 2);
      t.checkExpect(this.aVNB, aVN);
  }
  // tests vertexToEdge in MazeWorld 
  void testVertexToEdge(Tester t) {

      this.initialize();
      this.initializeV();

      IList<Edge> answer = new Cons<Edge>(e8to9, new Cons<Edge>(e7to8,
              new Cons<Edge>(e6to9, new Cons<Edge>(e5to6, new Cons<Edge>(
                      e5to8, new Cons<Edge>(e4to5, new Cons<Edge>(e4to7,
                              new Cons<Edge>(e3to6, new Cons<Edge>(e2to3,
                                      new Cons<Edge>(e2to5, new Cons<Edge>(
                                              e1to2, new Cons<Edge>(e1to4,
                                                      new Mt<Edge>(
                                                              )))))))))))));
      
      t.checkExpect(maze0.vertexToEdge(aVN), answer);

  }
  // tests IListToArr in the class MazeWorld
  void testIListToArr(Tester t) {

      IList<Integer> testList = new Mt<Integer>();
      ArrayList<Integer> answer = new ArrayList<Integer>();

      for(int i = 0; i < 100; i += 1) {
          testList = testList.addToBack(i);
          answer.add(i);
      }

      t.checkExpect(maze0.iListToArr(testList), answer);
  }
  // tests makeImage for the MazeWorld class TODO
  void testMakeImage(Tester t) {

  }
  // tests onKeyEvent for the MazeWorld class
  void testOnKeyEvent(Tester t) {
      
      MazeWorld testMaze = new MazeWorld(5, 5);
      
      testMaze.onKeyEvent("r");
     // t.checkExpect(testMaze, new MazeWorld(5, 5));// TODO random maze...?
      
      testMaze.onKeyEvent("m");
      t.checkExpect(testMaze.gameMode == 0);
      
      testMaze.onKeyEvent("e");
      t.checkExpect(testMaze.isPaused);
      
      testMaze.onKeyEvent("d");
      t.checkExpect(testMaze.gameMode == 1);
      
      testMaze.onKeyEvent("b");
      t.checkExpect(testMaze.gameMode == 2);
      
  }
  // tests onTick for the MazeWorld class TODO
  void testOnTick(Tester t) {
      
  }
  // tests initializeHashMap for the class UnionFind 
  void testInitializeHashMap(Tester t) {
      HashMap<Vertex, Vertex> hashy = new HashMap<Vertex, Vertex>();
      Vertex vertA = new Vertex(0, 0);
      Vertex vertB = new Vertex(0, 1);
      Vertex vertC = new Vertex(1, 0);
      Vertex vertD = new Vertex(1, 1);
      ArrayList<ArrayList<Vertex>> aAV = new ArrayList<ArrayList<Vertex>>();
      ArrayList<Vertex> aV = new ArrayList<Vertex>();
      aV.add(vertA);
      aV.add(vertB);
      aV.add(vertC);
      aV.add(vertD);
      aAV.add(aV);
      UnionFind uF = new UnionFind(aAV, new Mt<Edge>());
      uF.reps = new HashMap<Vertex, Vertex>();
      t.checkExpect(uF.reps, new HashMap<Vertex, Vertex>());
      hashy.put(vertA, vertA);
      hashy.put(vertB, vertB);
      hashy.put(vertC, vertC);
      hashy.put(vertD, vertD);
      uF.initializeHashMap();
      t.checkExpect(uF.reps, hashy);

  }
  // tests find for the class UnionFind 
  void testFind(Tester t) {
      Vertex vertA = new Vertex(0, 0);
      Vertex vertB = new Vertex(0, 1);
      Vertex vertC = new Vertex(1, 0);
      Vertex vertD = new Vertex(1, 1);
      ArrayList<ArrayList<Vertex>> aAV = new ArrayList<ArrayList<Vertex>>();
      ArrayList<Vertex> aV = new ArrayList<Vertex>();
      aV.add(vertA);
      aV.add(vertB);
      aV.add(vertC);
      aV.add(vertD);
      aAV.add(aV);
      UnionFind uF = new UnionFind(aAV, new Mt<Edge>());
      t.checkExpect(uF.find(vertA), vertA);
      t.checkExpect(uF.find(vertB), vertB);
      uF.reps.put(vertA, vertB);
      t.checkExpect(uF.find(vertA), vertB);
      uF.reps.put(vertB, vertD);
      t.checkExpect(uF.find(vertA), vertD);
  }
  // tests union for the class UnionFind 
  void testUnion(Tester t) {
      Vertex vertA = new Vertex(0, 0);
      Vertex vertB = new Vertex(0, 1);
      Vertex vertC = new Vertex(1, 0);
      Vertex vertD = new Vertex(1, 1);
      ArrayList<ArrayList<Vertex>> aAV = new ArrayList<ArrayList<Vertex>>();
      ArrayList<Vertex> aV = new ArrayList<Vertex>();
      aV.add(vertA);
      aV.add(vertB);
      aV.add(vertC);
      aV.add(vertD);
      aAV.add(aV);
      UnionFind uF = new UnionFind(aAV, new Mt<Edge>());
      t.checkExpect(uF.find(vertA), vertA);
      t.checkExpect(uF.find(vertB), vertB);
      uF.union(vertA, vertB);
      t.checkExpect(uF.find(vertA), vertB);
      uF.union(vertB, vertD);
      t.checkExpect(uF.find(vertA), vertD);
  }
  // tests formsCycle for the class UnionFind 
  void testformsCycle(Tester t) {
      Vertex vertA = new Vertex(0, 0);
      Vertex vertB = new Vertex(0, 1);
      Vertex vertC = new Vertex(1, 0);
      Vertex vertD = new Vertex(1, 1);
      Vertex vertE = new Vertex(2, 0);
      Vertex vertF = new Vertex(2, 1);
      ArrayList<ArrayList<Vertex>> aAV = new ArrayList<ArrayList<Vertex>>();
      ArrayList<Vertex> aV = new ArrayList<Vertex>();
      aV.add(vertA);
      aV.add(vertB);
      aV.add(vertC);
      aV.add(vertD);
      aV.add(vertE);
      aV.add(vertF);
      aAV.add(aV);
      UnionFind uF = new UnionFind(aAV, new Mt<Edge>());
      uF.union(vertC, vertA);
      uF.union(vertB, vertC);
      uF.union(vertD, vertE);
      t.checkExpect(uF.formsCycle(vertA, vertA), true);
      t.checkExpect(uF.formsCycle(vertB, vertC), true);
      t.checkExpect(uF.formsCycle(vertA, vertD), false);
      t.checkExpect(uF.formsCycle(vertB, vertE), false);
      t.checkExpect(uF.formsCycle(vertD, vertE), true);
  }
  // tests Kruskel for the class UnionFind TODO
  void testKruskel(Tester t) {
      
      Vertex A = new Vertex(0, 0);
      Vertex B = new Vertex(1, 0);
      Vertex C = new Vertex(2, 0);
      Vertex D = new Vertex(0, 1);
      Vertex E = new Vertex(1, 1);
      Vertex F = new Vertex(2, 1);
      ArrayList<Vertex> aV = new ArrayList<Vertex>();
      ArrayList<Vertex> aV2 = new ArrayList<Vertex>();
      ArrayList<Vertex> aV3 = new ArrayList<Vertex>();
      aV.add(A);
      aV.add(B);
      aV.add(C);
      aV.add(D);
      aV.add(E);
      aV.add(F);
      ArrayList<ArrayList<Vertex>> aAV = new ArrayList<ArrayList<Vertex>>();
      aAV.add(aV);
      
      Edge ec = new Edge(E, C, 15);
      Edge cd = new Edge(C, D, 25);
      Edge ab = new Edge(A, B, 30);
      Edge be = new Edge(B, E, 35);
      Edge bc = new Edge(B, C, 40);
      Edge fd = new Edge(F, D, 50);
      Edge ae = new Edge(A, E, 55);
      Edge bf = new Edge(B, F, 60);

      IList<Edge> edgeList = new Mt<Edge>();
      edgeList = edgeList.addToBack(ec);
      edgeList = edgeList.addToBack(cd);
      edgeList = edgeList.addToBack(ab);
      edgeList = edgeList.addToBack(be);
      edgeList = edgeList.addToBack(bc);
      edgeList = edgeList.addToBack(fd);
      edgeList = edgeList.addToBack(ae);
      edgeList = edgeList.addToBack(bf);

      IList<Edge> answer = new Cons<Edge>(fd, new Cons<Edge>(be,
              new Cons<Edge>(ab, new Cons<Edge>(cd, 
                      new Cons<Edge>(ec,
                              new Mt<Edge>())))));
      UnionFind uF = new UnionFind(aAV, edgeList);
      t.checkExpect(uF.kruskel(), answer);

  }
  
  // Tests the remove method in the interface IList<T>
  void testRemoveIList(Tester t) {
      
      String a = "A";
      String b = "B";
      String c = "C";
      
      IList<String> l1 = new Cons<String>(a, new Cons<String>(b,
              new Cons<String>(c, new Mt<String>())));
      IList<String> answer = new Cons<String>(b, new Cons<String>(c,
              new Mt<String>()));
      
      t.checkExpect(l1.remove(a), answer);
      t.checkExpect(answer.remove(c), new Cons<String>(b, new Mt<String>()));
      
  }
  
  // Tests the reconstruct method in the class MazeWorld
  void testReconstruct(Tester t) {
      
      Vertex v1 = new Vertex(0, 0);
      Vertex v2 = new Vertex(1, 0);
      Vertex v3 = new Vertex(2, 0);
      Vertex v4 = new Vertex(0, 1);
      Vertex v5 = new Vertex(1, 1);
      Vertex v6 = new Vertex(2, 1);
      
      Edge e1 = new Edge(v1, v4, 1);
      Edge e2 = new Edge(v4, v5, 2);
      Edge e3 = new Edge(v5, v2, 3);
      Edge e4 = new Edge(v2, v3, 4);
      Edge e5 = new Edge(v3, v6, 5);
      
      v1.edges = new Cons<Edge>(e1, new Mt<Edge>());
      v2.edges = new Cons<Edge>(e1, new Cons<Edge>(e2,  new Mt<Edge>()));
      v3.edges = new Cons<Edge>(e2, new Cons<Edge>(e3, new Mt<Edge>()));
      v4.edges = new Cons<Edge>(e3, new Cons<Edge>(e4, new Mt<Edge>()));
      v5.edges = new Cons<Edge>(e4, new Cons<Edge>(e5, new Mt<Edge>()));
      v6.edges = new Cons<Edge>(e5, new Mt<Edge>());
      
      HashMap<Vertex, Edge> cameFromEdge = new HashMap<Vertex, Edge>();
      cameFromEdge.put(v2, e1);
      cameFromEdge.put(v3, e2);
      cameFromEdge.put(v4, e3);
      cameFromEdge.put(v5, e4);
      cameFromEdge.put(v6, e5);
      
      // Path should run v1 -> v4 -> v5 -> v2 -> v3 -> v6
      IList<Vertex> answer = new Cons<Vertex>(v1, new Cons<Vertex>(v4,
              new Cons<Vertex>(v5, new Cons<Vertex>(v2, new Cons<Vertex>(
                      v3, new Cons<Vertex>(v6, new Mt<Vertex>()))))));
      
      
  }
  
  // runs the animation
  void testRunMaze(Tester t) {
      MazeWorld maze100x60 = new MazeWorld(25, 15);
      //maze100x60.gameMode = 0;
      /* t.checkExpect(maze2.board.length(), 3);
      t.checkExpect(this.maze0.board.length(), 0);
      t.checkExpect(this.maze2.board.length(), 3);
      t.checkExpect(this.maze3.board.length(), 8);
      t.checkExpect(maze100x60.board.length(), 5999); */
      maze100x60.bigBang(1000, 600, .000001);
      //t.checkExpect(maze100x60.searchHeads.length(), 1);
  }
}