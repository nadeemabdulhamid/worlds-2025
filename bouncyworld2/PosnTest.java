import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PosnTest {

	Posn p1 = new Posn(100, 10);
	Posn p2 = new Posn(20, 50);
	Posn p3 = new Posn(200, 10);
	Posn p4 = new Posn(20, 0);
	Posn p5 = new Posn(-20, -50);
		

	@Test
	void testDistanceTo() {
		assertEquals(100, p1.distanceTo(p3));
		assertEquals(50,  p2.distanceTo(p4));
		assertEquals(50,  p4.distanceTo(p2));
		assertEquals(80.6, p1.distanceTo(p4), 0.1);
		assertEquals(80.6, p4.distanceTo(p1), 0.1);
		assertEquals(0,   p3.distanceTo(p3));
	}
	
	@Test
	void testScale() {
		assertEquals(new Posn(10, 1), p1.scale(.1));
		assertEquals(new Posn(400, 20), p3.scale(2));
		assertEquals(new Posn(6, 15), p2.scale(.3));
		assertEquals(p5, p2.scale(-1));
	}
	
	@Test
	void testTranslate() {
		assertEquals(new Posn(300, 20), p1.translate(p3));
		assertEquals(new Posn(80, -40), p1.translate(p5));
		assertEquals(new Posn(0, 0), p5.translate(p2));
	}

	@Test
	void testDiff() {
		assertEquals(new Posn(40, 100), p5.diff(p2));
		// order matters...
		assertEquals(new Posn(-40, -100), p2.diff(p5));
		
		assertEquals(new Posn(0, 0), p1.diff(p1));
		assertEquals(new Posn(-180, 40), p3.diff(p2));
	}

}
