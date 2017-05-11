/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

class ControllerContext
implements AppletContext {
    private Applet myController;
    private Vector appletList = new Vector();

    public ControllerContext(Applet controller) {
        this.myController = controller;
        this.appletList.add(controller);
    }

    public Applet getApplet(String name) {
        return null;
    }

    public Enumeration getApplets() {
        return this.appletList.elements();
    }

    public AudioClip getAudioClip(URL url) {
        return Applet.newAudioClip(url);
    }

    public Image getImage(URL url) {
        String imgName = url.getFile();
        return Toolkit.getDefaultToolkit().getImage(imgName);
    }

    public void setStream(String key, InputStream stream) {
    }

    public Iterator getStreamKeys() {
        return null;
    }

    public InputStream getStream(String key) {
        return null;
    }

    public void showDocument(URL url) {
        this.showStatus("Reqeusted to load page" + url);
    }

    public void showDocument(URL url, String target) {
        this.showDocument(url);
    }

    public void showStatus(String status) {
        System.out.println(status);
    }
}

