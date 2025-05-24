import java.awt.Color;
import java.util.Objects;

import processing.core.PApplet;

/**
 * represents a sprite that moves around the world 
 */
public class Thing {
	
	Posn loc;   // current location 
	Posn vel;   // velocity = direction/speed of movement
	Color col;  // color
	double maxSpeed = 5.0;

	// the largest x/y values of the world boundaries that this thing is allowed to go
	Posn minBounds = new Posn(20, 20);
	Posn maxBounds = new Posn(380, 380); 
	
	
	public Thing(Posn loc, Color col) {	// overloaded constructor
		this.loc = loc;
		this.vel = new Posn(0, 0);
		this.col = col;
	}
	
	public Thing(Posn loc, Posn vel, Color col) {
		this.loc = loc;
		this.vel = vel;
		this.col = col;
	}
	
	/** update this thing's location */
	public Thing update() {
		return new Thing(this.loc.translate(this.vel).bound(minBounds, maxBounds), this.vel, this.col);
	}
	
    public PApplet draw(PApplet c) {
        c.fill(col.getRGB());
        c.circle((int)this.loc.getX(), (int)this.loc.getY(), 30);
        return c;
    }
    
    /** adds vp to the vel of this thing */
    public Thing addMovement(Posn vp) {
    	return new Thing(this.loc, this.vel.translate(vp).bound(this.maxSpeed), this.col);
    }

    @Override
	public String toString() {
		return "Thing [loc=" + loc + ", vel=" + vel + ", col=" + col + ", maxSpeed=" + maxSpeed + ", minBounds="
				+ minBounds + ", maxBounds=" + maxBounds + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, loc, maxBounds, maxSpeed, minBounds, vel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thing other = (Thing) obj;
		return Objects.equals(col, other.col) && Objects.equals(loc, other.loc)
				&& Objects.equals(maxBounds, other.maxBounds)
				&& Double.doubleToLongBits(maxSpeed) == Double.doubleToLongBits(other.maxSpeed)
				&& Objects.equals(minBounds, other.minBounds) && Objects.equals(vel, other.vel);
	}

    
}
