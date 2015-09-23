import info.gridworld.actor.*;

import java.util.ArrayList;


/********************************************************
 *   A RockHound gets the actors to be processed in the *
 * same way as a Critter.                               *
 *   It removes any rocks in that list from the grid.   *
 ********************************************************/
public class RockHound extends Critter {
    
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }

}
