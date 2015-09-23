import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
    public int distance(Location loc1, Location loc2) {
        int x1, x2, y1, y2;
        x1 = loc1.getRow();
        y1 = loc1.getCol();
        x2 = loc2.getRow();
        y2 = loc2.getCol();
        double ans = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2)) + 0.5;
        return (int)Math.floor(ans);
    }
    
    private boolean push(Actor actor) {
        ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(actor.getLocation());
        
        Location ownLoc = actor.getLocation();
        Location current = getLocation();
        int min = distance(ownLoc, current);

        for (Location loc : locs) {
            if (distance(current, loc) > min) {
                actor.moveTo(loc);
                return true;
            }
        }
        
        return false;
    }

    public void processActors(ArrayList<Actor> actors) {
        
        for (Actor a : actors) {
            if (!push(a)) {
                a.removeSelfFromGrid();
            }
        }
    }
}

