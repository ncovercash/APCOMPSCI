/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import objectdraw.CanvasManager;
import objectdraw.DrawableIterator;

public interface DrawingCanvas
extends ImageObserver,
ComponentListener {
    public CanvasManager getCanvasContent();

    public void clear();

    public void repaint();

    public void enableAutoRepaint();

    public void disableAutoRepaint();

    public Graphics getGraphics();

    public boolean prepareImage(Image var1, ImageObserver var2);

    public Dimension getSize();

    public int getWidth();

    public int getHeight();

    public Image createImage(int var1, int var2);

    public void addMouseListener(MouseListener var1);

    public void addMouseMotionListener(MouseMotionListener var1);

    public void addComponentListener(ComponentListener var1);

    public void addKeyListener(KeyListener var1);

    public boolean requestFocusInWindow();

    public void requestFocus();

    public DrawableIterator getDrawableIterator();
}

