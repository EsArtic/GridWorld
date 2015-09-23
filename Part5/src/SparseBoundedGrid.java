import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/***********************************************************************************
 * SparseBounedGrid use a "sparse array" to store the objects.                     *
 * The "sparse array" is an array list of linked lists.                            *
 * Each linked list entry holds both a grid occupant and a column index.           *
 * Each entry in the array list is a linked list or is null if that row is empty.  *
 ***********************************************************************************/
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private SparseGridNode[] occupantArray;
    private int column;
    private int row;

    // index method can locates the last entry of a list
    // If the head of the list is null, method returns null
    public SparseGridNode index(int r) {
        SparseGridNode temp = occupantArray[r];
        
        if (temp == null) {
            return null;
        }
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        
        // initialize the array list, all entries are null
        occupantArray = new SparseGridNode[rows];
        column = cols;
        row = rows;
    }

    public int getNumRows() {
        return row;
    }

    public int getNumCols() {
        return column;
    }

    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // All the entries in the linked list of the array contains an object
        // traces all the list and put the loication into the ArrayList
        for (int r = 0; r < getNumRows(); r++) {
            SparseGridNode temp = occupantArray[r];
            while (temp != null) {
                Location loc = new Location(r, temp.getCol());
                theLocations.add(loc);
                temp = temp.getNext();
            }
        }

        return theLocations;
    }

    // Get object from given valid location
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        int r = loc.getRow(), c = loc.getCol();
        SparseGridNode temp = occupantArray[r];
        
        while (temp != null) {
            if (temp.getCol() == c) {
                break;
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            return null;
        }
             
        return (E)temp.getObject();
    }

    // Add the object to the grid
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        E oldOccupant = get(loc);
        
        SparseGridNode temp = index(loc.getRow());
        if (temp == null) {
            occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol());
        } else {
            temp.setNext(new SparseGridNode(obj, loc.getCol()));
        }

        return oldOccupant;
    }

    // Remove the object from the grid
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        E r = get(loc);
        
        if (r == null) {
            return null;
        }
        
        SparseGridNode temp = occupantArray[loc.getRow()];
        SparseGridNode before = null;
        
        if (temp.getCol() == loc.getCol()) {
            occupantArray[loc.getRow()] = temp.getNext();
        } else {
            before = temp;
            temp = temp.getNext();
            while (temp != null) {
                if (temp.getCol() == loc.getCol()) {
                    temp = temp.getNext();
                    before.setNext(temp);
                    break;
                }
                temp = temp.getNext();
                before = before.getNext();
            }
        }
        return r;
    }
}

