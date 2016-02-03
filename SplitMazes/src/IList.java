import java.util.Iterator;

//represents a list
interface IList<T> extends Iterable<T> {
  // Computes the size of this list
  int length();
  // creates a new list with the given item added to the front
  IList<T> addToFront(T item);
  // creates a new list with the given item added to the front
  IList<T> addToBack(T item);
  // append this list onto the given one
  IList<T> append(IList<T> other);
  // creates a new Tree from this IList
  ITST<T> list2Tree(IComp<T> comp);
  // sorts this list using the given comparator
  IList<T> sort(IComp<T> comp);
  // Is this list empty?
  boolean isEmpty();
  // Accept the given Visitor
  <R> R accept(IVisitor<T, R> v);
  // determines whether the given item is in this list
  boolean contains(T t);
  // gets the nth element of the list
  T get(int n);
  // remove the given element from this list
  // If the given T is not in the list, the list will be unchanged.
  IList<T> remove(T t);
}

//represents a non-empty list
class Cons<T> implements IList<T> {
  T first;
  IList<T> rest;
  Cons(T first, IList<T> rest) {
      this.first = first;
      this.rest = rest;
  }
  // Computes the size of this list
  public int length() {
      int result = 0;
      for(@SuppressWarnings("unused") T t: this) {
          result += 1;
      }
      return result;
  }
  // creates a new list with the given item added to the front
  public IList<T> addToFront(T item) {
      return new Cons<T>(item, this);
  }
  // creates a new list with the given item added to the back
  public IList<T> addToBack(T item) {
      return new Cons<T>(this.first, this.rest.addToBack(item));
  }
  // appends this list onto the given one
  public IList<T> append(IList<T> other) {
      //return new Cons<T>(this.first, this.rest.append(other));
      IList<T> result = this;
      for(T t: other) {
          result = result.addToBack(t);
      }
      return result;
  }
  // creates a new Tree from this IList
  public ITST<T> list2Tree(IComp<T> comp) {
      ITST<T> result = new Leaf<T>();
      for(T t: this) {
          result = result.insert(comp, t);
      }
      return result;
  }
  // sorts this list using the given comparator
  public IList<T> sort(IComp<T> comp) {
      return this.list2Tree(comp).tree2List(); 
  }
  // Is this list empty?
  public boolean isEmpty() { return false; }
  // gets the iterator for this IList
  public Iterator<T> iterator() {
      return new IListIterator<T>(this);
  }
  // accepts a Visitor for this IList
  public <R> R accept(IVisitor<T, R> v) {
      return v.visit(this);
  }
  // determines whether the given item is in this list
  public boolean contains(T t) {
      return this.first == t || this.rest.contains(t);
  }
  // gets the nth element of this list
  public T get(int n) {
      if (this.length() > n) {
      int n1 = n;
      T result = this.first;
      for (T t: this) {
          if (n1 <= 0) {
              return t;
          }
          else {
              n1 -= 1;
          }
      }
      return result;
      }
      else {
          throw new RuntimeException("get cannot be called on Mt");
      }
  }
  
  public IList<T> remove(T t) {
      
      if (this.first == t) {
          return this.rest;
      }
      else {
          return new Cons<T>(this.first, this.rest.remove(t));
      }
      
  }
} 

//represents an empty list
class Mt<T> implements IList<T> {
  // Computes the size of this list
  public int length() {
      return 0;
  }
  // creates a new list with the given item added to the front
  public IList<T> addToFront(T item) {
      return new Cons<T>(item, this);
  }
  // creates a new list with the given item added to the back
  public IList<T> addToBack(T item) {
      return new Cons<T>(item, this);
  }
  // appends this list onto the given one
  public IList<T> append(IList<T> other) {
      return other;
  }
  // creates a new Tree from this IList
  public ITST<T> list2Tree(IComp<T> comp) {
      return new Leaf<T>();
  }    
  // sorts this list using the given comparator
  public IList<T> sort(IComp<T> comp) {
      return this;
  }
  // Is this list empty?
  public boolean isEmpty() { return true; }
  // gets this lists iterator
  public Iterator<T> iterator() {
      return new IListIterator<T>(this);
  }
  // accepts a visitor
  public <R> R accept(IVisitor<T, R> v) {
      return v.visit(this);
  }
  // determines whether the given item is in this list
  public boolean contains(T t) {
      return false;
  }
  // gets the nth element of this list
  public T get(int n) {
      throw new RuntimeException("get cannot be called on Mt");
  }
  public IList<T> remove(T t) {
      return this;
  }
}