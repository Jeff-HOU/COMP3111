package comp3111.coursescraper;

public class PageNotFoundError extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageNotFoundError(String errorMessage) {
        super(errorMessage);
    }
}