package supersnek;

import java.util.ArrayList;

import objectdraw.DrawingCanvas;

/**
 * Abstract superclass for all controllers of objects (obstacles)
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 * @param <T>
 *            Type of objects this object controls
 * @see supersnek.Moveable
 */
public abstract class ObjectController<T extends ContainsObject & Removable> implements Tickable {
    /**
     * World which this object is contained in
     */
    protected World world;
    /**
     * Canvas these objects are in
     */
    protected DrawingCanvas canvas;

    /**
     * Probability that, on a given tick, a new object will be added
     */
    private static double probabilityToAddOnTick;

    /**
     * Returns ArrayList of objects conrolled by the controller
     */
    protected ArrayList<T> objects = new ArrayList<T>();

    /**
     * Constructor
     *
     * @param canvas
     *            canvas these objects are on
     * @param world
     *            world which these objects reside
     */
    public ObjectController(DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
    }

    /**
     * Generates and adds an item of type T abstract for specific creation of
     * objects
     */
    public abstract void addItem();

    /**
     * Adds an item of type T
     *
     * @param item
     *            item to add
     */
    public void addItem(T item) {
        this.objects.add(item);
    }

    /**
     * Tickable implementation - adds an item based on probabilityToAddOnTick
     *
     * @see supersnek.Tickable#tick()
     * @see supersnek.ObjectController#probabilityToAddOnTick
     */
    public void tick() {
        if (Math.random() <= ObjectController.probabilityToAddOnTick) {
            this.addItem();
        }
    }

    /**
     * @return list of objects this object is controlling
     */
    public ArrayList<T> getObjects() {
        return this.objects;
    }

    /**
     * @param o
     *            object to remove from the list
     */
    public void remove(T o) {
        for (int i = 0; i < this.objects.size(); i++) {
            if (o == this.objects.get(i)) {
                ((Removable) o).remove();
                this.objects.remove(i);
                return;
            }
        }
    }

    /**
     * Removes all objects from the internal array
     */
    public void removeAll() {
        for (T obj : this.objects) {
            obj.remove();
        }
        this.objects = new ArrayList<T>();
    }

    /**
     * @return current probabilityToAddOnTick
     */
    public static double getProbabilityToAddOnTick() {
        return ObjectController.probabilityToAddOnTick;
    }

    /**
     * @param probabilityToAddOnTick
     *            the probabilityToAddOnTick to set
     */
    public static void setProbabilityToAddOnTick(double probabilityToAddOnTick) {
        ObjectController.probabilityToAddOnTick = probabilityToAddOnTick;
    }
}
