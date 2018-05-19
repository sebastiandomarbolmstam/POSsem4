package se.kth.IV1350.dbHandler;

import java.sql.SQLException;

/**
 * Checked exception class that is thrown when database fails in some unknown way. 
 */
public class DataBaseFailException extends SQLException{
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an exception with a printable message using methods in super class.
	 * @param message Message to be printed at exception occurrences. 
	 */
	public DataBaseFailException(String message) {
			super(message);	
		}

}
