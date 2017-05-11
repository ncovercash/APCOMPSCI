/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Dimension;
import javax.swing.JPanel;

public class SizeablePanel
extends JPanel {
    private int width;
    private int height;

    public SizeablePanel(int width, int height) {
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

