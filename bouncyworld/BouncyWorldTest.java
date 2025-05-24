import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

class BouncyWorldTest {

	BouncyWorld w1 = new BouncyWorld(new Posn(50, 50), new Posn(100, 100));
	BouncyWorld w2 = new BouncyWorld(new Posn(50, 50), new Posn(98, 98));
	BouncyWorld w3 = new BouncyWorld(new Posn(50, 50), new Posn(50, 50));
	BouncyWorld w4 = new BouncyWorld(new Posn(100, 50), new Posn(0, 70));

	@Test
	void testUpdate() {
		assertEquals(w2, w1.update());
		assertEquals(w3, w3.update());
		assertEquals(new BouncyWorld(new Posn(100, 50), new Posn(5, 69)),
					 w4.update());
	}
	
	@Test
	void testMousePressed() {
		assertEquals( w3, w1.mousePressed(new MouseEvent(null, 1, 0, 0, 50, 50, 0, 1)) );
		assertEquals( new BouncyWorld(new Posn(100, 50), new Posn(91, 73)), 
					  w4.mousePressed(new MouseEvent(null, 1, 0, 0, 91, 73, 0, 1)) );
	}
	
	@Test
	void testKeyPressed() {
		assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'c', 'c')));
		assertEquals( new BouncyWorld(new Posn(50, 40), new Posn(100, 100)), 
						w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.UP)));
		assertEquals( new BouncyWorld(new Posn(50, 60), new Posn(100, 100)), 
						w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.DOWN)));
		assertEquals( new BouncyWorld(new Posn(60, 50), new Posn(100, 100)), 
						w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.RIGHT)));
		assertEquals( new BouncyWorld(new Posn(40, 50), new Posn(100, 100)), 
						w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.LEFT)));
	}
	

}
