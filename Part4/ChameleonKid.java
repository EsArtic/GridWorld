import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/******************************************************************
 *   A ChameleonKid changes its color to the color of one of      *
 * the actors immediately in front or behind.                     *
 *   If there is no actor in either of these locations, then the  *
 * ChameleonKid darkens like the modified ChameleonCritter.       *
 ******************************************************************/
public class ChameleonKid extends ChameleonCritter {
    
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid grid = getGrid();
        Location loc = getLocation();
        
        Location loc1, loc2;
        
        loc1 = loc.getAdjacentLocation(getDirection());
        if (grid.isValid(loc1) && (grid.get(loc1) != null)) {
            Actor temp = (Actor)grid.get(loc1);
            actors.add(temp);
        }
        
        loc2 = loc.getAdjacentLocation((getDirection() + 180) % 360);
        if (grid.isValid(loc2) && (grid.get(loc2) != null)) {
            Actor temp = (Actor)grid.get(loc2);
            actors.add(temp);
        }
        
        return actors;
    }
}
