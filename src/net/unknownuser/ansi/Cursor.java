package net.unknownuser.ansi;

/**
 * Various codes for manipulating the cursor or already printed lines.<br>
 * Unlike the other implementations, this class' codeNumber is a char, as all codes don't use
 * numbers, but character to represent them.
 */
public class Cursor extends Default {
	private Cursor(int code) {
		super(code);
	}
	
	public static final Cursor UP = new Cursor('A');
	public static final Cursor DOWN = new Cursor('B');
	public static final Cursor RIGHT = new Cursor('C');
	public static final Cursor LEFT = new Cursor('D');
	public static final Cursor NEXT_LINE = new Cursor('E');
	public static final Cursor PREVIOUS_LINE = new Cursor('F');
	/**
	 * Moves the cursor to column 1
	 */
	public static final Cursor CURSOR_HORIZONTAL_ABSOLUTE = new Cursor('G');
	/**
	 * For {@link Cursor#multiEffect(int) multiEffect}:<br>
	 * 0: clear from cursor to end of screen (default)<br>
	 * 1: clear from cursor to beginning of screen<br>
	 * 2: clear entire screen<br>
	 * 3: clear entire screen and clear scroll buffer
	 */
	public static final Cursor ERASE_IN_DISPLAY = new Cursor('J');
	/**
	 * For {@link Cursor#multiEffect(int) multiEffect}:<br>
	 * 0: clear from cursor to end of line (default)<br>
	 * 1: clear form cursor to beginning of line<br>
	 * 2: clear line<br>
	 * does not change cursor position
	 */
	public static final Cursor ERASE_LINE = new Cursor('K');
	/**
	 * scolls up by 1, <b>adds new lines at bottom</b>
	 */
	public static final Cursor SCROLL_UP = new Cursor('S');
	/**
	 * scolls down by 1, <b>adds new lines at top</b>
	 */
	public static final Cursor SCROLL_DOWN = new Cursor('T');
	
	@Override
	public String getCode() {
		return ESCAPE_CODE_DEFAULT + Character.toString(codeNumber);
	}
	
	/**
	 * Return a code, which has this code's effect {@code amount} times.
	 * 
	 * @param amount The amount of times this code's effect is repeated.
	 * @return The code, which has the desired effect {@code amount} of times.
	 */
	public String multiEffect(int amount) {
		return ESCAPE_CODE_DEFAULT + amount + Character.toString(codeNumber);
	}
	
	/**
	 * All codes support the multiplication of their respective effect, e.g. instead of printing
	 * {@link Cursor#UP UP} multiple times, it is possible to print "move cursor up 5 lines" with the
	 * same effect.
	 * 
	 * @param amount The amount of times the code's effect is repeated.
	 * @param code   The code to be repeated.
	 * @return The code, which has the desired effect {@code amount} of times.
	 */
	public static String multiEffect(int amount, Cursor code) {
		return ESCAPE_CODE_DEFAULT + amount + Character.toString(code.codeNumber);
	}
	
	/**
	 * Sets the cursor position to the specified row and column. The position starts at the top left.
	 * 
	 * @param row    The row of the new cursor position.
	 * @param column The column of the new cursor position.
	 * @return The code to set the cursor to the specified one.
	 */
	public static String setCursorPosition(int row, int column) {
		return String.format("%s%d;%dH", ESCAPE_CODE_DEFAULT, row, column);
	}
	
	/**
	* Sets the cursor position to row 0 and column 0.<br>
	* Same as {@link #setCursorPosition(int, int) setCursorPosition} to 0,0.
	*
	* @return The code, which sets the cursor to 0,0.
	*/
	public static String setCursorToStart() {
		return ESCAPE_CODE_DEFAULT + "H";
	}
	
	/**
	 * Sets the cursor position to the specified row and column. The position starts at the top
	 * left.<br>
	 * Unlike {@link Cursor#setCursorPosition(int, int) setCursorPosition} this code is interpreted as
	 * a format effector (like CR or LF).
	 * 
	 * @param row    The row of the new cursor position.
	 * @param column The column of the new cursor position.
	 * @return The code to set the cursor to the specified one.
	 */
	public static String setCursorPositionFE(int row, int column) {
		return String.format("%s%d;%df", ESCAPE_CODE_DEFAULT, row, column);
	}
}
