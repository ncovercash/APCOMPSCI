/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.image.ImageObserver;
import objectdraw.Bounds;
import objectdraw.CanvasManager;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Resizable2D;
import objectdraw.Resizable2DInterface;

public class VisibleImage
extends Resizable2D
implements Resizable2DInterface {
    protected Image image;
    private Image scaledImage;
    private CanvasManager canvasContent;
    private Location origin;
    private int scaledWidth = -1;
    private int scaledHeight = -1;
    private int actualWidth = -1;
    private int actualHeight = -1;
    protected DrawingCanvas canvas;
    private int drawTries = 0;
    private static final int INITTRIES = 10;
    private int nextTries = 10;

    public VisibleImage(Image image, Location origin, DrawingCanvas canvas) {
        if (image == null) {
            throw new NullPointerException("Image parameter to VisibleImage constructor is undefined");
        }
        if (canvas == null) {
            throw new NullPointerException("DrawingCanvas parameter to VisibleImage constructor is undefined");
        }
        this.origin = new Location(origin);
        this.image = image;
        canvas.prepareImage(this.image, canvas);
        this.actualHeight = image.getHeight(canvas);
        this.actualWidth = image.getWidth(canvas);
        this.addToCanvas(canvas);
    }

    public VisibleImage(Image image, double x, double y, DrawingCanvas canvas) {
        this(image, new Location(x, y), canvas);
    }

    public VisibleImage(Image image, Location origin, double width, double height, DrawingCanvas canvas) {
        this(image, origin, canvas);
        this.setBounds(new Bounds(origin, width, height));
    }

    public VisibleImage(Image image, double x, double y, double width, double height, DrawingCanvas canvas) {
        this(image, new Location(x, y), width, height, canvas);
    }

    public synchronized void addToCanvas(DrawingCanvas canvas) {
        this.canvas = canvas;
        super.addToCanvas(canvas);
    }

    synchronized void draw(Graphics g) {
        if (this.origin != null) {
            super.draw(g);
            Point loc = this.origin.toPoint();
            if (this.scaledWidth != -1) {
                if (this.scaledImage.getWidth(this.canvas) == -1) {
                    if (this.drawTries <= 0) {
                        this.scaledImage = this.image.getScaledInstance(this.scaledWidth, this.scaledHeight, 1);
                        this.canvas.prepareImage(this.scaledImage, this.canvas);
                        this.drawTries = this.nextTries;
                        this.nextTries += this.nextTries;
                    } else {
                        this.nextTries = 10;
                    }
                }
                --this.drawTries;
                g.drawImage(this.scaledImage, loc.x, loc.y, this.canvas);
            } else {
                g.drawImage(this.image, loc.x, loc.y, this.canvas);
            }
        }
    }

    public synchronized void moveTo(Location location) {
        this.origin = new Location(location);
        this.setStateChanged();
    }

    public synchronized void move(double dx, double dy) {
        this.origin.translate(dx, dy);
        this.setStateChanged();
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image newImage) {
        if (newImage == null) {
            throw new NullPointerException("Parameter to setImage is undefined");
        }
        this.image = newImage;
        this.canvas.prepareImage(this.image, this.canvas);
        this.actualHeight = this.image.getHeight(this.canvas);
        this.actualWidth = this.image.getWidth(this.canvas);
        if (this.scaledWidth != -1 && this.scaledHeight != -1) {
            this.setSize(this.scaledWidth, this.scaledHeight);
        }
        this.setStateChanged();
    }

    void setBounds(Bounds b) {
        this.origin = b.getLocation();
        this.setSize(b.getWidth(), b.getHeight());
    }

    public void setSize(double width, double height) {
        this.scaledWidth = (int)Math.rint(width);
        this.scaledHeight = (int)Math.rint(height);
        this.scaledImage = this.image.getScaledInstance(this.scaledWidth, this.scaledHeight, 1);
        this.canvas.prepareImage(this.scaledImage, this.canvas);
        this.nextTries = 10;
        this.drawTries = 10;
        this.nextTries += this.nextTries;
        this.setStateChanged();
    }

    public synchronized void setWidth(double width) {
        this.loadImage();
        if (this.scaledHeight == -1) {
            this.setSize(width, this.actualHeight);
        } else {
            this.setSize(width, this.scaledHeight);
        }
    }

    public synchronized void setHeight(double height) {
        this.loadImage();
        if (this.scaledWidth == -1) {
            this.setSize(this.actualWidth, height);
        } else {
            this.setSize(this.scaledWidth, height);
        }
    }

    synchronized Bounds getBounds() {
        if (this.scaledWidth != -1 && this.scaledHeight != -1) {
            return new Bounds(this.origin, this.scaledWidth, this.scaledHeight);
        }
        this.loadImage();
        if (this.scaledWidth != -1) {
            this.scaledHeight = this.actualHeight;
        } else if (this.scaledHeight != -1) {
            this.scaledWidth = this.actualWidth;
        } else {
            return new Bounds(this.origin, this.actualWidth, this.actualHeight);
        }
        return new Bounds(this.origin, this.scaledWidth, this.scaledHeight);
    }

    public double getWidth() {
        return this.getBounds().getWidth();
    }

    public double getHeight() {
        return this.getBounds().getHeight();
    }

    private void loadImage() {
        MediaTracker mt = new MediaTracker((Component)((Object)this.canvas));
        mt.addImage(this.image, 0);
        while (this.image.getWidth(this.canvas) == -1) {
            try {
                mt.waitForAll();
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
        this.actualHeight = this.image.getHeight(this.canvas);
        this.actualWidth = this.image.getWidth(this.canvas);
    }
}

