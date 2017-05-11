package supersnek;

/**
 * Enum of directions (including shoot for Snake's keybinding mechanism)
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public enum Direction {
    /**
     * The direction UP
     */
    UP,
    /**
     * The direction DOWN
     */
    DOWN,
    /**
     * The direction RIGHT
     */
    RIGHT,
    /**
     * The direction LEFT
     */
    LEFT,
    /**
     * Command to shoot
     */
    SHOOT,
    /**
     * Unused, no direction (don't move)
     */
    NONE
}
