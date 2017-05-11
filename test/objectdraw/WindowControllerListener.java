/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.WindowController;

class WindowControllerListener
implements MouseListener,
MouseMotionListener {
    private WindowController controller;
    private DrawingCanvas canvas;

    public WindowControllerListener(WindowController controller, DrawingCanvas canvas) {
        this.controller = controller;
        this.canvas = canvas;
    }

    public void mouseClicked(MouseEvent e) {
        try {
            this.canvas.requestFocusInWindow();
        }
        catch (AbstractMethodError err) {
            this.canvas.requestFocus();
        }
        this.controller.onMouseClick(new Location(e.getPoint()));
    }

    public void mousePressed(MouseEvent e) {
        this.controller.onMousePress(new Location(e.getPoint()));
    }

    public void mouseReleased(MouseEvent e) {
        this.controller.onMouseRelease(new Location(e.getPoint()));
    }

    public void mouseEntered(MouseEvent e) {
        this.controller.onMouseEnter(new Location(e.getPoint()));
    }

    public void mouseExited(MouseEvent e) {
        this.controller.onMouseExit(new Location(e.getPoint()));
    }

    public void mouseDragged(MouseEvent e) {
        this.controller.onMouseDrag(new Location(e.getPoint()));
    }

    public void mouseMoved(MouseEvent e) {
        this.controller.onMouseMove(new Location(e.getPoint()));
    }
}

