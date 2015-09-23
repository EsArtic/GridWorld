import info.gridworld.actor.Bug;

// An extended class of Bug
// drops flowers in a spiral pattern with given size
public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;
    
    // Constructs a spiral bug that traces in a spiral pattern of a given side length
    // @param length is the value of sideLength
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }
    
    //Moves to the next location of the spiral
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            sideLength++;
            turn();
            turn();
            steps = 0;
        }
    }
}
