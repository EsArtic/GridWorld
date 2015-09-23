// The linked list node of the sparse array in sparseGrid
// Each linked list entry holds both a grid occupant and a column index
// Each entry in the array list is a linked list or is null if that row is empty
public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    // Default constructor
    public SparseGridNode() {
        occupant = null;
        next = null;
    }
    
    // constructor
    public SparseGridNode(Object obj, int cols) {
        occupant = obj;
        col = cols;
        next = null;
    }
    
    // methods to get and set the variable members
    public SparseGridNode getNext() {
        return next;
    }   
    public int getCol() {
        return col;
    }  
    public Object getObject() {
        return occupant;
    }   
    public void setObject(Object obj) {
        occupant = obj;
    }   
    public void setCol(int cols) {
        col = cols;
    }   
    public void setNext(SparseGridNode newNode) {
        next = newNode;
    }
}

