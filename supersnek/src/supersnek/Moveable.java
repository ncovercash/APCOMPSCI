package supersnek;

/**
 * abstract class - contains direction specific methods from an extension of
 * ContainsObject
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public abstract class Moveable extends ContainsObject {
    /**
     * Direction the object is traveling
     */
    protected Direction direction = Direction.NONE;

    /**
     * Changes the direction to the provided one
     *
     * @param direction
     *            direction to set to
     * @see supersnek.Moveable#direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return direction of object
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * abstract - move object in current direction
     */
    public abstract void move();

    /**
     * move object in specified direction
     *
     * @param direction
     *            direction to move object in
     */
    public void move(Direction direction) {
        this.direction = direction;
        this.move();
    }
}
