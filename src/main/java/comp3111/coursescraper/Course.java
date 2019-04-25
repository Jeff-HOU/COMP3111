package comp3111.coursescraper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Course {	
	private String title ; 
	private String description ;
	private String exclusion;
	private boolean cc4y; // CC-4Y
	private boolean tla; //has tutorial or lab
    private boolean noexclusion; // No exclusion
	private ArrayList<Section> sections;
	
	public Course() {
		sections = new ArrayList<Section>();
		cc4y = false;
		tla = false;
		noexclusion = false;
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
		if (exclusion.equals("null")) noexclusion = true;
	}
	
	/**
	 * @return the NumSections
	 */
	public int getNumSections() {
		return sections.size();
	}
	
	public void setcc4y(boolean b) {
		cc4y = b;
	}
	public boolean getcc4y() {
		return cc4y;
	}
	public void settla(boolean b) {
		tla = b;
	}
	public boolean gettla() {
		return tla;
	}
	public void setnoexclusion(boolean b) {
		noexclusion = b;
	}
	public boolean getnoexclusion() {
		return noexclusion;
	}
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
