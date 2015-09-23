import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains Z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug bob = new ZBug(5);
        world.add(new Location(2, 2), bob);
        world.show();
    }
}
