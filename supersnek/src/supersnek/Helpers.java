package supersnek;

import java.awt.Color;

/**
 * A few helpful methods with no place
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Helpers {
    /**
     * Binary search through an array Used for determining if a key is in a
     * valid list
     *
     * @param key
     *            search parameter
     * @param array
     *            array to search in
     * @return index or -1 if not found
     */
    public static int inSortedArray(int key, int[] array) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (key < array[middle]) {
                high = middle - 1;
            } else if (key > array[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * Flips the direction (up to down, vice versa, etc) If not a direction,
     * returns Direction.NONE
     *
     * @param in
     *            direction to flip
     * @return flipped direction
     */
    public static Direction flipDirection(Direction in) {
        switch (in) {
            case RIGHT:
                return Direction.LEFT;
            case LEFT:
                return Direction.RIGHT;
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            default:
                return Direction.NONE;
        }
    }

    /**
     * Rotates the direction clockwise (up to right, right to down, etc) If not
     * a direction, returns Direction.NONE
     *
     * @param in
     *            direction to rotate
     * @return rotate direction
     */
    public static Direction rotateDirection(Direction in) {
        switch (in) {
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            case UP:
                return Direction.RIGHT;
            default:
                return Direction.NONE;
        }
    }

    /**
     * Gets a random color scheme
     *
     * @return 1D array of Color objects
     */
    public static Color[] getRandomColorScheme() {
        return ColorSchemes.ALL_SCHEMES[(int) (Math.random() * ColorSchemes.ALL_SCHEMES.length)];
    }
}
