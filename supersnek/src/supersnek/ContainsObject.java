package supersnek;

import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Resizable2DInterface;

/**
 * Things that contain objects on a canvas there is an optional inner object for
 * things with 2 items
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public abstract class ContainsObject implements ConstrainedToArea, Removable {
    /**
     * main object which is typically the full grid cell size
     */
    protected Resizable2DInterface object;
    /**
     * optional secondary object
     */
    protected Resizable2DInterface innerObject;
    /**
     * World which this object is contained in
     */
    protected World world;
    /**
     * Canvas these objects are in
     */
    protected DrawingCanvas canvas;

    /**
     * Contains whether item is removed
     */
    protected boolean removed = false;

    /**
     * @return the object
     */
    public Resizable2DInterface getObject() {
        return this.object;
    }

    /**
     * @return the innerObject
     */
    public Resizable2DInterface getInnerObject() {
        return this.innerObject;
    }

    /**
     * Check if this object overlaps the other one
     *
     * @param obj
     *            object to check if it overlaps
     * @return whether the 2 objects overlap base on x/y
     */
    public boolean overlaps(Resizable2DInterface obj) {
        return this.object.getX() == obj.getX() && this.object.getY() == obj.getY();
    }

    /**
     * Check if this object overlaps the other one
     *
     * @param obj
     *            object to check if it overlaps
     * @return whether the 2 objects overlap base on x/y
     */
    public boolean overlaps(ContainsObject obj) {
        return this.object.getX() == obj.getX() && this.object.getY() == obj.getY();
    }

    /**
     * ConstrainedToArea implementation Compares x and y to canvas edges
     *
     * @see supersnek.ConstrainedToArea#isInArea()
     */
    public boolean isInArea() {
        return this.object.getX() >= 0 && this.object.getY() >= 0 && this.object.getX() < this.canvas.getWidth()
                && this.object.getY() < this.canvas.getHeight();
    }

    /**
     * implements Removable removes both object and innerObject from canvas
     *
     * @see supersnek.Removable#remove()
     */
    public void remove() {
        this.object.removeFromCanvas();
        if (this.innerObject != null) {
            this.innerObject.removeFromCanvas();
        }
        this.removed = true;
    }

    /**
     * implements Removable
     *
     * @return whether or not the instance is removed from canvas
     * @see supersnek.Removable#remove()
     */
    public boolean isRemoved() {
        return this.removed;
    }
    
    /**
     * Hides objects
     */
    public void hide() {
        this.object.hide();
        if (this.innerObject != null) {
            this.innerObject.hide();
        }
    }
    
    /**
     * Shows objects
     */
    public void show() {
        this.object.show();
        if (this.innerObject != null) {
            this.innerObject.show();
        }
    }

    /**
     * @return x of main object
     * @see objectdraw.Drawable2DInterface#getX()
     */
    public double getX() {
        return this.object.getX();
    }

    /**
     * @return y of main object
     * @see objectdraw.Drawable2DInterface#getY()
     */
    public double getY() {
        return this.object.getY();
    }

    /**
     * @return height of main object
     * @see objectdraw.Drawable2DInterface#getHeight()
     */
    public double getHeight() {
        return this.object.getHeight();
    }

    /**
     * @return width of main object
     * @see objectdraw.Drawable2DInterface#getWidth()
     */
    public double getWidth() {
        return this.object.getWidth();
    }

    /**
     * @return location of main object
     * @see objectdraw.Drawable2DInterface#getLocation()
     */
    public Location getLocation() {
        return this.object.getLocation();
    }

    /**
     * @return String representation of the bullet: Bullet at ADDRESS, outside:
     *         (x,y,w,h), inside: (x,y,w,h)
     */
    public String toString() {
        String str = this.getClass() + " at " + this.hashCode() + ", outside: (" + this.object.getX() + ","
                + this.object.getY() + "," + this.object.getWidth() + "," + this.object.getHeight() + ")";
        if (this.innerObject != null) {
            str += " , inside: (" + this.innerObject.getX() + "," + this.innerObject.getY() + ","
                    + this.innerObject.getWidth() + "," + this.innerObject.getHeight() + ")";
        }
        return str;
    }
}
