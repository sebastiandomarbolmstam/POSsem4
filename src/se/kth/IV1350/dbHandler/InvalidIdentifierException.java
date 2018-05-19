package se.kth.IV1350.dbHandler;

import java.time.LocalDateTime;

/**
 * Checked exception class thrown in case of invalid identifier entry by user. 
 */
public class InvalidIdentifierException extends Exception {
	private static final long serialVersionUID = 1L;
	private String itemIdentifier;
	
	/**
	 * Creates an instance of the exception with the entered identifier (cause of error), and a default
	 * message to print if exception is thrown. 
	 * @param itemIdentifier Identifier entered to cause miss match error.
	 */
	public InvalidIdentifierException(String itemIdentifier) {
		super("Runtime error: invalid identifier entered at:" + LocalDateTime.now() + "\n");
		this.itemIdentifier = itemIdentifier;
	}
	
	/**
	 * @return Item identifier that was invalid.
	 */
	public String getIdentifier() {
		return this.itemIdentifier;
	}
}
