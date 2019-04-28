package comp3111.coursescraper;

/**
 * Exception that catch invalid subject
 * @author Jeff
 *
 */
public class SubjectNotValidError extends Exception { 
    /**
	 * 
	 * @author Jeff
	 * Don't know why this is useful, but Eclipse report warnings if I don't add it.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @author Jeff
	 * @param errorMessage error message (project)
	 */
	public SubjectNotValidError(String errorMessage) {
        super(errorMessage);
    }
}