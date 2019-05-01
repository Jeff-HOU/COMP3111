package comp3111.coursescraper;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Label;

/**
 * Class created to store all slots of a section.
 * @author Jeff, Kevin
 *
 */
public class Slot {
	/**
     * the day the slot belongs to
     */
	private int day;
	/**
     * the starttime of the slot
     */
	private LocalTime start;
	/**
     * the end time of the slot
     */
	private LocalTime end;
	/**
     * the venue of the slot
     */
	private String venue;
	/**
     * the label of this slot in tab time table
     */
	public Label la = null;
	/**
     * all the days in a week may has slots
     */
	public static final String DAYS[] = {"Mo", "Tu", "We", "Th", "Fr", "Sa"};
	/**
     * a hash table store all the days
     */
	public static final Map<String, Integer> DAYS_MAP = new HashMap<String, Integer>();
	static {
		for (int i = 0; i < DAYS.length; i++)
			DAYS_MAP.put(DAYS[i], i);
	}
	/**
	 * overrides Object.clone() function
	 * @return a clone of itself
	 */
	@Override
	public Slot clone() {
		Slot s = new Slot();
		s.day = this.day;
		s.start = this.start;
		s.end = this.end;
		s.venue = this.venue;
		return s;
	}
	/**
	 * 
	 * @param s compare the current slot to another to check if they equals or not
	 * @return equal or not
	 */
	public boolean equals(Slot s) {
		if (s.getDay() == day && s.getStart().equals(start) && s.getEnd().equals(end) && s.getVenue().equals(venue)) return true;
		return false;
	}
	/**
	 * @return abstract class information to string
	 */
	public String toString() {
		return DAYS[day] + " " + start.toString() + "-" + end.toString() + ":" + venue;
	}
	/**
	 * @return slot start hour
	 */
	public int getStartHour() {
		return start.getHour();
	}
	/**
	 * @return slot start minute
	 */
	public int getStartMinute() {
		return start.getMinute();
	}
	/**
	 * @return slot end hour
	 */
	public int getEndHour() {
		return end.getHour();
	}
	/**
	 * @return slot end minute
	 */
	public int getEndMinute() {
		return end.getMinute();
	}
	/**
	 * @return the start
	 */
	public LocalTime getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}
	/**
	 * @return the end
	 */
	public LocalTime getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("hh:mma", Locale.US));
	}
	/**
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

}
