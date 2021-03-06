package comp3111.coursescraper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Class created to store all courses scraped.
 * @author Jeff, zzhangcl
 *
 */
public class Course {	
	/**
	 * @author zxiaac
     * the name of the course
     */
	private String title ; 
	/**
	 * @author zxiaac
     * the description of the course
     */
	private String description ;
	/**
	 * @author Jeff
	 * exclusions of this course
	 */
	private String exclusion;
	/**
	 * @author Jeff
	 * Is this course a Common Core - 4Y?
	 */
	private boolean cc4y; // CC-4Y
	/**
	 * @author Jeff
	 * Does this course have a tutorial or lab section?
	 */
	private boolean tla; //has tutorial or lab
	/**
	 * @author Jeff
	 * Does this course have any exclusions?
	 */
	private boolean noexclusion; // No exclusion
    /**
     * a list of sections that belongs to the course 
     * @author zzhangcl
     *
     */
	
	private ArrayList<Section> sections;
    /**
     * the sfq for the course
     * 
     * 
     * @author zzhangcl
     *
     */
	private double sfq;
    /**
     * a list of sections that belongs to the course
     * 
     * 
     * @author zzhangcl
     *
     */
	private String courseCode;
    /**
     * set the course code for the course
     * 
     * @author zzhangcl
     * @param s the course code
     */

  	public void setCourseCode(String s)
	{
		this.courseCode=s;

  	}
    /**
     * set the sfq for the course
     * 
     * @param sfq: the sfq value to set
     * @author zzhangcl
     *
     */
  	public void setSfq(Double sfq)
	{
		this.sfq=sfq;

  	}
    /**
     * get the course code for the course
     * 
     * @return string type course code
     * @author zzhangcl
     *
     */

  	public String getCourseCode() {
		return courseCode;
	}
    /**
     * get the sfq value for the course
     * 
     * @return value of course sfq (double type)
     * @author zzhangcl
     *
     */
	public double getSfq() {
		return sfq;
	}
	
	/**
	 * all the courses
	 * @author Jeff, zzhangcl
	 */
	public Course() {
		sections = new ArrayList<Section>();
		cc4y = false;
		tla = false;
		noexclusion = false;
		sfq=0;
		courseCode="";


	}
	 /**
     * this function is to know whether the course has a certain section
     * 
     * @return true for has the section, false for don't have (boolean type)
     * @param s Section to be checked whether is in the course
     * @author zzhangcl
     *
     */
	public boolean hasSection(Section s)
	{
		for(Section sec:sections)
		{
		if (sec.getcode()==s.getcode()&&sec.getCourseCode()==s.getCourseCode())
		{
		return true;
		}
	}
	return false;
	}
	/**add section to course 
	 * @author Jeff
	 * @param s Section to be added
	 */
	public void addSection(Section s) {
		sections.add(s.clone());
	}
	/**get the section
	 * @author Jeff
	 * @param i get the Section at position i in the array of this Course
	 * @return Section at position i in the array of this Course
	 */
	public Section getSection(int i) {
		if (i >= 0 && i < sections.size())
			return sections.get(i);
		return null;
	}

	/** get the title of the course
	 * @return the title
	 * @author Jeff, zzhangcl
	 */
	public String getTitle() {
		return title;
	}

	/** set the title of the course
	 * 
	 * @param title: the title to set
	 * @author Jeff, zzhangcl
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** get the description for the course
	 * @author Jeff, zzhangcl
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/** set the description for the course
	 * @author Jeff, zzhangcl
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** get exclusion for the course
	 * @return the exclusion
	 * @author Jeff, zzhangcl
	 */
	public String getExclusion() {
		return exclusion;
	}

	/** set the exclusive course for the course
	 * @param exclusion the exclusion to set
	 * @author Jeff, zzhangcl
	 */
	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
		if (exclusion.equals("null")) noexclusion = true;
	}
	
	/** get the total number of Sections for the course
	 * @return the NumSections
	 * @author Jeff. zzhangcl
	 */
	public int getNumSections() {
		return sections.size();
	}
	/**
	 * set if is common core
	 * @author Jeff,zzhangcl
	 * @param b Common Core - 4Y attribute to be set
	 */
	public void setcc4y(boolean b) {
		cc4y = b;
	}
	/**
	 * get common core
	 * @author Jeff,zzhangcl
	 * @return If this is a Common Core - 4Y
	 */
	public boolean getcc4y() {
		return cc4y;
	}
	/**
	 * set if has lab or tutorial
	 * @author Jeff,zzhangcl
	 * @param b "Has a tutorial / lab section" attribute to be set
	 */
	public void settla(boolean b) {
		tla = b;
	}
	/**
	 * get if has lab or tutorial
	 * @author Jeff
	 * @return "Has a tutorial / lab section" attribute
	 */
	public boolean gettla() {
		return tla;
	}
	/**
	 * set if has no exclusion
	 * @author Jeff
	 * @param b "Has no exclusions" attribute to be set
	 */
	public void setnoexclusion(boolean b) {
		noexclusion = b;
	}
	/**
	 * get if has no exclusion
	 * @author Jeff
	 * @return "Has no exclusions" attribute
	 */
	public boolean getnoexclusion() {
		return noexclusion;
	}
	/**
	 * get sections that slot on day i
	 * @author Jeff,zzhangcl
	 * @param day get all Sections that have slots on "day"
	 * @return get all Sections that have slots on "day"
	 */
	public Vector<Section> getSectionsThatHaveSlotOnDay(int day) {
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {
			for (int i = 0; i < sec.getNumSlots(); i++) {
				Slot s = sec.getSlot(i);
				if (s.getDay() == day) {
					selected.add(sec);
					break;
				}
			}
		}
		return selected;
	}
	/**
	 * get sections that have AM slot
	 * @author Jeff,zzhangcl
	 * @return get all sections that have an AM slot
	 */
	public Vector<Section> getSectionsThatHaveAMSlots(){ // wait ta's response???
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {
			for (int i = 0; i < sec.getNumSlots(); i++) {
				Slot s = sec.getSlot(i);
				if (s.getStartHour() < 12) {
					selected.add(sec);
					break;
				}
			}
		}
		return selected;
	}
	/**
	 * get sections that has PM slots
	 * @author Jeff
	 * @return get all sections that have an PM slot
	 */
	public Vector<Section> getSectionsThatHavePMSlots(){ // wait ta's response???
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {
			for (int i = 0; i < sec.getNumSlots(); i++) {
				Slot s = sec.getSlot(i);
				if (s.getEndHour() >= 12) {
					selected.add(sec);
					break;
				}
			}
		}
		return selected;
	}
	 /**
     * get all the sections for the course
     * 
     * @return a vector of all the sections for the course
     * @author zzhangcl
     *
     */
	public Vector<Section> getAllSections(){ // wait ta's response???
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {

					selected.add(sec);

			
		}
		return selected;
	}
	/**
	 * get sections that have am and pm slots
	 * @author Jeff
	 * @return get sections that have both AM and PM slots
	 */
	public Vector<Section> getSectionsThatHaveAMandPMSlots(){ // wait ta's response???
		Vector<Section> selected_AM = getSectionsThatHaveAMSlots();
		Vector<Section> selected_PM = getSectionsThatHavePMSlots();
		selected_AM.retainAll(selected_PM); // section has slots across 12:00. below are sections have slot 1 in am slot 2 in pm.
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {
			boolean hasAMslot = false;
			boolean hasPMslot = false;
			for (int i = 0; i < sec.getNumSlots(); i++) {
				Slot s = sec.getSlot(i);
				if (s.getStartHour() >= 12) {
					hasPMslot = true;
				} else if (s.getEndHour() < 12) {
					hasAMslot = true;
				}
			}
			if (hasAMslot && hasPMslot) {
				selected.add(sec);
			}
		}
		selected.addAll(selected_AM);
		return selected;
	}

}
