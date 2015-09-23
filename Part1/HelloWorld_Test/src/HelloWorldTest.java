import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void testHello() {
		assertEquals("Hello World!", HelloWorld.getStr());
	}
}