/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.util.Enumeration;
import java.util.Vector;
import objectdraw.Drawable;

public class DrawableIterator {
    private Enumeration elements;

    DrawableIterator(Vector v) {
        this.elements = v.elements();
    }

    public boolean hasNext() {
        return this.elements.hasMoreElements();
    }

    public Drawable next() {
        return (Drawable)this.elements.nextElement();
    }
}

