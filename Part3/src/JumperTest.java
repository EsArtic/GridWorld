import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.actor.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/******************************************************
 * JumperTest class test five method in Jumper class: *
 * 1) act                                             *
 * 2) jump                                            *
 * 3) move                                            *
 * 4) canJump                                         *
 * 5) canMove                                         *
 ******************************************************/
public class JumperTest {
	
	private static ActorWorld world;
	private static Jumper bob;
	
	// Construct an ActorWorld instance and a jumper instance
	// Place the jumper int location (5, 5) in actorworld
	@BeforeClass
	public static void setUpBeforeClass() {
		world = new ActorWorld();
		bob = new Jumper();
		world.add(new Location(5, 5), bob);
	}

	// Move the jumper to the initial place
	@Before
	public void setUp() throws Exception {
		bob.moveTo(new Location(5, 5));
	}

	// The test method of method Act in Jumper class
	@Test
	public void testAct() {
		Rock rock = new Rock();
		world.add(rock);
		rock.moveTo(new Location(3, 5));
		
		bob.act();
		assertEquals(new Location(4, 5), bob.getLocation());
		
		bob.act();
		assertEquals(new Location(2, 5), bob.getLocation());
		
		bob.act();
		assertEquals(new Location(0, 5), bob.getLocation());
	}

	// The test method of method jump in Jumper class
	@Test
	public void testJump() {
		bob.jump();
		assertEquals(new Location(3, 5), bob.getLocation());
		
		assertEquals(true, world.getGrid().get(new Location(5, 5)) instanceof Flower);
	}

	// The test method of method move in Jumper class
	@Test
	public void testMove() {
		bob.move();
		assertEquals(new Location(4, 5), bob.getLocation());
		
		assertEquals(true, world.getGrid().get(new Location(5, 5)) instanceof Flower);
		
	}

	// The test method of method canJump in Jumper class
	@Test
	public void testCanJump() {
		assertEquals(true, bob.canJump());
		
		Rock rock = new Rock();
		world.add(new Location(3, 5), rock);
		assertEquals(false, bob.canJump());
		rock.removeSelfFromGrid();
		
		Flower flower = new Flower();
		world.add(new Location(3, 5), flower);
		assertEquals(true, bob.canJump());
		flower.removeSelfFromGrid();
		
		Jumper jumper = new Jumper();
		world.add(new Location(3, 5), jumper);
		assertEquals(false, bob.canJump());
		jumper.removeSelfFromGrid();
		
	}

	// The test method of method canMove in Jumper class
	@Test
	public void testCanMove() {
		assertEquals(true, bob.canMove());
		
		Rock rock = new Rock();
		world.add(new Location(4, 5), rock);
		assertEquals(false, bob.canMove());
		rock.removeSelfFromGrid();
		
		Flower flower = new Flower();
		world.add(new Location(4, 5), flower);
		assertEquals(true, bob.canMove());
		flower.removeSelfFromGrid();
		
		Jumper jumper = new Jumper();
		world.add(new Location(4, 5), jumper);
		assertEquals(false, bob.canMove());
		jumper.removeSelfFromGrid();
		
	}
}
