/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import objectdraw.AWTController;

class AWTControllerFrame
extends Frame
implements WindowListener {
    private AWTController controller;

    public AWTControllerFrame(String title, AWTController controller, int width, int height) {
        super(title);
        this.controller = controller;
        MenuBar menubar = new MenuBar();
        Menu file = new Menu("File", true);
        menubar.add(file);
        file.add("Quit");
        this.setMenuBar(menubar);
        this.addWindowListener(this);
        this.add((Component)controller, "Center");
        this.setSize(width, height);
        this.setVisible(true);
    }

    public boolean action(Event e, Object arg) {
        String label;
        if (e.target instanceof MenuItem && (label = (String)arg).equals("Quit")) {
            System.exit(0);
        }
        return false;
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        this.controller.stop();
        this.controller.destroy();
        this.remove(this.controller);
        this.dispose();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }
}

