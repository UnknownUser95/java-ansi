package net.unknownuser.ansi;

/**
 * A class which holds the basic functions used for ANSI codes as well as the RESET_ALL code.
 */
public class Default {
	protected static final String ESCAPE_CODE_DEFAULT = "\u001B[";
	
	public static final Default ALL_RESET = new Default(0);
	
	final int codeNumber;
	
	protected Default(int code) {
		this.codeNumber = code;
	}
	
	/**
	 * Tests whether a given integer is 8 bit compatible (between 0 and 255, inclusive).
	 * 
	 * @param i The integer to test
	 * @return {@code true} if the integer in in range of an 8 bit integer, {@code false} otherwise.
	 */
	protected static boolean is8Bit(int i) {
		return i >= 0 && i <= 255;
	}
	
	/**
	 * Returns the escape sequence for the given code.
	 * 
	 * @return The Unicode character for the given format.
	 */
	public String getCode() {
		return String.format(ESCAPE_CODE_DEFAULT + "%sm", getCodeNumber());
	}
	
	/**
	 * Returns the code number of the given code. Does not give you the escape sequence, used for
	 * formatting.
	 * 
	 * @return The code number of the specified code.
	 * @see Colour#getCode()
	 */
	public int getCodeNumber() {
		return codeNumber;
	}
	
	/**
	 * Creates an escape code for multiple styles at once. Some combinations may not work properly.<br>
	 * All {@link Cursor Cursor} codes will be ignored.
	 * 
	 * @param codes
	 * 
	 * @return The escape sequence for all of the styles combined.
	 */
	public static String multiStyle(Default... codes) {
		if(codes.length == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(ESCAPE_CODE_DEFAULT);
		
		for(Default code : codes) {
			if(code instanceof Cursor) {
				continue;
			}
			
			sb.append(code.getCodeNumber() + ";");
		}
		sb.deleteCharAt(sb.length() - 1);
		
		sb.append("m");
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return getCode();
	}
}
