package comp3111.coursescraper;

public class UrlNotValidError extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UrlNotValidError(String errorMessage) {
        super(errorMessage);
    }
}