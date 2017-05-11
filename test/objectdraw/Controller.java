/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import objectdraw.ActiveObject;
import objectdraw.ControllerFrame;
import objectdraw.ControllerStub;
import objectdraw.TerminateEvent;
import objectdraw.UnintSuspendEvent;

public class Controller
extends JApplet {
    static final int DEFAULT_WINDOW_WIDTH = 400;
    static final int DEFAULT_WINDOW_HEIGHT = 400;
    private UnintSuspendEvent stopper;

    public static String getVersion() {
        return "Version 1.1.1, released February 2005";
    }

    protected void setup() {
        this.getContentPane().setLayout(new BorderLayout());
        ActiveObject.initializeEventList();
    }

    public void init() {
        this.setup();
        this.validate();
        this.begin();
        this.validate();
    }

    public void begin() {
    }

    public synchronized Image getImage(String path) {
        Image im;
        try {
            im = path.substring(0, 4).equals("http") ? this.getImage(new URL(path)) : this.getImage(this.getClass().getResource(path));
        }
        catch (MalformedURLException e) {
            System.out.println("Invalid Image path - " + path);
            return null;
        }
        return im;
    }

    public synchronized AudioClip getAudio(String path) {
        AudioClip sound;
        try {
            sound = path.substring(0, 4).equals("http") ? this.getAudioClip(new URL(path)) : this.getAudioClip(this.getClass().getResource(path));
        }
        catch (MalformedURLException e) {
            System.out.println("Invalid Sound path - " + path);
            return null;
        }
        return sound;
    }

    public static synchronized double getTime() {
        throw new UnsupportedOperationException("getTime() is no longer supported - use System.currentTimeMillis() instead");
    }

    public synchronized void start() {
        if (this.stopper != null) {
            this.stopper.resume();
            this.stopper = null;
        }
    }

    public synchronized void stop() {
        if (this.stopper == null) {
            this.stopper = new UnintSuspendEvent();
            ActiveObject.scheduleClassEvent(this.stopper);
        }
    }

    public synchronized void destroy() {
        TerminateEvent killer = new TerminateEvent();
        ActiveObject.scheduleClassEvent(killer);
    }

    public void placeInFrame(int width, int height) {
        ControllerFrame myFrame = new ControllerFrame(this.getName(), this, width, height);
        new objectdraw.ControllerStub(this, myFrame);
    }

    public void startController(int width, int height) {
        this.placeInFrame(width, height);
        this.init();
        this.start();
    }

    public void startController() {
        this.startController(400, 400);
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        this.validate();
    }
}

