package comp3111.coursescraper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Class created to store all courses scraped.
 * @author Jeff, Ziyue
 *
 */
public class Course {	
	private String title ; 
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
  
	private ArrayList<Section> sections;
	
	private double sfq;
	private String courseCode;


  	public void setCourseCode(String s)
	{
		this.courseCode=s;

  	}

  	public void setSfq(Double sfq)
	{
		this.sfq=sfq;

  	}


  	public String getCourseCode() {
		return courseCode;
	}
	public double getSfq() {
		return sfq;
	}
	
	/**
	 * @author Jeff, Ziyue
	 */
	public Course() {
		sections = new ArrayList<Section>();
		cc4y = false;
		tla = false;
		noexclusion = false;
		sfq=0;
		courseCode="";


	}
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
	/**
	 * @author Jeff
	 * @param s Section to be added
	 */
	public void addSection(Section s) {
		sections.add(s.clone());
	}
	/**
	 * @author Jeff
	 * @param i get the Section at position i in the array of this Course
	 * @return Section at position i in the array of this Course
	 */
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
		if (exclusion.equals("null")) noexclusion = true;
	}
	
	/**
	 * @return the NumSections
	 */
	public int getNumSections() {
		return sections.size();
	}
	/**
	 * @author Jeff
	 * @param b Common Core - 4Y attribute to be set
	 */
	public void setcc4y(boolean b) {
		cc4y = b;
	}
	/**
	 * @author Jeff
	 * @return If this is a Common Core - 4Y
	 */
	public boolean getcc4y() {
		return cc4y;
	}
	/**
	 * @author Jeff
	 * @param b "Has a tutorial / lab section" attribute to be set
	 */
	public void settla(boolean b) {
		tla = b;
	}
	/**
	 * @author Jeff
	 * @return "Has a tutorial / lab section" attribute
	 */
	public boolean gettla() {
		return tla;
	}
	/**
	 * @author Jeff
	 * @param b "Has no exclusions" attribute to be set
	 */
	public void setnoexclusion(boolean b) {
		noexclusion = b;
	}
	/**
	 * @author Jeff
	 * @return "Has no exclusions" attribute
	 */
	public boolean getnoexclusion() {
		return noexclusion;
	}
	/**
	 * @author Jeff
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
	 * @author Jeff
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

	public Vector<Section> getAllSections(){ // wait ta's response???
		Vector<Section> selected = new Vector<Section>();
		for (Section sec: sections) {

					selected.add(sec);

			
		}
		return selected;
	}
	/**
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
