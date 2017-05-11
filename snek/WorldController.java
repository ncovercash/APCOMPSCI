public class WorldController implements KeyHandler {
	World world;

	public WorldController(World world) {
		this.world = world;
	}

	public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
		switch (keyCode) {
			case 81:
				world.running = false;
				break;
			case 45:
				world.pauseTime = Math.min(world.MAX_PAUSE_TIME, world.pauseTime+10);
				break;
			case 61:
				if (shiftState) {
					world.pauseTime = Math.max(world.MIN_PAUSE_TIME, world.pauseTime-10);
				}
				break;
		}
	}
}
