import javalib.worldimages.Posn;

class MoveVertex {
    Vertex current;
    boolean hasDir;
    MoveVertex(Vertex v) {
        this.current = v;
        hasDir = false;
    }
    // checks to see if two Posn's are equal
    boolean equalPosn(Posn p1, Posn p2) {
        return (p1.x == p2.x) && (p1.y == p2.y);
    }
    // checks each of the 
    Vertex move(String dir) {
        Vertex result = this.current;
        int pX = this.current.getX();
        int pY = this.current.getY();
        Posn comp = null;
        // picks the correct posn
        if (dir.equals("up")) {
            comp = new Posn(pX, pY - 1);
        }
        else if (dir.equals("down")) {
            comp = new Posn(pX, pY + 1);
        }
        else if (dir.equals("left")) {
            comp = new Posn(pX - 1, pY);
        }
        else if (dir.equals("right")) {
            comp = new Posn(pX + 1, pY);
        }
        else {
            throw new IllegalArgumentException("dir is not a direction");
        }
        for (Vertex v: this.current.findNeighbors()) {
            if (this.equalPosn(v.posn, comp)) {
                result = v;
                this.hasDir = true;
            }
        }
        return result;
    }
}