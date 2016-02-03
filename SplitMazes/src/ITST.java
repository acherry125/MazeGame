
//represents a Cell Tertiary Tree
interface ITST<T> {
    // inserts the given item into this tree
    ITST<T> insert(IComp<T> comp, T t);
    // accepts a visitor 
    <R> R accept(IVisitor<T, R> v);
    // converts this tree into an IList
    IList<T> tree2List();
}

//represents a known Cell Tertiary Tree
class TTNode<T> implements ITST<T> {
    T data;
    ITST<T> left;
    ITST<T> middle;
    ITST<T> right;
    TTNode(T data, ITST<T> left, ITST<T> middle, ITST<T> right) {
        this.data = data;
        this.left = left;
        this.middle = middle;
        this.right = right;
    }
    // inserts an item into this tree according to the given comparator
    public ITST<T> insert(IComp<T> comp, T t) {
        if (comp.compare(this.data, t) > 0) {
            return new TTNode<T>(this.data, this.left.insert(comp, t), this.middle, this.right);
        }
        else if (comp.compare(this.data, t) < 0) {
            return new TTNode<T>(this.data, this.left, this.middle, this.right.insert(comp, t));
        }
        else {
            return new TTNode<T>(this.data, this.left, this.middle.insert(comp, t), this.right);
        }
    }
    // accepts the given visitor
    public <R> R accept(IVisitor<T, R> v) {
        return v.visit(this);
    }
    // converts this tree into an IList
    public IList<T> tree2List() {
        return this.left.tree2List().append(this.middle.tree2List()).append(
                this.right.tree2List().addToFront(this.data)); 
    }
}

//represents an empty Binary Tree
class Leaf<T> implements ITST<T> {
    // inserts an item into this tree according to the given comparator
    public ITST<T> insert(IComp<T> comp, T t) {
        return new TTNode<T>(t, this, this, this);        
    }
    // accepts the given visitor
    public <R> R accept(IVisitor<T, R> v) {
        return v.visit(this);
    }
    // converts this tree into an IList
    public IList<T> tree2List() {
        return new Mt<T>();
    }
}