package supersnek;

/**
 * Controls the world
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class WorldController implements KeyHandler {
    /**
     * Minimum pause time for World
     */
    public static final int MIN_PAUSE_TIME = 20;
    /**
     * Maximum pause time for World
     */
    public static final int MAX_PAUSE_TIME = 300;
    /**
     * World to control
     */
    World world;

    /**
     * @param world
     *            to control
     */
    public WorldController(World world) {
        this.world = world;
    }

    /**
     * Handle codes for quitting and other events
     *
     * @param keyCode
     *            code of key
     * @param shiftState
     *            boolean of shift key
     * @param controlState
     *            boolean of ctrl key
     * @param altState
     *            boolean of alt/option key
     */
    public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
        switch (keyCode) {
            case 10:
                if (!World.snakesPresent()) {
                    this.world.startGame();
                }
                break;
            case 27:
                this.world.running = false;
                break;
            case 45:
                this.world.pauseTime = Math.min(WorldController.MAX_PAUSE_TIME, this.world.pauseTime + 10);
                break;
            case 61:
                if (shiftState) {
                    this.world.pauseTime = Math.max(WorldController.MIN_PAUSE_TIME, this.world.pauseTime - 10);
                }
                break;
            default:
                break;
        }
    }
}
