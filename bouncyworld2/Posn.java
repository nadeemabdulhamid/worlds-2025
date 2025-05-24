import java.util.Objects;
import processing.core.PApplet;

public class Posn {
    double x;
    double y;

    public Posn(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }
    
    /** draws a line from this point to that */
    public PApplet drawLineTo(PApplet c, Posn that) {
        c.line((int)this.x, (int)this.y, (int)that.x, (int)that.y);

        return c;
    }
    
    /** produces the distance between this and that */
    public double distanceTo(Posn that) {
        return (double) Math.sqrt( Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2) );
    }
    
    /** produces this posn's coordinates multiplied by d */
    public Posn scale(double d) {
        return new Posn(this.x * d, this.y * d);
    }
    
    /** moves this posn by the given offsets */
    public Posn translate(Posn offset) {
        return new Posn( this.x + offset.x, this.y + offset.y );
    }

    /** produce the difference between that and this posn */
    public Posn diff(Posn that) {
        return new Posn( that.x - this.x,  that.y - this.y );
    }
    
    @Override
    public String toString() {
        return "Posn [x=" + x + ", y=" + y + "]";
    }

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posn other = (Posn) obj;
		return this.distanceTo(other) < 0.001;
//				Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
//				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

}
