import objectdraw.Resizable2DInterface;

public interface Obstacle {
	public boolean overlaps(Obstacle a);
	public boolean overlaps(Resizable2DInterface a);
	public Resizable2DInterface getObject();
}
