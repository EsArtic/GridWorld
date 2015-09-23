import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Jumper;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains a jumper and a rock.
 * Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants
 */
public final class JumperRunner {
    private JumperRunner() {
    }
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 5), new Rock());
        Jumper bob = new Jumper();
        world.add(new Location(5, 5), bob);
        world.show();
    }
}
