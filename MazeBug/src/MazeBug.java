import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/*****************************************
 * A MazeBug can find its way in a maze  *
 *****************************************/
public class MazeBug extends Bug {
    private Location next;
    private Location last;

    // A sign to show note whether the bug reach the destination or not
    private boolean isEnd = false;

    private Integer stepCount = 0;

    // Final message has been shown
    private boolean hasShown = false;

    // Stores the locations that maze bug went through
    private Stack<Location> lastMessage = new Stack<Location>();
    private int[] count = {2, 2, 2, 2};

	// Constructs a maze bug with green color
	public MazeBug() {
		setColor(Color.GREEN);
		last = null;
	}

	// If reach the destination, show the stepCount message
	// Else, move a step
	public void act() {
	    boolean willMove = canMove();
	    if (isEnd == true) {

            //to show step count when reach the goal		
            if (hasShown == false) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();

            //increase step count when move 
            stepCount++;
        } else {
            last = lastMessage.pop();
            next = last;
            back();
            stepCount++;
        }
    }


	// Find the valid location to given location in four directions
	//     (North, East, South, West)
	// @param loc is the location to detect
	// @return List of positions
	public ArrayList<Location> getValid(Location loc) {
	    Grid<Actor> gr = getGrid();
	    if (gr == null) {
	        return null;
	    }
	    ArrayList<Location> valid = new ArrayList<Location>();
	    
	    int[][] direct = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int i = 0; i < 4; i++) {
            Location temp = new Location(loc.getRow()+direct[i][0], loc.getCol()+direct[i][1]);
            if (gr.isValid(temp)) {
                valid.add(temp);
            }
        }
        return valid;
    }

    // Decide the next location
    private int decideNext(ArrayList<Location> locs) {
        int sum = 0, i = 0;
        int[] tmp = new int[locs.size()];
        for (Location l : locs) {
            int dir = getLocation().getDirectionToward(l);
            if (dir == 0) {
                sum += count[0];
                tmp[i] = count[0];
            }
            else if (dir == 90) {
                sum += count[1];
                tmp[i] = count[1];
            }
            else if (dir == 180) {
                sum += count[2];
                tmp[i] = count[2];
            }
            else if (dir == 270) {
                sum += count[3];
                tmp[i] = count[3];
            }
            i++;
        }
        
        int index = (int)(Math.random()*sum);
        i = 0;
        sum = tmp[0];
        while (sum < index) {
            i++;
            sum += tmp[i];
        }
        
        return i;
    }

    // Check the array list returned by getValid method
    // If the bug reach the destination, change the value of isEnd
    // Else, get the next location
    public ArrayList<Location> getNext() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        Location loc = getLocation();
        ArrayList<Location> possibleNext = new ArrayList<Location>();
        ArrayList<Location> valid = getValid(loc);

        for (Location l : valid) {
            Actor neighbor = gr.get(l);

            if ((neighbor instanceof Rock) && (neighbor.getColor().equals(Color.RED))) {
                isEnd = true;
                return possibleNext;
            }
            if (neighbor == null) {
                possibleNext.add(l);
            }
        }

        if (possibleNext.size() > 0) {
            next = possibleNext.get(decideNext(possibleNext));
        }
        return possibleNext;
    }

    // Tests whether this bug can move forward into a location that is empty
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        ArrayList<Location> possibleNext = getNext();
        if (possibleNext.size() == 0) {
            return false;
        }
        return true;
    }

    // Method to operate the value of count array
    private void add(int dir, int entry) {
        if (entry == 1) {
            if (dir == 0) {
                count[0]++;
            }
            else if (dir == 90) {
                count[1]++;
            }
            else if (dir == 180) {
                count[2]++;
            }
            else if (dir == 270) {
                count[3]++;
            }
        }
        if (entry == -1) {
            if (dir == 0) {
                count[2]--;
            }
            else if (dir == 90) {
                count[3]--;
            }
            else if (dir == 180) {
                count[0]--;
            }
            else if (dir == 270) {
                count[1]--;
            }
        }
    }

    // Moves the bug to the next location
    // Putting a flower into the location it previously occupied
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();

        if (gr.isValid(next)) {
            last = getLocation();
		    lastMessage.push(last);

            int direction = getLocation().getDirectionToward(next);
            setDirection(direction);
            add(direction, 1);
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    // The move method to move the bug back to locations with Flowers
    private void back() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }

        Location loc = getLocation();
        if (gr.isValid(next)) {
            int direction = getLocation().getDirectionToward(next);
            setDirection(direction);
            add(direction, -1);
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
