/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import objectdraw.AWTControllerFrame;
import objectdraw.ActiveObject;
import objectdraw.ControllerStub;
import objectdraw.TerminateEvent;
import objectdraw.UnintSuspendEvent;

public class AWTController
extends Applet {
    static final int DEFAULT_WINDOW_WIDTH = 400;
    static final int DEFAULT_WINDOW_HEIGHT = 400;
    private UnintSuspendEvent stopper;
    private boolean beginCalled = false;

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

    protected synchronized AudioClip getAudio(String path) {
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

    protected void setup() {
        this.setLayout(new BorderLayout());
    }

    public void init() {
        this.setup();
        this.validate();
        ActiveObject.initializeEventList();
        this.begin();
        this.validate();
    }

    synchronized boolean callBegin() {
        this.begin();
        this.beginCalled = true;
        return this.beginCalled;
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
        AWTControllerFrame myFrame = new AWTControllerFrame(this.getName(), this, width, height);
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

