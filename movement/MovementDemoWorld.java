import java.util.Objects;

import processing.core.*;
import processing.event.KeyEvent;


/**
 * Demonstration of a world with two things that can be moved around
 * using the keyboard.  The first thing is moved with the WASD keys
 * and the second thing is moved with the arrow keys.
 * 
 * Movement is smooth even when two people are pressing keys on the 
 * same keyboard at the "same time". This is by using keyPressed and
 * keyReleased events to add and remove movement vectors to the
 * things.  The movement vectors are added to the things' positions
 * when the update method is called. So the things are not actually
 * *moved* when the key is pressed, but rather later on what the update
 * happens. This is a common pattern in games and other interactive
 * applications.
 */
public class MovementDemoWorld implements IWorld {
    
	Thing t1;
	Thing t2;
	
	public MovementDemoWorld(Thing t1, Thing t2) {
		this.t1 = t1;
		this.t2 = t2;
	}
	
	/* updates the things in this world */
	public MovementDemoWorld update() {
		return new MovementDemoWorld(t1.update(), t2.update());
	}
	
    public PApplet draw(PApplet c) {
        c.background(135, 206, 250);  // clear the screen each time (color sky blue)
        this.t1.draw(c);
        this.t2.draw(c);
        return c;
    }
    
    public MovementDemoWorld keyPressed(KeyEvent kev) {
    	if (Character.toLowerCase(kev.getKey()) == 'w') {  // t1 up
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(0, -5)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 's') { // t1 down
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(0, 5)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 'a') { // t1 left
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(-5, 0)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 'd') { // t1 right
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(5, 0)), this.t2);    		
    	} else if (kev.getKeyCode() == PApplet.UP) {	// t2 ...
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(0, -5)));
    	} else if (kev.getKeyCode() == PApplet.DOWN) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(0, 5)));    		
    	} else if (kev.getKeyCode() == PApplet.LEFT) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(-5, 0)));    		
    	} else if (kev.getKeyCode() == PApplet.RIGHT) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(5, 0)));    		
    	} else {
    		return this;
    	}
    }
    
    /** "undo"s the effect of keypressed */ 
    public MovementDemoWorld keyReleased(KeyEvent kev) {
    	if (Character.toLowerCase(kev.getKey()) == 'w') {  // t1 up
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(0, 5)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 's') { // t1 down
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(0, -5)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 'a') { // t1 left
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(5, 0)), this.t2);
    	} else if (Character.toLowerCase(kev.getKey()) == 'd') { // t1 right
    		return new MovementDemoWorld(this.t1.addMovement(new Posn(-5, 0)), this.t2);    		
    	} else if (kev.getKeyCode() == PApplet.UP) {	// t2 ...
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(0, 5)));
    	} else if (kev.getKeyCode() == PApplet.DOWN) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(0, -5)));    		
    	} else if (kev.getKeyCode() == PApplet.LEFT) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(5, 0)));    		
    	} else if (kev.getKeyCode() == PApplet.RIGHT) {
    		return new MovementDemoWorld(this.t1, this.t2.addMovement(new Posn(-5, 0)));    		
    	} else {
    		return this;
    	}
    }

    

	
	@Override
	public int hashCode() {
		return Objects.hash(t1, t2);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovementDemoWorld other = (MovementDemoWorld) obj;
		return Objects.equals(t1, other.t1) && Objects.equals(t2, other.t2);
	}
	
	@Override
	public String toString() {
		return "MovementDemoWorld [t1=" + t1 + ", t2=" + t2 + "]";
	}
		    
}
