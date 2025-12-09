import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {
    
    @Test
    public void testHelloWorld() {
        HelloWorld hw = new HelloWorld();
        assertNotNull(hw);
    }
    
    @Test
    public void testMain() {
        HelloWorld.main(new String[]{});
        assertTrue(true);
    }
}
