package supersnek;

/**
 * interface for objects that should recieve keypresses
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public interface KeyHandler {
    /**
     * Method to handle keys
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
    public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState);
}
