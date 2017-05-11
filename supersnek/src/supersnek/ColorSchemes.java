package supersnek;

import java.awt.Color;

/**
 * Class to hold color scheme constants SHOULD NOT BE INSTANTIATED
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class ColorSchemes {
    /**
     * Red color scheme
     */
    public static final Color[] RED_SCHEME = { new Color(0xFF5252), new Color(0xFF1744), new Color(0xFF8A80),
            new Color(0xD50000) };
    /**
     * Pink color scheme
     */
    public static final Color[] PINK_SCHEME = { new Color(0xff4081), new Color(0xf50057), new Color(0xff80ab),
            new Color(0xc51162) };
    /**
     * Purple color scheme
     */
    public static final Color[] PURPLE_SCHEME = { new Color(0xe040fb), new Color(0xd500f9), new Color(0xea80fc),
            new Color(0xaa00ff) };
    /**
     * Deep purple color scheme
     */
    public static final Color[] DEEP_PURPLE_SCHEME = { new Color(0x7c4dff), new Color(0x651fff), new Color(0xb388ff),
            new Color(0x6200ea) };
    /**
     * Indigo color scheme
     */
    public static final Color[] INDIGO_SCHEME = { new Color(0x536dfe), new Color(0x3d5afe), new Color(0x8c9eff),
            new Color(0x304ffe) };
    /**
     * Blue color scheme
     */
    public static final Color[] BLUE_SCHEME = { new Color(0x448AFF), new Color(0x2979FF), new Color(0x82B1FF),
            new Color(0x2962FF) };
    /**
     * Light blue color scheme
     */
    public static final Color[] LIGHT_BLUE_SCHEME = { new Color(0x40c4ff), new Color(0x00b0ff), new Color(0x80d8ff),
            new Color(0x0091ea) };
    /**
     * Cyan color scheme
     */
    public static final Color[] CYAN_SCHEME = { new Color(0x18ffff), new Color(0x00e5ff), new Color(0x84ffff),
            new Color(0x00b8d4) };
    /**
     * Teal color scheme
     */
    public static final Color[] TEAL_SCHEME = { new Color(0x64ffda), new Color(0x1de9b6), new Color(0xa7ffeb),
            new Color(0x00bfa5) };
    /**
     * Green color scheme
     */
    public static final Color[] GREEN_SCHEME = { new Color(0x69F0AE), new Color(0x00E676), new Color(0xB9F6CA),
            new Color(0x00C853) };
    /**
     * Light green color scheme
     */
    public static final Color[] LIGHT_GREEN_SCHEME = { new Color(0xb2ff59), new Color(0x76ff03), new Color(0xccff90),
            new Color(0x64dd17) };
    /**
     * Lime color scheme
     */
    public static final Color[] LIME_SCHEME = { new Color(0xeeff41), new Color(0xc6ff00), new Color(0xf4ff81),
            new Color(0xaeea00) };
    /**
     * Yellow color scheme
     */
    public static final Color[] YELLOW_SCHEME = { new Color(0xffff00), new Color(0xffea00), new Color(0xffff8d),
            new Color(0xffd600) };
    /**
     * Amber color scheme
     */
    public static final Color[] AMBER_SCHEME = { new Color(0xffd740), new Color(0xffc400), new Color(0xffe57f),
            new Color(0xffab00) };
    /**
     * Orange color scheme
     */
    public static final Color[] ORANGE_SCHEME = { new Color(0xffab40), new Color(0xff9100), new Color(0xffd180),
            new Color(0xff6d00) };

    /**
     * 2D array of all color schemes
     */
    public static final Color[][] ALL_SCHEMES = { ColorSchemes.RED_SCHEME, ColorSchemes.PINK_SCHEME,
            ColorSchemes.PURPLE_SCHEME, ColorSchemes.DEEP_PURPLE_SCHEME, ColorSchemes.INDIGO_SCHEME,
            ColorSchemes.BLUE_SCHEME, ColorSchemes.LIGHT_BLUE_SCHEME, ColorSchemes.CYAN_SCHEME,
            ColorSchemes.TEAL_SCHEME, ColorSchemes.GREEN_SCHEME, ColorSchemes.LIGHT_GREEN_SCHEME,
            ColorSchemes.LIME_SCHEME, ColorSchemes.YELLOW_SCHEME, ColorSchemes.AMBER_SCHEME,
            ColorSchemes.ORANGE_SCHEME };
    /**
     * Rainbow of color schemes
     */
    public static final Color[] RAINBOW_SCHEME = { ColorSchemes.RED_SCHEME[0], ColorSchemes.PINK_SCHEME[1],
            ColorSchemes.PURPLE_SCHEME[2], ColorSchemes.DEEP_PURPLE_SCHEME[3], ColorSchemes.INDIGO_SCHEME[0],
            ColorSchemes.BLUE_SCHEME[1], ColorSchemes.LIGHT_BLUE_SCHEME[2], ColorSchemes.CYAN_SCHEME[3],
            ColorSchemes.TEAL_SCHEME[0], ColorSchemes.GREEN_SCHEME[1], ColorSchemes.LIGHT_GREEN_SCHEME[2],
            ColorSchemes.LIME_SCHEME[3], ColorSchemes.YELLOW_SCHEME[0], ColorSchemes.AMBER_SCHEME[1],
            ColorSchemes.ORANGE_SCHEME[2], ColorSchemes.RED_SCHEME[1], ColorSchemes.PINK_SCHEME[2],
            ColorSchemes.PURPLE_SCHEME[3], ColorSchemes.DEEP_PURPLE_SCHEME[0], ColorSchemes.INDIGO_SCHEME[1],
            ColorSchemes.BLUE_SCHEME[2], ColorSchemes.LIGHT_BLUE_SCHEME[3], ColorSchemes.CYAN_SCHEME[0],
            ColorSchemes.TEAL_SCHEME[1], ColorSchemes.GREEN_SCHEME[2], ColorSchemes.LIGHT_GREEN_SCHEME[3],
            ColorSchemes.LIME_SCHEME[0], ColorSchemes.YELLOW_SCHEME[1], ColorSchemes.AMBER_SCHEME[2],
            ColorSchemes.ORANGE_SCHEME[3], ColorSchemes.RED_SCHEME[2], ColorSchemes.PINK_SCHEME[3],
            ColorSchemes.PURPLE_SCHEME[0], ColorSchemes.DEEP_PURPLE_SCHEME[1], ColorSchemes.INDIGO_SCHEME[2],
            ColorSchemes.BLUE_SCHEME[3], ColorSchemes.LIGHT_BLUE_SCHEME[0], ColorSchemes.CYAN_SCHEME[1],
            ColorSchemes.TEAL_SCHEME[2], ColorSchemes.GREEN_SCHEME[3], ColorSchemes.LIGHT_GREEN_SCHEME[0],
            ColorSchemes.LIME_SCHEME[1], ColorSchemes.YELLOW_SCHEME[2], ColorSchemes.AMBER_SCHEME[3],
            ColorSchemes.ORANGE_SCHEME[0], ColorSchemes.RED_SCHEME[3], ColorSchemes.PINK_SCHEME[0],
            ColorSchemes.PURPLE_SCHEME[1], ColorSchemes.DEEP_PURPLE_SCHEME[2], ColorSchemes.INDIGO_SCHEME[3],
            ColorSchemes.BLUE_SCHEME[0], ColorSchemes.LIGHT_BLUE_SCHEME[1], ColorSchemes.CYAN_SCHEME[2],
            ColorSchemes.TEAL_SCHEME[3], ColorSchemes.GREEN_SCHEME[0], ColorSchemes.LIGHT_GREEN_SCHEME[1],
            ColorSchemes.LIME_SCHEME[2], ColorSchemes.YELLOW_SCHEME[3], ColorSchemes.AMBER_SCHEME[0],
            ColorSchemes.ORANGE_SCHEME[1] };
}
