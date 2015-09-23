import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*********************************************
 * SparseBoundedGrid implemented by hashMap  *
 *********************************************/
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantArray;
    private int column;
    private int row;

    /**************************************************************
     * Constructs an empty bounded grid with the given dimensions *
     * (Precondition: rows > 0 and cols > 0)                      *
     * @param rows number of rows in BoundedGrid                  *
     * @param cols number of columns in BoundedGrid               *
     **************************************************************/
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        column = cols;
        row = rows;
        occupantArray = new HashMap<Location, E>();
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

    // Get all the entries in the map ans put them to the ArrayList
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        for (Location loc : occupantArray.keySet()) {
            theLocations.add(loc);
        }

        return theLocations;
    }

    // Get object from given valid location
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        return (E)occupantArray.get(loc);
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

        occupantArray.remove(loc);
        occupantArray.put(loc, obj);

        return oldOccupant;
    }

    // Remove the object from the grid
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        E r = get(loc);
        occupantArray.remove(loc);
        return r;
    }
}
