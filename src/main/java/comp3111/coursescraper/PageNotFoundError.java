package comp3111.coursescraper;

/**
 * Exception that catch url accessing which will receive 404.
 * @author Jeff
 * 
 */
public class PageNotFoundError extends Exception { 
    /**
	 * 
	 * @author Jeff
	 * Don't know why this is useful, but Eclipse report warnings if I don't add it.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Jeff
	 * @param errorMessage error message (url)
	 */
	public PageNotFoundError(String errorMessage) {
        super(errorMessage);
    }
}