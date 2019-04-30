package comp3111.coursescraper;

/**
 * Class created to store instructor information.
 * @author Jeff, Ziyue
 *
 */
public class Instructor {
	private String name;
	private double sfq;
	public int numSection;
	/**
	 * @author Jeff, Ziyue
	 * standard constructor
	 */
	public Instructor() {
		name = "";
		sfq = 0.0;
		numSection=1;
	}
	/**
	 * @author Jeff, Ziyue
	 * @param s constructor from instructor name: s
	 */
	public Instructor(String s) {
		name = s;
		sfq = 0.0;
		numSection=1;
	}
	/**
	 * overrides Object.clone() function
	 * @return a clone of itself
	 */
	@Override
	public Instructor clone() {
		Instructor i = new Instructor();
		i.name = this.name;
		i.sfq = this.sfq;
		i.numSection=this.numSection;
		return i;
	}
	/**
	 * @author Jeff
	 * @param i compare the current Instructor to another to check if they equals or not
	 * @return equal or not based on their names
	 */
	@Override
	public boolean equals(Object i) {
		if (name.equals(((Instructor)i).name)) return true;
		return false;
	}
	/**
	 * @return abstract class information to string
	 */
	public String toString() {
		return name;
	}
	/**
	 * @author Jeff
	 * @param name instructor name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @author Jeff
	 * @return instructor's name
	 */
	public String getName() {
		return name;
	}
	public void setSfq(double sfq) {
		this.sfq = sfq;
	}
	public double getSfq() {
		return sfq;
	}
	public int getNumSection() {
		return numSection;
	}
}
