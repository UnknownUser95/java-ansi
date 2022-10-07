package net.unknownuser.ansi;

/**
 * Controls for the screen.
 */
public abstract class Screen extends Default {
	private Screen() {
		super(0);
	}
	
	/**
	 * Makes a noise when printed.
	 */
	public static final String BELL = "\u0007";
	/**
	 * Saves the screen content.
	 */
	public static final String SAVE_SCREEN = ESCAPE_CODE_DEFAULT + "?47h";
	/**
	 * Restores the screen content saved in {@link #SAVE_SCREEN SAVE_SCREEN}.
	 */
	public static final String RESTORE_SCREEN = ESCAPE_CODE_DEFAULT + "?47l";
	/**
	 * Saves the current cursor position.
	 */
	public static final String SAVE_CURSOR_POSITION = ESCAPE_CODE_DEFAULT + "s";
	/**
	 * Restores the cursor position saved in {@link #SAVE_CURSOR_POSITION SAVE_CURSOR_POSITION}.
	 */
	public static final String RESTORE_CURSOR_POSITION = ESCAPE_CODE_DEFAULT + "u";
	/**
	 * Shows the cursor.
	 */
	public static final String SHOW_CURSOR = ESCAPE_CODE_DEFAULT + "?25h";
	/**
	 * Hides the cursor.
	 */
	public static final String HIDE_CURSOR = ESCAPE_CODE_DEFAULT + "?25l";
	/**
	 * Enables the screen buffer.
	 */
	public static final String ENABLE_SCREEN_BUFFER = ESCAPE_CODE_DEFAULT + "?1049h";
	/**
	 * Disables the screen buffer.
	 */
	public static final String DISABLE_SCREEN_BUFFER = ESCAPE_CODE_DEFAULT + "?1049l";
}
