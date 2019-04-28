package comp3111.coursescraper;

/**
 * Exception that catch invalid url
 * @author Jeff
 *
 */
public class UrlNotValidError extends Exception { 
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
	public UrlNotValidError(String errorMessage) {
        super(errorMessage);
    }
}