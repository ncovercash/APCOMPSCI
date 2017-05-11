/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Dimension;
import java.awt.Panel;

public class AWTSizeablePanel
extends Panel {
    private int width;
    private int height;

    public AWTSizeablePanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }

    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}

