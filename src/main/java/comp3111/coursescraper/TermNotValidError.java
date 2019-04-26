package comp3111.coursescraper;

public class TermNotValidError extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TermNotValidError(String errorMessage) {
        super(errorMessage);
    }
}