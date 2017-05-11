import objectdraw.Resizable2DInterface;

public abstract class Moveable extends ContainsObject {
	Direction direction = Direction.NONE;

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public abstract void move();
}