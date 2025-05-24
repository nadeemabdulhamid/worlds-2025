/* 
 * Nadeem Abdul Hamid, 2025.
 */

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * Encapsulates a circle world and pauses its animation
 * until the user presses the enter key.
 */
public class PauseWorld implements IWorld {

    CircleWorld paused;  // the circle world that is suspended

    public PauseWorld(CircleWorld paused) {
        this.paused = paused;
    }

    public PApplet draw(PApplet w) {
        paused.draw(w);
        w.fill(0, 0, 255, 100);  // semi-transparent blue
        w.rect(0, 0, w.width, w.height);
        w.fill(255);
        w.textSize(32);
        w.textAlign(PApplet.CENTER, PApplet.CENTER);
        w.text("Paused", w.width / 2, w.height / 2 - 20);
        w.textSize(16);
        w.text("Press Enter to continue or 'x' to exit.", w.width / 2, w.height / 2 + 20);
        return w;
    }

    public IWorld keyTyped(KeyEvent kev) { 
        if (kev.getKey() == '\n') {
            return paused;  // resumes the circle world by returning it as the active world
        } else if (kev.getKey() == 'x') {
            return Main.instance.forceExit();       // shuts down the entire application
        } else {
            return keyDefault(kev);
        }
    }
}
