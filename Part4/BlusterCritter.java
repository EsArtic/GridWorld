import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;


/*************************************************************************************************************
 *  A BlusterCritter looks at all of the neighbors within two steps of its current location.                 *
 *  It counts the number of critters in those locations:                                                     *
 *     If there are fewer than c critters, the BlusterCritter's color gets brighter (color values increase)  *
 *     If there are c or more critters, the BlusterCritter's color darkens (color values decrease)           *
 *************************************************************************************************************/
public class BlusterCritter extends Critter
{
    private int courage;
    
    public BlusterCritter(int number) {
        courage = number;
        setColor(Color.YELLOW);
    }

    // Looks at all of the neighbors within two steps of its current location
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        Location loc = getLocation();

        for (Location neighborLoc : getGrid().getValidAdjacentLocations(loc)) {
            for (Actor actor : getGrid().getNeighbors(neighborLoc)) {
                if (!neighbors.contains(actor)) {
                    neighbors.add(actor);
                }
            }
        }
        return neighbors;
    }

    // Change the color or brighter/darken the color
    public void processActors(ArrayList<Actor> actors) {
        int count = -1;
        for (Actor a : actors) {
            if (a instanceof Critter) {
                count++;
            }
        }

        Color c = getColor();
        int red = (int) (c.getRed());
        int green = (int) (c.getGreen());
        int blue = (int) (c.getBlue());

        if (count < courage) {
            if (red < 255) {
                red++;
            }
            if (green < 255) {
                green++;
            }
            if (blue < 255) {
                blue++;
            }
            
            setColor(new Color(red, green, blue));
        } else {
            if (red > 0) {
                red--;
            }
            if (green > 0) {
                green--;
            }
            if (blue > 0) {
                blue--;
            }
            
            setColor(new Color(red, green, blue));
        }
    }
}
