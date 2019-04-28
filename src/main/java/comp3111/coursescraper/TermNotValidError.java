package comp3111.coursescraper;

/**
 * Exception that catch invalid terms
 * @author Jeff
 *
 */
public class TermNotValidError extends Exception { 
    /**
	 * 
	 * @author Jeff
	 * Don't know why this is useful, but Eclipse report warnings if I don't add it.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @author Jeff
	 * @param errorMessage error message (term)
	 */
	public TermNotValidError(String errorMessage) {
        super(errorMessage);
    }
}