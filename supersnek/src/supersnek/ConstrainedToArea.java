package supersnek;

/**
 * Interface for things within areas such as canvas bounds, etc.
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public interface ConstrainedToArea {
    /**
     * @return whether or not the object is within its constrained area
     */
    public boolean isInArea();
}
