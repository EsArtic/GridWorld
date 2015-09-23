import info.gridworld.actor.Bug;
import java.util.Arrays;

/**********************************************************************************************
 * An extended class of Bug, move in a random pattern decide by an array                      *
 * The integer entries in the array represent how many times the bug turns before it moves    *
 * After carrying out the last turn in the array, it start again with the initial array value *
 **********************************************************************************************/
public class DancingBug extends Bug {
    private int[] array;
    private int index;

    // Constructs a Dancing bug that moves follow the array entries
    // @param guide is the reference of the array
    public DancingBug(int[] guide) {
        index = 0;
        array = Arrays.copyOf(guide, guide.length);
    }

    public void nTurn(int times) {
        for (int i = 0; i < times; i++) {
            turn();
        }
    }
    
    // Move to the next location
    public void act() {
        if (canMove()) {
            move();
        }
        nTurn(array[index]);
        index++;
        index %= array.length;
    }
}
