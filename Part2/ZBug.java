import info.gridworld.actor.Bug;

/*******************************************
 * An extended class of Bug                *
 * move in a "Z" pattern                   *
 * ZBug stop moving in these ssituations:  *
 *     1. Method canMove returns false;    *
 *     2. It complete a "Z" pattern        *
 *******************************************/
public class ZBug extends Bug {
    private int steps;
    private int sideLength;
    private boolean right;
    private boolean left;

    // Constructs a Z bug that traces a "Z" of a given side length
    // @param length if the value of sideLength
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        complete = false;
        right = false;
        left = false;
        turn();
        turn();
    }

    // Moves to the next location of the "Z" pattern
    public void act() {
        if (canMove()) {
            if (steps < sideLength && canMove()) {
                move();
                steps++;
            } else {
                if (!right) {
                    turn();
                    turn();
                    turn();
                    steps = 0;
                    right = true;
                }
                else if (!left) {
                    turn();
                    turn();
                    turn();
                    turn();
                    turn();
                    steps = 0;
                    left = true;
                }
            }
        }
    }
}
