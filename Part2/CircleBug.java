import info.gridworld.actor.Bug;

// An extended class of Bug
// traces out a positive eight edge shape("circle") of a given size
public class CircleBug extends Bug {
    private int steps;
    private int sideLength;
    
    // Constructs a circle bug that traces a circle(positive eight edge shape) of a given side length
    // @param length is the vlaue of sideLength
    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
    }

    // Moves to the next location of the circle(positive eight edge shape)
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}
