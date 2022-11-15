package net.unknownuser.ansi;

/**
 * Various colours for printing in a console.
 */
public abstract class Colour extends Default {
	protected Colour(int code) {
		super(code);
	}
	
	/**
	 * All standard foreground colours
	 */
	public static class Foreground extends Colour {
		protected Foreground(int code) {
			super(code);
		}
		
		public static final Colour BLACK = new Foreground(30);
		public static final Colour RED = new Foreground(31);
		public static final Colour GREEN = new Foreground(32);
		public static final Colour YELLOW = new Foreground(33);
		public static final Colour BLUE = new Foreground(34);
		public static final Colour PURPLE = new Foreground(35);
		public static final Colour CYAN = new Foreground(36);
		public static final Colour WHITE = new Foreground(37);
		
		public static final Colour BRIGHT_BLACK = new Foreground(90);
		public static final Colour BRIGHT_RED = new Foreground(91);
		public static final Colour BRIGHT_GREEN = new Foreground(92);
		public static final Colour BRIGHT_YELLOW = new Foreground(93);
		public static final Colour BRIGHT_BLUE = new Foreground(94);
		public static final Colour BRIGHT_PURPLE = new Foreground(95);
		public static final Colour BRIGHT_CYAN = new Foreground(96);
		public static final Colour BRIGHT_WHITE = new Foreground(97);
	}
	
	/**
	 * All standard background colours
	 */
	public static class Background extends Colour {
		protected Background(int code) {
			super(code);
		}
		
		public static final Colour BLACK = new Background(40);
		public static final Colour RED = new Background(41);
		public static final Colour GREEN = new Background(42);
		public static final Colour YELLOW = new Background(43);
		public static final Colour BLUE = new Background(44);
		public static final Colour PURPLE = new Background(45);
		public static final Colour CYAN = new Background(46);
		public static final Colour WHITE = new Background(47);
		
		public static final Colour BRIGHT_BLACK = new Background(100);
		public static final Colour BRIGHT_RED = new Background(101);
		public static final Colour BRIGHT_GREEN = new Background(102);
		public static final Colour BRIGHT_YELLOW = new Background(103);
		public static final Colour BRIGHT_BLUE = new Background(104);
		public static final Colour BRIGHT_PURPLE = new Background(105);
		public static final Colour BRIGHT_CYAN = new Background(106);
		public static final Colour BRIGHT_WHITE = new Background(107);
	}
	
	/**
	 * Converts a string to a ANSI formatted string with the given style.
	 * 
	 * @param text  The text, which should be formatted.
	 * @param style The style of the text.
	 * 
	 * @return The ANSI formatted string.
	 */
	public static String colourString(String text, Colour style) {
		return style.getCode() + text + Default.ALL_RESET;
	}
	
	/**
	 * Formats an escape sequence to display a custom 24 bit colour. Essentially a standard RGB colour.
	 * 
	 * @param r            The red intensity
	 * @param g            The green intensity
	 * @param b            The blue intensity
	 * @param isBackground Whether the colour should be for the background
	 * 
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 */
	public static String get24BitColour(int r, int g, int b, boolean isBackground) {
		if(!is8Bit(r) || !is8Bit(g) || !is8Bit(b)) {
			return "";
		}
		
		return String.format("\u001B[%d;2;%d;%d;%dm", (isBackground ? 48 : 38), r, g, b);
	}
	
	/**
	 * Formats an escape sequence to display a custom 24 bit foreground colour. Essentially a standard RGB colour.
	 * 
	 * @param r The red intensity
	 * @param g The green intensity
	 * @param b The blue intensity
	 * 
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 */
	public static String get24BitColour(int r, int g, int b) {
		return get24BitColour(r, g, b, false);
	}
	
	/**
	 * Returns an escape sequence for the given colour on the standard ANSI colour table.<br>
	 * Also known as indexed colours.<br>
	 * The codes are:
	 * 
	 * <pre>
	 *   0 -   7: standard colours
	 *   8 -  15: high intensity or bright colours
	 *  16 - 231: colours on a 6x6x6 colour cube
	 * 232 - 255: grey colours, smaller number means darker grey
	 * </pre>
	 * 
	 * @param id           The pre defined colour number
	 * @param isBackground Whether the colour should be for the background
	 * 
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 * 
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
	 * The code will be for foreground colours only. Use {@link #get8BitColour(int, boolean) get8BitColour} for background colours.<br>
	 * Also known as indexed colours.<br>
	 * The codes are:
	 * 
	 * <pre>
	 *   0 -   7: standard colours
	 *   8 -  15: high intensity or bright colours
	 *  16 - 231: colours on a 6x6x6 colour cube
	 * 232 - 255: grey colours, smaller number means darker grey
	 * </pre>
	 * 
	 * @param id The pre defined colour number
	 * 
	 * @return The ANSI sequence for the given colour. An empty string if any number is invalid.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit">The colour codes for each id</a>
	 */
	public static String get8BitColour(int id) {
		return get8BitColour(id, false);
	}
}