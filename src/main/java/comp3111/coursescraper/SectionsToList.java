package comp3111.coursescraper;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SectionsToList {
	private StringProperty courseCode;
	private StringProperty section;
	private StringProperty courseName;
	private StringProperty instructor;
	private BooleanProperty enrolled;
	SectionsToList(String _courseCode, String _section, String _courseName, String _instructor, boolean _enrolled){
		courseCode = new SimpleStringProperty(_courseCode);
		section = new SimpleStringProperty(_section);
		courseName = new SimpleStringProperty(_courseName);
		instructor = new SimpleStringProperty(_instructor);
		enrolled = new SimpleBooleanProperty(_enrolled);
	}
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
	@Override
	public SectionsToList clone() {
		SectionsToList s = new SectionsToList(getCourseCode(), getSection(), getCourseName(), getInstructor(), getEnrolled());
		return s;
	}
	public String toString() {
		String s = courseCode + " " + courseName + " " + section;
		s += enrolled.get() ? " enrolled" : " not enrolled";
		return s;
	}
	public boolean equals(SectionsToList sectl) {
		if (courseCode.equals(sectl.courseCode) && section.equals(sectl.section)) return true;
		return false;
	}
	public String getCourseCode() {
		return courseCode.get();
	}
	public void setCourseCode(String s) {
		courseCode.set(s);
	}
	public String getSection() {
		return section.get();
	}
	public void setSection(String s) {
		section.set(s);
	}
	public String getCourseName() {
		return courseName.get();
	}
	public void setCourseName(String s) {
		courseName.set(s);
	}
	public String getInstructor() {
		return instructor.get();
	}
	public void setInstructor(String s) {
		instructor.set(s);;
	}
	public boolean getEnrolled() {
		return enrolled.get();
	}
	public void setEnrolled(boolean b) {
		enrolled.set(b);;
	}
	public StringProperty courseCodeProperty() {
	    return courseCode;
	}
	public StringProperty sectionProperty() {
	    return section;
	}
	public StringProperty courseNameProperty() {
	    return courseName;
	}
	public StringProperty instructorProperty() {
	    return instructor;
	}
	public BooleanProperty enrolledProperty() {
		return enrolled;
	}
}
