package net.unknownuser.ansi;

/**
 * Various styles for printing in a console.
 */
public class Style extends Default {
	
	private Style(int code) {
		super(code);
	}

	public static final Style BOLD = new Style(1);
	public static final Style ITALIC = new Style(3);
	public static final Style UNDERLINE = new Style(4);
	public static final Style INVERT = new Style(7);
	public static final Style HIDE = new Style(8);
	public static final Style STRIKETHROUGH = new Style(9);
	public static final Style DOUBLE_UNDERLINE = new Style(21);
	public static final Style FRAMED = new Style(51);
	
	// other styles
	public static final Style NORMAL_INTENSITY = new Style(22);
	public static final Style NOT_ITALIC_OR_BLACKLETTER = new Style(23);
	public static final Style NOT_UNDERLINED = new Style(24);
	public static final Style BLINK_SLOW = new Style(5);
	public static final Style BLINK_FAST = new Style(6);
	public static final Style NOT_BLINKING = new Style(25);
	public static final Style NOT_REVERSED = new Style(27);
	public static final Style REVEALED = new Style(28); // undo hide
	public static final Style NOT_CROSSED = new Style(29);
	public static final Style NOT_FRAMED_OR_ENCIRCLED = new Style(54);
	
	// untested
	public static final Style FAINT = new Style(2);
	public static final Style FRAKTUR = new Style(20);
	public static final Style ENCIRCLED = new Style(52);
	public static final Style OVERLINE = new Style(53);
}
