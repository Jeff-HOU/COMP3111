package comp3111.coursescraper;

public class Section {
	private static final int DEFAULT_MAX_SLOT = 3;
	private static final int DEFAULT_MAX_INS = 20;
	private String id; // 1809
	private String courseCode;
	private String courseName;
	private String code; //L1 LA1 T1
	private Slot [] slots;
	private int numSlots;
	private Instructor [] instructors;
	private int numInstructors;
	private double secsfq;
	
	public Section() {
		slots = new Slot[DEFAULT_MAX_SLOT];
		for (int i = 0; i < DEFAULT_MAX_SLOT; i++) slots[i] = null;
		numSlots = 0;
		instructors = new Instructor[DEFAULT_MAX_INS];
		for (int i = 0; i < DEFAULT_MAX_INS; i++) instructors[i] = null;
		numInstructors = 0;
		secsfq=0;
	}
	
	@Override
	public Section clone() {
		Section s = new Section();
		s.secsfq=this.secsfq;
		s.id = this.id;
		s.code = this.code;
		s.slots = this.slots.clone();
		s.numSlots = this.numSlots;
		s.instructors = this.instructors.clone();
		s.numInstructors = this.numInstructors;
		s.courseCode = this.courseCode;
		s.courseName = this.courseName;
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
		if (numSlots >= DEFAULT_MAX_SLOT)
			return;
		slots[numSlots++] = s.clone();
	}
	public Slot getSlot(int i) {
		if (i >= 0 && i < numSlots)
			return slots[i];
		return null;
	}
	public void addInstructor(Instructor i) {
		instructors[numInstructors++] = i.clone();
	}
	public Instructor [] getInstructors() {
		return instructors.clone(); // do we need clone???
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
		return numSlots;
	}

	/**
	 * @param numSlots the numSlots to set
	 */
	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}
	
	public void setCourseCode(String s) {
		courseCode = s;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseName(String s) {
		courseName = s;
	}
	public String getCourseName() {
		return courseName;
	}
	public double getSecSfq() {
		return secsfq;
	}
	public void setSecSfq(double sfq) {
		this.secsfq =sfq;
	}
}
