package supersnek;

/**
 * Things that can be destroyed - recommended for removeFromCanvas, etc Formerly
 * called Destroyable, but conflicted with an existing interface
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public interface Removable {
    /**
     * Remove this object
     */
    public void remove();

    /**
     * @return if object is removed
     */
    public boolean isRemoved();
}
