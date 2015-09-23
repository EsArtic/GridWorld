package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/*********************************************************
 *     A Jumper is an actor that can move, jump and turn *
 * It drops flowers as it moves                          *
 *********************************************************/
public class Jumper extends Actor {    
    // Constructs a yellow jumper
    public Jumper() {
        setColor(Color.YELLOW);
    }

    // Constructs a jumper of a given color
    // @param jumper Color is the color for this jumper
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }

    // Jumps if it can Jumper
    // else, moves if it can move, turns otherwise
    public void act() {
        if (canJump()) {
            jump();
        } else if (canMove()) {
            move();
        } else {
            turn();
        }
    }

    // Turns the jumper 45 degrees to the right without changing its location
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    // Moves the bug forward two cells
    // putting a flower into the location it previously occupied
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }

        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location second = next.getAdjacentLocation(getDirection());

        if (gr.isValid(second)) {
            moveTo(second);
        } else {
            removeSelfFromGrid();
        }

        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    
    // Moves the bug forward putting a flower into the location it previously occupied
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    // Tests whether this bug can jump forward into a location that is empty or
    // contains a flower
    // @return true if this bug can jump
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }

        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location second = next.getAdjacentLocation(getDirection());

        if (!gr.isValid(second)) {
            return false;
        }

        Actor neighbor = gr.get(second);
        return ((neighbor == null) || (neighbor instanceof Flower));
        // ok to jump into empty location or onto flower
        // not ok to jump onto any other actor
    }

    // Tests whether this bug can move forward into a location that is empty or
    // contains a flower
    // @return true if this bug can move
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return ((neighbor == null) || (neighbor instanceof Flower));
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}