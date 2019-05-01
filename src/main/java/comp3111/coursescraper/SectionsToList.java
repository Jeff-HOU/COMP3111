package comp3111.coursescraper;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.control.CheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class created for the purpose of displaying sections selected on table view
 * @author Jeff
 */
public class SectionsToList {
	/**
     * course code of the section
     */
	private StringProperty courseCode;
	/**
     * the section code
     */
	private StringProperty section;
	/**
     * the course name
     */
	private StringProperty courseName;
	/**
     * the instructor
     */
	private StringProperty instructor;
	/**
     * if the section enrolled or not
     */
	private BooleanProperty enrolled;
	/**
	 * @author Jeff
	 * @param _courseCode course code
	 * @param _section section code (L1 / LA1 / T1)
	 * @param _courseName course name
	 * @param _instructor section instructor
	 * @param _enrolled user selected to enroll or not
	 */
	SectionsToList(String _courseCode, String _section, String _courseName, String _instructor, boolean _enrolled){
		courseCode = new SimpleStringProperty(_courseCode);
		section = new SimpleStringProperty(_section);
		courseName = new SimpleStringProperty(_courseName);
		/**
		 * Note that the instructor is stored as string.
		 * If there are multiple instructors, they will be join to make a string.
		 */
		instructor = new SimpleStringProperty(_instructor);
		enrolled = new SimpleBooleanProperty(_enrolled);
	}
	/**
	 * @author Jeff
	 * @param _courseCode course code
	 * @param _section section code (L1 / LA1 / T1)
	 * @param _courseName course name
	 * @param _instructor section instructor
	 * @param _enrolled user selected to enroll or not
	 */
	SectionsToList(String _courseCode, String _lectureSection, String _courseName, ArrayList<Instructor> _instructor, boolean _enrolled){
		courseCode = new SimpleStringProperty(_courseCode);
		section = new SimpleStringProperty(_lectureSection);
		courseName = new SimpleStringProperty(_courseName);
		String instructor_string = "";
		for (Instructor inst: _instructor) {
			instructor_string += inst.toString() + " ";
		}
		instructor = new SimpleStringProperty(instructor_string);
		enrolled = new SimpleBooleanProperty(_enrolled);
	}
	/**
	 * overrides Object.clone() function
	 * @return a clone of itself
	 */
	@Override
	public SectionsToList clone() {
		SectionsToList s = new SectionsToList(getCourseCode(), getSection(), getCourseName(), getInstructor(), getEnrolled());
		return s;
	}
	/**
	 * @return abstract class information to string
	 */
	public String toString() {
		String s = courseCode + " " + courseName + " " + section;
		s += enrolled.get() ? " enrolled" : " not enrolled";
		return s;
	}
	/**
	 * 
	 * @param sectl compare the current SectionsToList to another to check if they equals or not
	 * @return equal or not
	 */
	public boolean equals(SectionsToList sectl) {
		if (courseCode.get().equals(sectl.getCourseCode()) && section.get().equals(sectl.getSection())) return true;
		return false;
	}
	/**
	 * @author Jeff
	 * @return course code in string format
	 */
	public String getCourseCode() {
		return courseCode.get();
	}
	/**
	 * @author Jeff
	 * @param s course code to be set
	 */
	public void setCourseCode(String s) {
		courseCode.set(s);
	}
	/**
	 * @author Jeff
	 * @return section code in string format
	 */
	public String getSection() {
		return section.get();
	}
	/**
	 * @author Jeff
	 * @param s section code to be set
	 */
	public void setSection(String s) {
		section.set(s);
	}
	/**
	 * @author Jeff
	 * @return course name in string format
	 */
	public String getCourseName() {
		return courseName.get();
	}
	/**
	 * @author Jeff
	 * @param s course name to be set
	 */
	public void setCourseName(String s) {
		courseName.set(s);
	}
	/**
	 * @author Jeff
	 * @return instructor(s) in string format
	 */
	public String getInstructor() {
		return instructor.get();
	}
	/**
	 * @author Jeff
	 * @param s instructor(s) to be set
	 */
	public void setInstructor(String s) {
		instructor.set(s);;
	}
	/**
	 * @author Jeff
	 * @return get enrolled status in boolean format
	 */
	public boolean getEnrolled() {
		return enrolled.get();
	}
	/**
	 * @author Jeff
	 * @param b enrolled status to be set
	 */
	public void setEnrolled(boolean b) {
		enrolled.set(b);;
	}
	/**
	 * @author Jeff
	 * @return get course code in StringProperty
	 */
	public StringProperty courseCodeProperty() {
	    return courseCode;
	}
	/**
	 * @author Jeff
	 * @return get section code in StringProperty
	 */
	public StringProperty sectionProperty() {
	    return section;
	}
	/**
	 * @author Jeff
	 * @return get section code in StringProperty
	 */
	public StringProperty courseNameProperty() {
	    return courseName;
	}
	/**
	 * @author Jeff
	 * @return get instructor(s) in StringProperty
	 */
	public StringProperty instructorProperty() {
	    return instructor;
	}
	/**
	 * @author Jeff
	 * @return get enrolled status in BooleanProperty
	 */
	public BooleanProperty enrolledProperty() {
		return enrolled;
	}
}

/**
 * 
 * @author Jeff
 * implements a comparator for ranking purpose in TableView display.
 */
class SortSectionsToList implements Comparator<SectionsToList> 
{ 
    /**
     * @author Jeff
     * Used for sorting in ascending order of roll number
     */
    public int compare(SectionsToList a, SectionsToList b) 
    { 
        if (a.getCourseCode().equals(b.getCourseCode())) {
        	return a.getSection().compareTo(b.getSection());
        } else {
        	return a.getCourseCode().compareTo(b.getCourseCode());
        }
    } 
} 
