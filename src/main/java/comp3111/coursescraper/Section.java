package comp3111.coursescraper;

import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 * Section class to store sections of a course.
 * @author Jeff, Ziyue, zxiaac
 *
 */
public class Section {
	private static final int DEFAULT_MAX_SLOT = 3;
	private static final int DEFAULT_MAX_INS = 20;
	private String courseCode;
	private String courseName;
	private String id; // 1809
	private String code; //L1 LA1 T1
	private ArrayList<Slot> slots;
	private Color c;
	private ArrayList<Instructor> instructors;
	private double secsfq;
	public boolean draw=false;
	public Section() {
		Random r = new Random();
		c = Color.color((double)r.nextInt(Integer.MAX_VALUE)/(Integer.MAX_VALUE-1), (double)r.nextInt(Integer.MAX_VALUE)/(Integer.MAX_VALUE-1), (double)r.nextInt(Integer.MAX_VALUE)/(Integer.MAX_VALUE-1),0.5);
		slots = new ArrayList<Slot>();
		instructors = new ArrayList<Instructor>();
		secsfq=0;
		
	}
	 /**
     * get the sfq value for the section
     * 
     * @return value of section sfq (double type)
     * @author Ziyue
     *
     */
	public double getSecSfq() {
		return secsfq;
	}
	 /**
     * set the sfq value for the course
     * @author Ziyue
     * @param sfq the value of sfq
     *
     */
	public void setSecSfq(double sfq) {
		this.secsfq =sfq;
	}
	/**
	 * overrides Object.clone() function
	 * @author Jeff, zxiaac
	 * @return a clone of itself
	 */
	@Override
	public Section clone() {
		Section s = new Section();
		s.id = this.id;
		s.code = this.code;
		s.c = this.c;
		s.slots = (ArrayList<Slot>)this.slots.clone();
		s.instructors = (ArrayList<Instructor>)this.instructors.clone();
		s.courseCode = this.courseCode;
		s.courseName = this.courseName;
		s.secsfq=this.secsfq;
		return s;
	}
	/**
	 * @author Jeff
	 * @param sec compare the current section to another to check if they equals
	 * @return equal or not
	 */
	public boolean equals(Section sec) {
		if (id == sec.getid()) return true;
		return false;
	}
	/**
	 * @author Jeff
	 * @return abstract class information to string
	 */
	public String toString() {
		String out = code + ":\n";
		for (Slot s: slots) {
			if (s != null) out += "\t" + s + "\n";
		}
		return out;
	}
	/**
	 * @author Jeff
	 * @param s new slot to be added to this section
	 */
	public void addSlot(Slot s) {
		if (getNumSlots() >= DEFAULT_MAX_SLOT)
			return;
		slots.add(s.clone());
	}
	/**
	 * @author Jeff
	 * @param i get the Slot at position i in the array of this section
	 * @return the Slot at position i in the array of this section
	 */
	public Slot getSlot(int i) {
		if (i >= 0 && i < getNumSlots())
			return slots.get(i);
		return null;
	}
	/**
	 * @author zxiaac
	 * @return the section color, which is randomly assigned when the section is created
	 */
	public Color getColor() {
		return c;
	}
  /**
	 * @author Jeff
	 * @param i a new instructor to be added to this section
	 */
	public void addInstructor(Instructor i) {
		instructors.add(i.clone());
	}
	/**
	 * @author Jeff
	 * @return an ArrayList of all instructors that teaches this section
	 */
	public ArrayList<Instructor> getInstructors() {
		return (ArrayList<Instructor>)instructors.clone(); // do we need clone???
	}
	/**
	 * @author Jeff
	 * @return the id
	 */
	public String getid() {
		return id;
	}

	/**
	 * @author Jeff
	 * @param id the id to set
	 */
	public void setid(String id) {
		this.id = id;
	}
	/**
	 * @author Jeff
	 * @return the code (e.g. L1 / T1 / LA1)
	 */
	public String getcode() {
		return code;
	}

	/**
	 * @author Jeff
	 * @param code the code to set
	 */
	public void setcode(String code) {
		this.code = code;
	}

	/**
	 * @author Jeff
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return slots.size();
	}
	/**
	 * @author Jeff
	 * @return the course code
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * @author Jeff
	 * @param s the course name to be set to the section.
	 */
	public void setCourseName(String s) {
		courseName = s;
	}
	/**
	 * @author Jeff
	 * @param s the course code to be set to the section.
	 */
	public void setCourseCode(String s) {
		courseCode = s;
	}
	/**
	 * @author Jeff
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
}
