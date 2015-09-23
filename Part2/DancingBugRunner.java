import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class DancingBugRunner
{
    public static void main(String[] args)
    {
        int[] list = new int[10];
        for (int i = 0; i < 10; i++) {
            list[i] = (int)(Math.random()*11);
        }
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(list);
        alice.setColor(Color.ORANGE);
        world.add(new Location(5, 5), alice);
        world.show();
    }
}
