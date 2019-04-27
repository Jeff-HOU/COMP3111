package comp3111.coursescraper;

import java.util.ArrayList;

public class Section {
	private static final int DEFAULT_MAX_SLOT = 3;
	private static final int DEFAULT_MAX_INS = 20;
	private String courseCode;
	private String courseName;
	private String id; // 1809
	private String code; //L1 LA1 T1
	private ArrayList<Slot> slots;
	private ArrayList<Instructor> instructors;
	private double secsfq;
	public Section() {
		slots = new ArrayList<Slot>();
		instructors = new ArrayList<Instructor>();
		secsfq=0;
		
	}
	public double getSecSfq() {
		return secsfq;
	}
	public void setSecSfq(double sfq) {
		this.secsfq =sfq;
	}
	@Override
	public Section clone() {
		Section s = new Section();
		s.id = this.id;
		s.code = this.code;
		s.slots = (ArrayList<Slot>)this.slots.clone();
		s.instructors = (ArrayList<Instructor>)this.instructors.clone();
		s.courseCode = this.courseCode;
		s.courseName = this.courseName;
		s.secsfq=this.secsfq;
		return s;
	}
	public boolean equals(Section sec) {
		if (id == sec.getid()) return true;
		return false;
	}

	public String toString() {
		String out = code + ":\n";
		for (Slot s: slots) {
			if (s != null) out += "\t" + s + "\n";
		}
		return out;
	}
	
	public void addSlot(Slot s) {
		if (getNumSlots() >= DEFAULT_MAX_SLOT)
			return;
		slots.add(s.clone());
	}
	public Slot getSlot(int i) {
		if (i >= 0 && i < getNumSlots())
			return slots.get(i);
		return null;
	}
	public void addInstructor(Instructor i) {
		instructors.add(i.clone());
	}
	public ArrayList<Instructor> getInstructors() {
		return (ArrayList<Instructor>)instructors.clone(); // do we need clone???
	}
	/**
	 * @return the id
	 */
	public String getid() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setid(String id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getcode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setcode(String code) {
		this.code = code;
	}

	/**
	 * @return the numSlots
	 */
	public int getNumSlots() {
		return slots.size();
	}

	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseName(String s) {
		courseName = s;
	}

	
	public void setCourseCode(String s) {
		courseCode = s;
	}

	public String getCourseName() {
		return courseName;
	}
	
}
