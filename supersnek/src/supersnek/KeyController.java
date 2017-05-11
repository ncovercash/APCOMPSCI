package supersnek;

import java.awt.event.InputEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import objectdraw.JDrawingCanvas;

/**
 * Controls and forwards keystrokes
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class KeyController {
    /**
     * Arrow key codes (arrows and space)
     */
    public static final int[] ARROW_KEYS = { 32, 37, 38, 39, 40, 160 }; // arrows and
                                                                   // space
    /**
     * Arrow key direction map
     */
    public static final Direction[] ARROW_KEYS_MAP = { Direction.SHOOT, Direction.LEFT, Direction.UP, Direction.RIGHT,
            Direction.DOWN, Direction.SHOOT };
    /**
     * WASD, Q, Backspace, Tab key codes
     */
    public static final int[] WASD_KEYS = { 8, 9, 20, 65, 68, 81, 83, 87, 161, 192 }; // wasd,
    // q/backspace/backquote
    /**
     * WASDQ... key map
     */
    public static final Direction[] WASD_KEYS_MAP = { Direction.SHOOT, Direction.SHOOT, Direction.SHOOT, Direction.LEFT,
            Direction.RIGHT, Direction.SHOOT, Direction.DOWN, Direction.UP, Direction.SHOOT, Direction.SHOOT };
    /**
     * WARS, q, backspace, tab codes
     */
    public static final int[] COLEMAK_KEYS = { 8, 9, 20, 65, 81, 82, 83, 87, 161, 192 }; // wars,
    // q/backspace/backquote
    /**
     * WARSQ... key map
     */
    public static final Direction[] COLEMAK_KEYS_MAP = { Direction.SHOOT, Direction.SHOOT, Direction.SHOOT,
            Direction.LEFT, Direction.SHOOT, Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.SHOOT, Direction.SHOOT };
    
    /**
     * @param world
     *            World the KeyController controls
     * @param canvas
     *            Canvas which should listen for keys
     */
    public KeyController(World world, JDrawingCanvas canvas) {
        Object[][] keystrokes = {
                { KeyStroke.getKeyStroke(8, 0, false), "back_space", new KeyAction(8, false, false, false, world) },
                { KeyStroke.getKeyStroke(9, 0, false), "tab", new KeyAction(9, false, false, false, world) },
                { KeyStroke.getKeyStroke(10, 0, false), "enter", new KeyAction(10, false, false, false, world) },
                { KeyStroke.getKeyStroke(27, 0, false), "escape", new KeyAction(27, false, false, false, world) },
                { KeyStroke.getKeyStroke(32, 0, false), "space", new KeyAction(32, false, false, false, world) },
                { KeyStroke.getKeyStroke(161, 0, false), "lshift", new KeyAction(161, false, false, false, world) },
                { KeyStroke.getKeyStroke(160, 0, false), "rshift", new KeyAction(160, false, false, false, world) },
                { KeyStroke.getKeyStroke(37, 0, false), "left", new KeyAction(37, false, false, false, world) },
                { KeyStroke.getKeyStroke(38, 0, false), "up", new KeyAction(38, false, false, false, world) },
                { KeyStroke.getKeyStroke(39, 0, false), "right", new KeyAction(39, false, false, false, world) },
                { KeyStroke.getKeyStroke(40, 0, false), "down", new KeyAction(40, false, false, false, world) },
                { KeyStroke.getKeyStroke(45, 0, false), "minus", new KeyAction(45, false, false, false, world) },
                { KeyStroke.getKeyStroke(49, 0, false), "one", new KeyAction(49, false, false, false, world) },
                { KeyStroke.getKeyStroke(50, 0, false), "two", new KeyAction(50, false, false, false, world) },
                { KeyStroke.getKeyStroke(51, 0, false), "three", new KeyAction(51, false, false, false, world) },
                { KeyStroke.getKeyStroke(52, 0, false), "four", new KeyAction(52, false, false, false, world) },
                { KeyStroke.getKeyStroke(53, 0, false), "five", new KeyAction(53, false, false, false, world) },
                { KeyStroke.getKeyStroke(54, 0, false), "six", new KeyAction(54, false, false, false, world) },
                { KeyStroke.getKeyStroke(55, 0, false), "seven", new KeyAction(55, false, false, false, world) },
                { KeyStroke.getKeyStroke(56, 0, false), "eight", new KeyAction(56, false, false, false, world) },
                { KeyStroke.getKeyStroke(61, InputEvent.SHIFT_DOWN_MASK, false), "plus",
                        new KeyAction(61, false, false, false, world) },
                { KeyStroke.getKeyStroke(65, 0, false), "a", new KeyAction(65, false, false, false, world) },
                { KeyStroke.getKeyStroke(68, 0, false), "d", new KeyAction(68, false, false, false, world) },
                { KeyStroke.getKeyStroke(81, 0, false), "q", new KeyAction(81, false, false, false, world) },
                { KeyStroke.getKeyStroke(82, 0, false), "r", new KeyAction(82, false, false, false, world) },
                { KeyStroke.getKeyStroke(83, 0, false), "s", new KeyAction(83, false, false, false, world) },
                { KeyStroke.getKeyStroke(87, 0, false), "w", new KeyAction(87, false, false, false, world) },
                { KeyStroke.getKeyStroke(192, 0, false), "tab2", new KeyAction(192, false, false, false, world) } };

        canvas.setFocusTraversalKeysEnabled(false);

        for (Object[] keystroke : keystrokes) {
            canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) keystroke[0], (String) keystroke[1]);
            canvas.getActionMap().put((String) keystroke[1], (KeyAction) keystroke[2]);
        }
    }
}
