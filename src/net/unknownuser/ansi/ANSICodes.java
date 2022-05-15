package net.unknownuser.ansi;

public enum ANSICodes {
	// https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
	
	// simply no style at all
	NONE(""),
	
	// resets all attributes
	ALL_RESET("\u001B[0m"),
	
	// all foreground colours
	COLOUR_FOREGROUND_BLACK("\u001B[30m"),
	COLOUR_FOREGROUND_RED("\u001B[31m"),
	COLOUR_FOREGROUND_GREEN("\u001B[32m"),
	COLOUR_FOREGROUND_YELLOW("\u001B[33m"),
	COLOUR_FOREGROUND_BLUE("\u001B[34m"),
	COLOUR_FOREGROUND_PURPLE("\u001B[35m"),
	COLOUR_FOREGROUND_CYAN("\u001B[36m"),
	COLOUR_FOREGROUND_WHITE("\u001B[37m"),
	
	COLOUR_FOREGROUND_BRIGHT_BLACK("\u001B[90m"),
	COLOUR_FOREGROUND_BRIGHT_RED("\u001B[91m"),
	COLOUR_FOREGROUND_BRIGHT_GREEN("\u001B[92m"),
	COLOUR_FOREGROUND_BRIGHT_YELLOW("\u001B[93m"),
	COLOUR_FOREGROUND_BRIGHT_BLUE("\u001B[94m"),
	COLOUR_FOREGROUND_BRIGHT_PURPLE("\u001B[95m"),
	COLOUR_FOREGROUND_BRIGHT_CYAN("\u001B[96m"),
	COLOUR_FOREGROUND_BRIGHT_WHITE("\u001B[97m"),
	
	// all background colours
	COLOUR_BACKROUND_BLACK("\u001B[40m"),
	COLOUR_BACKROUND_RED("\u001B[41m"),
	COLOUR_BACKROUND_GREEN("\u001B[42m"),
	COLOUR_BACKROUND_YELLOW("\u001B[43m"),
	COLOUR_BACKROUND_BLUE("\u001B[44m"),
	COLOUR_BACKROUND_PURPLE("\u001B[45m"),
	COLOUR_BACKROUND_CYAN("\u001B[46m"),
	COLOUR_BACKROUND_WHITE("\u001B[47m"),
	
	COLOUR_BACKROUND_BRIGHT_BLACK("\u001B[100m"),
	COLOUR_BACKROUND_BRIGHT_RED("\u001B[101m"),
	COLOUR_BACKROUND_BRIGHT_GREEN("\u001B[102m"),
	COLOUR_BACKROUND_BRIGHT_YELLOW("\u001B[103m"),
	COLOUR_BACKROUND_BRIGHT_BLUE("\u001B[104m"),
	COLOUR_BACKROUND_BRIGHT_PURPLE("\u001B[105m"),
	COLOUR_BACKROUND_BRIGHT_CYAN("\u001B[106m"),
	COLOUR_BACKROUND_BRIGHT_WHITE("\u001B[107m"),
	
	// various styles
	STYLE_BOLD("\u001B[1m"),
	STYLE_ITALIC("\u001B[3m"),
	STYLE_UNDERLINE("\u001B[4m"),
	STYLE_INVERT("\u001B[7m"),
	STYLE_HIDE("\u001B[8m"),
	STYLE_STRIKETHROUGH("\u001B[9m"),
	STYLE_DOUBLE_UNDERLINE("\u001B[21m"),
	STYLE_FRAMED("\u001B[51m"),
	
	// other styles
	STYLE_NORMAL_INTENSITY("\u001B[22m"),
	STYLE_NOT_ITALIC_OR_BLACKLETTER("\u001B[23m"),
	STYLE_NOT_UNDERLINED("\u001B[24m"),
	STYLE_BLINK_SLOW("\u001B[5m"),
	STYLE_BLINK_FAST("\u001B[6m"),
	STYLE_NOT_BLINKING("\u001B[25m"),
	STYLE_NOT_REVERSED("\u001B[27m"),
	STYLE_REVEALED("\u001B[28m"), // undo hide
	STYLE_NOT_CROSSED("\u001B[29m"),
	STYLE_NOT_FRAMED_OR_ENCIRCLED("\u001B[54m"),
	
	// untested
	STYLE_FAINT("\u001B[2m"),
	STYLE_FRAKTUR("\u001B[20m"),
	STYLE_ENCIRCLED("\u001B[52m"),
	STYLE_OVERLINE("\u001B[53m");
	
	private final String code;
	
	private ANSICodes(String code) {
		this.code = code;
	}
	
	/**
	 * Returns the code for the given code. Output has to have the capability to display ANSI
	 * formatted text.
	 * 
	 * @return The Unicode character for the given format.
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Returns the code number of the given code. Does not give you the useful string, used for
	 * formatting.
	 * 
	 * @return The code number of the specified code.
	 * @see ANSICodes#getCode()
	 */
	public int getCodeNumber() {
		return Integer.parseInt(getCode().substring(getCode().indexOf("[") + 1, getCode().indexOf("m")));
	}
	
	/**
	 * Converts a string to a ANSI formatted string with the given style.
	 * 
	 * @param text  The text, which should be formatted.
	 * @param style The style of the text.
	 * @return The ANSI formatted string.
	 */
	public static String colourString(String text, ANSICodes style) {
		return style.getCode() + text + ANSICodes.ALL_RESET;
	}
}