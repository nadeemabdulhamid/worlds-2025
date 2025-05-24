import java.util.Objects;

import processing.core.*;

public class Blob {
    Posn loc;
    int SNAP_FACTOR = 40;
    
    public Blob(Posn loc) {
        this.loc = loc;
    }
    
    public PApplet draw(PApplet c, Anchor a) {
        c.stroke(0);   // color black
        this.loc.drawLineTo(c, a.loc);
        c.imageMode(PApplet.CENTER);
        c.image(c.loadImage("green_blob.gif"), (int)this.loc.getX(), (int)this.loc.getY());
        return c;
    }
    
    /** moves this blob closer to the given anchor */
    public Blob moveTowards(Anchor a) {
        if (a.distanceTo(this.loc) < (2.0f / this.SNAP_FACTOR) ) {
            return new Blob(a.loc);  // snap to anchor
        } else {
            return new Blob(this.loc.translate( this.loc.diff(a.loc).scale( 1.0f / this.SNAP_FACTOR )));
        }
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(SNAP_FACTOR, loc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Blob other = (Blob) obj;
        return SNAP_FACTOR == other.SNAP_FACTOR && Objects.equals(loc, other.loc);
    }

    @Override
    public String toString() {
        return "Blob [loc=" + loc + ", BOUNCE_FACTOR=" + SNAP_FACTOR + "]";
    }
}
