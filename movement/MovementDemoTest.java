import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import java.awt.Color;

class MovementDemoTest {
	Color blue = new Color(0, 0, 255);
	Color red = new Color(255, 0, 0);
	MovementDemoWorld w1 = new MovementDemoWorld(new Thing(new Posn(50, 50), blue), new Thing(new Posn(100, 100), red));
	MovementDemoWorld w2 = new MovementDemoWorld(new Thing(new Posn(50, 50), new Posn(0, 5), blue),
												 new Thing(new Posn(100, 100), new Posn(-5, 5), red));
	MovementDemoWorld w2next = new MovementDemoWorld(new Thing(new Posn(50, 55), new Posn(0, 5), blue),
												     new Thing(new Posn(95, 105), new Posn(-5, 5), red));
	

	@Test
	void testUpdate() {
		assertEquals(w1, w1.update());
		assertEquals(w2next, w2.update());
	}
	
	@Test
	void testKeyPressedReleased() {
		assertEquals(new MovementDemoWorld(new Thing(new Posn(50, 50), new Posn(0, 5), blue), 
											new Thing(new Posn(100, 100), red)),
					w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 's', 's')));
		
		assertEquals(w2, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 's', 's'))
							.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.LEFT))
							.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.DOWN)));

		// undo effects...
		assertEquals(w1, w2.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, 's', 's'))
				.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, '\0', PApplet.LEFT))
				.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, '\0', PApplet.DOWN)));
		
		// ignore other keys
		assertEquals(w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'z', 'z')));
		assertEquals(w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.SHIFT)));
	}
	

}
