/* 
 * Nadeem Abdul Hamid, 2025.
 */

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * A welcome screen view for the application.
 */
public class WelcomeWorld implements IWorld {

    public PApplet draw(PApplet w) {
        w.background(255);
        w.fill(0);
        w.textSize(32);
        w.textAlign(PApplet.CENTER, PApplet.CENTER);
        w.text("Welcome to the Circle World!", w.width / 2, w.height / 2 - 20);
        w.textSize(16);
        w.text("Press Enter to start or 'x' to exit.", w.width / 2, w.height / 2 + 20);
        return w;
    }

    /**
     * Launches the CircleWorld when the enter key is pressed.
     * Exits the application completely if 'x' is pressed.
     * Otherwise, it returns the default behavior of the keyTyped method.
     */
    public IWorld keyTyped(KeyEvent kev) { 
        if (kev.getKey() == '\n') {
            return new CircleWorld(200, 0);
        } else if (kev.getKey() == 'x') {
            return Main.instance.forceExit();       // shuts down the entire application
        } else {
            return keyDefault(kev);
        }
    }
}
