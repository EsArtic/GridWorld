import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.*;

/*********************************************************************************************
 *   An unbounded grid in which all valid locations have non-negative row and column values. *
 *   The constructor allocates a 16 x 16 array.                                              *
 *   When a call is made to the put method with a row or column index that is outside the    *
 * current array bounds, take these steps:                                                   *
 *     1) double both array bounds until they are large enough                               *
 *     2) construct a new square array with those bounds                                     *
 *     3) place the existing occupants into the new array                                    *
 *********************************************************************************************/
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    private int length;
    private static final int INITIAL_LENGTH = 16;

    // Initially, a 16 x 16 array as a container
    public UnboundedGrid2() {
        length = INITIAL_LENGTH;
        occupantArray = new Object[length][length];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    // UnboundedGrid2 requires all valid locations have non-negative row and column values
    public boolean isValid(Location loc) {
        if ((loc.getRow() >= 0) && (loc.getCol() >= 0)) {
            return true;
        }
        return false;
    }

    // Trace the whole two-dimensional array to ccollect the occupied locations
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    a.add(loc);
                }
            }
        }
        return a;
    }

    // When a call is made to get object outside the current bound, return null
    // ELse, return the object at that location
    public E get(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
            
        if ((length <= loc.getCol()) || (length <= loc.getRow())) {
            return null;
        }
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    // Method to resize the container array
    public void lengthen(int temp) {
        Object[][] tempArray = new Object[length][length];
        
        for (int i = 0; i < temp; i++) {
            for (int j = 0; j < temp; j++) {
                tempArray[i][j] = occupantArray[i][j];
            }
        }
        occupantArray = tempArray;
    }
    
    // When a call is made to put object outside the current bound
    // resize the array
    public E put(Location loc, E obj) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        
        int cols = loc.getCol(), rows = loc.getRow(), temp = length;
        while ((length <= cols) || (length <= rows)) {
            length *= 2;
        }
        
        if (temp == length) {
            E oldOccupant = get(loc);
            occupantArray[rows][cols] = obj;
            return oldOccupant;
        }
        lengthen(temp);
        occupantArray[rows][cols] = obj;

        return null;
    }

    // When a call is made to remove object outside the current bound, return null
    // Else, remove the object at that location and return the object
    public E remove(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc == null");
        }

        if ((length <= loc.getCol()) || (length <= loc.getRow())) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
