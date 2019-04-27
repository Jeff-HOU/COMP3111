package comp3111.coursescraper;

public class SubjectNotValidError extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubjectNotValidError(String errorMessage) {
        super(errorMessage);
    }
}