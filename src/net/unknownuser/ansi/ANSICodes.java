package net.unknownuser.ansi;

public enum ANSICodes {
	// https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
	
	// resets all attributes
	ALL_RESET(0),
	
	// all foreground colours
	COLOUR_FOREGROUND_BLACK(30),
	COLOUR_FOREGROUND_RED(31),
	COLOUR_FOREGROUND_GREEN(32),
	COLOUR_FOREGROUND_YELLOW(33),
	COLOUR_FOREGROUND_BLUE(34),
	COLOUR_FOREGROUND_PURPLE(35),
	COLOUR_FOREGROUND_CYAN(36),
	COLOUR_FOREGROUND_WHITE(37),
	
	COLOUR_FOREGROUND_BRIGHT_BLACK(90),
	COLOUR_FOREGROUND_BRIGHT_RED(91),
	COLOUR_FOREGROUND_BRIGHT_GREEN(92),
	COLOUR_FOREGROUND_BRIGHT_YELLOW(93),
	COLOUR_FOREGROUND_BRIGHT_BLUE(94),
	COLOUR_FOREGROUND_BRIGHT_PURPLE(95),
	COLOUR_FOREGROUND_BRIGHT_CYAN(96),
	COLOUR_FOREGROUND_BRIGHT_WHITE(97),
	
	// all background colours
	COLOUR_BACKROUND_BLACK(40),
	COLOUR_BACKROUND_RED(41),
	COLOUR_BACKROUND_GREEN(42),
	COLOUR_BACKROUND_YELLOW(43),
	COLOUR_BACKROUND_BLUE(44),
	COLOUR_BACKROUND_PURPLE(45),
	COLOUR_BACKROUND_CYAN(46),
	COLOUR_BACKROUND_WHITE(47),
	
	COLOUR_BACKROUND_BRIGHT_BLACK(100),
	COLOUR_BACKROUND_BRIGHT_RED(101),
	COLOUR_BACKROUND_BRIGHT_GREEN(102),
	COLOUR_BACKROUND_BRIGHT_YELLOW(103),
	COLOUR_BACKROUND_BRIGHT_BLUE(104),
	COLOUR_BACKROUND_BRIGHT_PURPLE(105),
	COLOUR_BACKROUND_BRIGHT_CYAN(106),
	COLOUR_BACKROUND_BRIGHT_WHITE(107),
	
	// various styles
	STYLE_BOLD(1),
	STYLE_ITALIC(3),
	STYLE_UNDERLINE(4),
	STYLE_INVERT(7),
	STYLE_HIDE(8),
	STYLE_STRIKETHROUGH(9),
	STYLE_DOUBLE_UNDERLINE(21),
	STYLE_FRAMED(51),
	
	// other styles
	STYLE_NORMAL_INTENSITY(22),
	STYLE_NOT_ITALIC_OR_BLACKLETTER(23),
	STYLE_NOT_UNDERLINED(24),
	STYLE_BLINK_SLOW(5),
	STYLE_BLINK_FAST(6),
	STYLE_NOT_BLINKING(25),
	STYLE_NOT_REVERSED(27),
	STYLE_REVEALED(28), // undo hide
	STYLE_NOT_CROSSED(29),
	STYLE_NOT_FRAMED_OR_ENCIRCLED(54),
	
	// untested
	STYLE_FAINT(2),
	STYLE_FRAKTUR(20),
	STYLE_ENCIRCLED(52),
	STYLE_OVERLINE(53);
	
	private final String escapeCodeDefault = "\u001B[%sm";
	
	private final int codeNumber;
	private static boolean enabled = true;
	
	private ANSICodes(int code) {
		this.codeNumber = code;
	}
	
	/**
	 * Activates or deactives the ANSI formatting. All methods will return an empty string if the
	 * methods are disabled. Does not affect {@link #getCodeNumber()}.
	 *  
	 * @param set The new state of the formatter and creators.
	 */
	public static void setEnabled(boolean set) {
		enabled = set;
	}
	
	/**
	 * Returns whether the methods are activated.
	 *  
	 * @return The current state of the methods.
	 */
	public static boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Returns the escape sequence for the given code.
	 * 
	 * @return The Unicode character for the given format.
	 */
	public String getCode() {
		if(enabled) {
			return String.format(escapeCodeDefault, codeNumber);
		} else {
			return "";
		}
	}
	
	/**
	 * Returns the code number of the given code. Does not give you the escape sequence, used for
	 * formatting.
	 * 
	 * @return The code number of the specified code.
	 * @see ANSICodes#getCode()
	 */
	public int getCodeNumber() {
		return codeNumber;
	}
	
	/**
	 * Converts a string to a ANSI formatted string with the given style.
	 * 
	 * @param text  The text, which should be formatted.
	 * @param style The style of the text.
	 * @return The ANSI formatted string.
	 */
	public static String colourString(String text, ANSICodes style) {
		if(enabled) {
			return style.getCode() + text + ANSICodes.ALL_RESET;
		} else {
			return "";
		}
	}
	
	/**
	 * Creates an escape code for multiple styles at once. Some special combinations may not work properly.
	 * 
	 * @param codes
	 * @return
	 */
	public static String multiStyle(ANSICodes... codes) {
		if(!enabled || codes.length == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\u001B[");
		
		for(ANSICodes code : codes) {
			sb.append(code.getCodeNumber() + ";");
		}
		sb.deleteCharAt(sb.length() - 1);
		
		sb.append("m");
		
		return sb.toString();
	}
	
	/**
	 * Tests whether a given integer is 8 bit compatible (between 0 and 255, inclusive).
	 * 
	 * @param i The integer to test
	 * @return {@code true} if the integer in in range of an 8 bit integer, {@code false} otherwise.
	 */
	private static boolean is8Bit(int i) {
		return i >= 0 && i <= 255;
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
		if(!enabled || !is8Bit(r) || !is8Bit(g) || !is8Bit(b)) {
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
		if(!enabled || !is8Bit(id)) {
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
	
	@Override
	public String toString() {
		return getCode();
	}
}