package supersnek;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

/**
 * Handles keys for the game
 *
 * @author Noah Overcash smileytechguy@smileytechguy.com
 */
public class KeyAction implements Action {
    /**
     * World which the KeyAction performs actions on
     */
    protected World world;
    /**
     * code of key this action responds to
     */
    protected int code;
    /**
     * If shift is pressed
     */
    protected boolean shiftState;
    /**
     * If control is pressed
     */
    protected boolean controlState;
    /**
     * If alt is pressed
     */
    protected boolean altState;
    /**
     * If the action is enabled
     */
    protected boolean enabled = true;

    /**
     * Constructor
     *
     * @param code
     *            code of key
     * @param shiftState
     *            boolean of shift key
     * @param controlState
     *            boolean of ctrl key
     * @param altState
     *            boolean of alt/option key
     * @param world
     *            world the action lies on
     */
    public KeyAction(int code, boolean shiftState, boolean controlState, boolean altState, World world) {
        this.code = code;
        this.world = world;
        this.shiftState = shiftState;
        this.controlState = controlState;
        this.altState = altState;
    }

    /**
     * What is fired by the action binding
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.world.getKeyHandlers().size() && this.enabled; i++) {
            this.world.getKeyHandlers().get(i).handleKey(this.code, this.shiftState, this.controlState, this.altState);
        }
    }
    
    /**
     * Action implementation
     *
     * @see javax.swing.Action#getValue(java.lang.String)
     */
    public Object getValue(String key) {
        switch (key) {
            case "code":
                return new Integer(this.code);
            case "shiftState":
                return new Boolean(this.shiftState);
            case "controlState":
                return new Boolean(this.controlState);
            case "altState":
                return new Boolean(this.altState);
            default:
                return null;
        }
    }
    
    /**
     * Action implementation
     *
     * @see javax.swing.Action#putValue(java.lang.String, java.lang.Object)
     */
    public void putValue(String key, Object value) {
        switch (key) {
            case "code":
                this.code = ((Integer) value).intValue();
                break;
            case "shiftState":
                this.shiftState = ((Boolean) value).booleanValue();
                break;
            case "controlState":
                this.controlState = ((Boolean) value).booleanValue();
                break;
            case "altState":
                this.altState = ((Boolean) value).booleanValue();
                break;
            default:
        }

    }
    
    /**
     * Action implementation
     *
     * @see javax.swing.Action#setEnabled(boolean)
     */
    public void setEnabled(boolean b) {
        this.enabled = b;

    }
    
    /**
     * Action implementation
     *
     * @see javax.swing.Action#isEnabled()
     */
    public boolean isEnabled() {
        return this.enabled;
    }
    
    /**
     * Action implementation not implemented
     *
     * @see javax.swing.Action#addPropertyChangeListener(PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // not implemented
    }
    
    /**
     * Action implementation not implemented
     *
     * @see javax.swing.Action#removePropertyChangeListener(PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // not implemented
    }
}
