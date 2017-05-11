/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Graphics;
import objectdraw.WindowController;

public class FrameWindowController
extends WindowController {
    private boolean inited = false;

    public FrameWindowController() {
        this.placeInFrame(400, 400);
    }

    public void init() {
        this.inited = true;
        super.init();
    }

    public void paint(Graphics g) {
        if (!this.inited) {
            this.init();
            this.start();
        }
        super.paint(g);
    }
}

