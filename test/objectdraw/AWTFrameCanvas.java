/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import objectdraw.AWTDrawingCanvas;
import objectdraw.ActiveObject;
import objectdraw.CanvasManager;
import objectdraw.DrawingCanvas;
import objectdraw.TerminateEvent;

public class AWTFrameCanvas
extends AWTDrawingCanvas
implements DrawingCanvas,
ComponentListener,
WindowListener {
    private Dimension preferred_dim;
    private CanvasManager canvasContent;
    private Frame frame;
    public static final Dimension DEFAULT_PREFERRED_SIZE = new Dimension(400, 400);

    public AWTFrameCanvas() {
        this(DEFAULT_PREFERRED_SIZE);
    }

    public AWTFrameCanvas(int width, int height) {
        this(new Dimension(width, height));
    }

    public AWTFrameCanvas(Dimension size) {
        this.canvasContent = new CanvasManager(size, this);
        this.preferred_dim = size;
        ActiveObject.initializeEventList();
        this.frame = new Frame();
        this.frame.add("Center", this);
        this.frame.setVisible(true);
        this.frame.pack();
    }

    public void setSize(Dimension d) {
        this.preferred_dim = d;
        this.frame.setSize(this.preferred_dim);
        this.frame.pack();
    }

    public void setSize(int width, int height) {
        this.setSize(new Dimension(width, height));
    }

    public Dimension getPreferredSize() {
        return this.preferred_dim;
    }

    public void windowClosing(WindowEvent e) {
        TerminateEvent killer = new TerminateEvent();
        ActiveObject.scheduleClassEvent(killer);
        this.frame.dispose();
        System.exit(0);
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
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

