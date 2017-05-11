/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.Frame;
import java.io.File;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import objectdraw.ControllerContext;

class ControllerStub
implements AppletStub {
    private Applet myController;
    private Frame myFrame;
    private ControllerContext myContext;
    private boolean active = true;

    public ControllerStub(Applet controller, Frame window) {
        this.myFrame = window;
        this.myController = controller;
        this.myContext = new ControllerContext(controller);
        this.myController.setStub(this);
    }

    public void activate() {
        this.active = true;
        this.myController.init();
        this.myController.start();
    }

    public AppletContext getAppletContext() {
        return this.myContext;
    }

    public URL getDocumentBase() {
        URL url = null;
        try {
            File file = new File(System.getProperty("user.dir"));
            url = file.toURL();
        }
        catch (MalformedURLException e) {
            System.out.println("Unable to resolve request for document base");
            url = null;
        }
        return url;
    }

    public URL getCodeBase() {
        URL url = null;
        try {
            File file = new File(System.getProperty("user.dir"));
            url = file.toURL();
        }
        catch (MalformedURLException e) {
            System.out.println("Unable to resolve request for code base");
            url = null;
        }
        return url;
    }

    public boolean isActive() {
        return this.active;
    }

    public void deactivate() {
        this.active = false;
    }

    public String getParameter(String name) {
        return "";
    }

    public void appletResize(int width, int height) {
        this.myFrame.setSize(width, height);
    }
}

