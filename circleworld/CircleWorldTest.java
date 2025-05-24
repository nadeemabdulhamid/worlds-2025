import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import processing.event.*;

class CircleWorldTest {

    CircleWorld cw1 = new CircleWorld(100, 150);
    
    @Test
    void tests() {        
        assertEquals("[100.0, 150.0]", cw1.toString());
        
        assertEquals(new CircleWorld(100, 150.5), cw1.update());
        assertEquals(new CircleWorld(100, 151), cw1.update().update());
        
        assertEquals(new CircleWorld(200, 135), 
        			 cw1.mousePressed(new MouseEvent(null, 1, 0, 0, 200, 135, 0, 1)));
    }

}
