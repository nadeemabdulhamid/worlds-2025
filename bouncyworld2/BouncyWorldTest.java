import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

class BouncyWorldTest {

	Blob b1 = new Blob(new Posn(100, 100));
	Blob b2 = new Blob(new Posn(50, 50));
	Anchor a1 = new Anchor(new Posn(102, 102));
	Anchor a2 = new Anchor(new Posn(50, 50));
	Anchor a3 = new Anchor(new Posn(200, 200));
	Anchor a2up = new Anchor(new Posn(50, 40));
	Anchor a2right = new Anchor(new Posn(60, 50));
	Anchor a3down = new Anchor(new Posn(200, 210));
	Anchor a3left = new Anchor(new Posn(190, 200));

	BouncyWorld w0 = new BouncyWorld(a2, a3, b1, true);
	BouncyWorld w1 = new BouncyWorld(a2, a3, b1, false);
	BouncyWorld w2 = new BouncyWorld(a2, a3,
			new Blob(new Posn(100 - 5.0/4, 100 - 5.0/4)), // moves 1/40 towards 50, 50
			false);
	BouncyWorld w2swap = new BouncyWorld(a3, a2,
			new Blob(new Posn(100 - 5.0/4, 100 - 5.0/4)), // moves 1/40 towards 50, 50
			false);

	BouncyWorld w3 = new BouncyWorld(a3, a2, b1, false);  // anchors swapped


	@Test
	void testBlob() {
		// moveTowards
		assertEquals(new Blob(new Posn(100.05, 100.05)),
				b1.moveTowards(a1));
		assertEquals(new Blob(new Posn(102, 102)),
				new Blob(new Posn(102.01f, 101.99f)).moveTowards(a1));
	}


	@Test
	void testAnchor() {
		// translate
		assertEquals(new Anchor(new Posn(105, 90)),
				a1.translate(new Posn(3, -12)));

		// distanceTo
		assertEquals(5, a1.distanceTo(new Posn(105, 98)));
		assertEquals(10, a1.distanceTo(new Posn(112, 102)));
	}


	@Test
	void testCloserAnchor() {
		assertEquals( a2, w1.closerAnchor(new Posn(100, 100)) );
		assertEquals( a3, w1.closerAnchor(new Posn(150, 270)) );
		assertEquals( a2, w1.closerAnchor(new Posn(-150, 270)) );
	}


	@Test
	void testUpdate() {
		assertEquals(w0, w0.update());		 // paused - no change
		
		assertEquals(w2, w1.update());			// blob moves towards closest
		assertEquals(w2swap, w3.update());
		
		assertEquals(new BouncyWorld(a2, a3, b2, false),   // b2 already at closest anchor
					 new BouncyWorld(a2, a3, b2, false)); 

		assertEquals(new BouncyWorld(a3, a2, b2, false),   // b2 already at closest anchor
				 new BouncyWorld(a3, a2, b2, false)); 
	}

	@Test 
	void testMousePressed() {
		assertEquals(new BouncyWorld(a2, a3, new Blob(new Posn(91, 73)), false),
				w2.mousePressed(new MouseEvent(null, 1, 0, 0, 91, 73, 0, 1)));
	}

	@Test
	void testKeyPressed() {

		// move the active (first) anchor
		assertEquals( new BouncyWorld(a2up, a3, b1, false), 
				w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.UP)));
		assertEquals( new BouncyWorld(a2right, a3, b1, false), 
				w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.RIGHT)));

		assertEquals( new BouncyWorld(a3down, a2, b1, false), 
				w3.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.DOWN)));
		assertEquals( new BouncyWorld(a3left, a2, b1, false), 
				w3.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.LEFT)));
		
		// swap active anchors
		assertEquals( w3, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.TAB)) );
		assertEquals( w1, w3.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.TAB)) );

		// pause/unpause
		assertEquals( w0, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, ' ', ' ')));
		assertEquals( w1, w0.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, ' ', ' ')));

		// ignore other keys
		assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'c', 'c')));
		assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '$', '$')));

	}



}
