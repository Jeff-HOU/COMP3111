package comp3111.coursescraper;
import java.util.ArrayList;
import java.util.Arrays;


public class Course {	
	private String title ; 
	private String description ;
	private String exclusion;
	private ArrayList<Section> sections;
	
	public Course() {
		sections = new ArrayList<Section>();
	}
	
	public void addSection(Section s) {
		sections.add(s.clone());
	}
	
	public Section getSection(int i) {
		if (i >= 0 && i < sections.size())
			return sections.get(i);
		return null;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the exclusion
	 */
	public String getExclusion() {
		return exclusion;
	}

	/**
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}
	
	/**
	 * @return the NumSections
	 */
	public int getNumSections() {
		return sections.size();
	}

}
