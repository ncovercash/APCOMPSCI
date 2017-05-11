import java.awt.Color;

public class Helpers {
	public static int inSortedArray(int key, int[] array) {
		int low=0, high=array.length-1;
		while (low <= high) {
			int middle = (low+high)/2;
			if (key < array[middle]) {
				high = middle-1;
			} else if (key > array[middle]) {
				low = middle+1;
			} else {
				return middle;
			}
		}
		return -1;
	}

	public static Direction flipDirection(Direction in) {
		switch (in) {
			case RIGHT: return Direction.LEFT;
			case LEFT: return Direction.RIGHT;
			case UP: return Direction.DOWN;
			case DOWN: return Direction.UP;
			default: return Direction.NONE;
		}
	}

	public static Direction rotateDirection(Direction in) {
		switch (in) {
			case RIGHT: return Direction.DOWN;
			case DOWN: return Direction.LEFT;
			case LEFT: return Direction.UP;
			case UP: return Direction.RIGHT;
			default: return Direction.NONE;
		}
	}

	public static Color[] getRandomColorScheme() {
		return World.ALL_SCHEMES[(int)(Math.random()*World.ALL_SCHEMES.length)];
	}
}