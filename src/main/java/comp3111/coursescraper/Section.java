package comp3111.coursescraper;

import javafx.scene.paint.Color;
import java.util.Random;
import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 * Section class to store sections of a course.
 * @author Jeff, zzhangcl, zxiaac
 *
 */
public class Section {
	/**
     * the number of max slots
     */
	private static final int DEFAULT_MAX_SLOT = 3;
	/**
     * the number of max instructors
     */
	private static final int DEFAULT_MAX_INS = 20;
	/**
     * the course code
     */
	private String courseCode;
	/**
     * the course name
     */
	private String courseName;
	/**
     * the section id
     */
	private String id; // 1809
	/**
     * the section code
     */
	private String code; //L1 LA1 T1
	/**
     * all the slots belongs to the section
     */
	private ArrayList<Slot> slots;
	/**
     * the color to be displayed in timetable
     */
	private Color c;
	/**
     * all the instructors
     */
	private ArrayList<Instructor> instructors;
	/**
     * the sfq of this section
     */
	private double secsfq;
	/**
     * if the section has been drawn on timetable or not
     * @author zxiaac
     */
	public boolean draw=false;
	/**
     * constructor for section, assign a random color to it
     * @author zxiaac
     */
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
     * @author zzhangcl
     *
     */
	public double getSecSfq() {
		return secsfq;
	}
	 /**
     * set the sfq value for the course
     * @author zzhangcl
     * @param sfq the value of sfq
     *
     */
	public void setSecSfq(double sfq) {
		this.secsfq =sfq;
	}
	/**
	 * overrides Object.clone() function
	 * clone this section
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
	 * if the section is the same as another section
	 * @author Jeff
	 * @param sec compare the current section to another to check if they equals
	 * @return equal or not
	 */
	public boolean equals(Section sec) {
		if (id == sec.getid()) return true;
		return false;
	}
	/**
	 * the string of this section
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
	 * add slot
	 * @author Jeff
	 * @param s new slot to be added to this section
	 */
	public void addSlot(Slot s) {
		if (getNumSlots() >= DEFAULT_MAX_SLOT)
			return;
		slots.add(s.clone());
	}
	/**
	 * get slot i
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
	 * get color
	 * @author zxiaac
	 * @return the section color, which is randomly assigned when the section is created
	 */
	public Color getColor() {
		return c;
	}
  /**
   * add instructor
	 * @author Jeff
	 * @param i a new instructor to be added to this section
	 */
	public void addInstructor(Instructor i) {
		instructors.add(i.clone());
	}
	/**
	 * get all the instructors
	 * @author Jeff
	 * @return an ArrayList of all instructors that teaches this section
	 */
	public ArrayList<Instructor> getInstructors() {
		return (ArrayList<Instructor>)instructors.clone(); // do we need clone???
	}
	/**
	 * get section id
	 * @author Jeff
	 * @return the id
	 */
	public String getid() {
		return id;
	}

	/**
	 * set section id
	 * @author Jeff
	 * @param id the id to set
	 */
	public void setid(String id) {
		this.id = id;
	}
	/**
	 * get section code
	 * @author Jeff
	 * @return the code (e.g. L1 / T1 / LA1)
	 */
	public String getcode() {
		return code;
	}

	/**
	 * set section code
	 * @author Jeff
	 * @param code the code to set
	 */
	public void setcode(String code) {
		this.code = code;
	}

	/**
	 * get the number of slots
	 * @author Jeff
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return slots.size();
	}
	/**
	 * get course code
	 * @author Jeff
	 * @return the course code
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * set course name
	 * @author Jeff
	 * @param s the course name to be set to the section.
	 */
	public void setCourseName(String s) {
		courseName = s;
	}
	/**
	 * set course code
	 * @author Jeff
	 * @param s the course code to be set to the section.
	 */
	public void setCourseCode(String s) {
		courseCode = s;
	}
	/**
	 * get course name
	 * @author Jeff
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
}
