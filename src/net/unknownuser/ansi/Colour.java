package net.unknownuser.ansi;

/**
 * Various colours for printing in a console.
 */
public class Colour extends Default {
	
	protected Colour(int code) {
		super(code);
	}
	
	// all foreground colours
	public static final Colour FOREGROUND_BLACK = new Colour(30);
	public static final Colour FOREGROUND_RED = new Colour(31);
	public static final Colour FOREGROUND_GREEN = new Colour(32);
	public static final Colour FOREGROUND_YELLOW = new Colour(33);
	public static final Colour FOREGROUND_BLUE = new Colour(34);
	public static final Colour FOREGROUND_PURPLE = new Colour(35);
	public static final Colour FOREGROUND_CYAN = new Colour(36);
	public static final Colour FOREGROUND_WHITE = new Colour(37);
	
	public static final Colour FOREGROUND_BRIGHT_BLACK = new Colour(90);
	public static final Colour FOREGROUND_BRIGHT_RED = new Colour(91);
	public static final Colour FOREGROUND_BRIGHT_GREEN = new Colour(92);
	public static final Colour FOREGROUND_BRIGHT_YELLOW = new Colour(93);
	public static final Colour FOREGROUND_BRIGHT_BLUE = new Colour(94);
	public static final Colour FOREGROUND_BRIGHT_PURPLE = new Colour(95);
	public static final Colour FOREGROUND_BRIGHT_CYAN = new Colour(96);
	public static final Colour FOREGROUND_BRIGHT_WHITE = new Colour(97);
	
	// all background colours
	public static final Colour BACKROUND_BLACK = new Colour(40);
	public static final Colour BACKROUND_RED = new Colour(41);
	public static final Colour BACKROUND_GREEN = new Colour(42);
	public static final Colour BACKROUND_YELLOW = new Colour(43);
	public static final Colour BACKROUND_BLUE = new Colour(44);
	public static final Colour BACKROUND_PURPLE = new Colour(45);
	public static final Colour BACKROUND_CYAN = new Colour(46);
	public static final Colour BACKROUND_WHITE = new Colour(47);
	
	public static final Colour BACKROUND_BRIGHT_BLACK = new Colour(100);
	public static final Colour BACKROUND_BRIGHT_RED = new Colour(101);
	public static final Colour BACKROUND_BRIGHT_GREEN = new Colour(102);
	public static final Colour BACKROUND_BRIGHT_YELLOW = new Colour(103);
	public static final Colour BACKROUND_BRIGHT_BLUE = new Colour(104);
	public static final Colour BACKROUND_BRIGHT_PURPLE = new Colour(105);
	public static final Colour BACKROUND_BRIGHT_CYAN = new Colour(106);
	public static final Colour BACKROUND_BRIGHT_WHITE = new Colour(107);
	
	/**
	 * Converts a string to a ANSI formatted string with the given style.
	 * 
	 * @param text  The text, which should be formatted.
	 * @param style The style of the text.
	 * @return The ANSI formatted string.
	 */
	public static String colourString(String text, Colour style) {
		return style.getCode() + text + Default.ALL_RESET;
	}
	
	/**
	 * Formats an escape sequence to display a custom 24 bit colour. Essentially a standard
	 * RGB colour.
	 * 
	 * @param r The red intensity
	 * @param g The green intensity
	 * @param b The blue intensity
	 * @param isBackground Whether the colour should be for the background
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 */
	public static String get24BitColour(int r, int g, int b, boolean isBackground) {
		if(!is8Bit(r) || !is8Bit(g) || !is8Bit(b)) {
			return "";
		}
		
		return String.format("\u001B[%d;2;%d;%d;%dm",(isBackground ? 48 : 38) , r, g, b);
	}
	
	/**
	 * Formats an escape sequence to display a custom 24 bit foreground colour. Essentially a standard
	 * RGB colour.
	 * 
	 * @param r The red intensity
	 * @param g The green intensity
	 * @param b The blue intensity
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 */
	public static String get24BitColour(int r, int g, int b) {
		return get24BitColour(r, g, b, false);
	}
	
	/**
	 * Returns an escape sequence for the given colour on the standard ANSI colour table.<br>
	 * The codes are:
	 * 
	 * <pre>
	 *   0 -   7: standard colours
	 *   8 -  15: high intensity or bright colours
	 *  16 - 231: colours on a 6x6x6 colour cube
	 * 232 - 255: grey colours, smaller number means darker grey</pre>
	 * 
	 * @param id The pre defined colour number
	 * @param isBackground Whether the colour should be for the background
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit">The colour codes for each id</a>
	 */
	public static String get8BitColour(int id, boolean isBackgorund) {
		if(!is8Bit(id)) {
			return "";
		}
		
		return String.format("\u001B[%d;5;%dm", (isBackgorund ? 48 : 38), id);
	}
	
	/**
	 * Returns an escape sequence for the given colour on the standard ANSI colour table.<br>
	 * The codes are:
	 * 
	 * <pre>
	 *   0 -   7: standard colours
	 *   8 -  15: high intensity or bright colours
	 *  16 - 231: colours on a 6x6x6 colour cube
	 * 232 - 255: grey colours, smaller number means darker grey</pre>
	 * 
	 * @param id The pre defined colour number
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit">The colour codes for each id</a>
	 */
	public static String get8BitColour(int id) {
		return get8BitColour(id, false);
	}
}