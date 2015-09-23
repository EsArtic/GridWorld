import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid grid = getGrid();
        Location loc = getLocation();

        int[] dirs = { Location.LEFT, Location.RIGHT};
        
        Location firstStep, secondStep;

        firstStep = loc.getAdjacentLocation(getDirection()+dirs[0]);
        if (grid.isValid(firstStep) && (grid.get(firstStep) == null)) {
            secondStep = firstStep.getAdjacentLocation(getDirection()+dirs[0]);
            if (grid.isValid(secondStep) && (grid.get(secondStep) == null)) {
                locs.add(secondStep);
            }
        }
        
        firstStep = loc.getAdjacentLocation(getDirection()+dirs[1]);
        if (grid.isValid(firstStep) && (grid.get(firstStep) == null)) {
            secondStep = firstStep.getAdjacentLocation(getDirection()+dirs[1]);
            if (grid.isValid(secondStep) && (grid.get(secondStep) == null)) {
                locs.add(secondStep);
            }
        }
        
        if (locs.size() == 0) {
            return super.getMoveLocations();
        }
        
        return locs;
    }
}
