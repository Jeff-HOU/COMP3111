package comp3111.coursescraper;

/**
 * Class created to store instructor information.
 * @author Jeff, zzhangcl
 *
 */
public class Instructor {
	/**
     * the instructor name
     */
	private String name;
	/**
     * the sfq of the instructor
     */
	private double sfq;
	/**
     * the number of sections the instructor teach
     * @author zzhangcl
     */
	public int numSection;
	/**
	 * standard constructor
	 * @author Jeff, zzhangcl
	 * 
	 */
	public Instructor() {
		name = "";
		sfq = 0.0;
		numSection=1;
	}
	/**
	 * the instructors
	 * @author Jeff, zzhangcl
	 * @param s constructor from instructor name: s
	 */
	public Instructor(String s) {
		name = s;
		sfq = 0.0;
		numSection=1;
	}
	/**
	 * overrides Object.clone() function
	 * @author Jeff
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
	 * if the 2 object equals
	 * @author Jeff
	 * @param i compare the current Instructor to another to check if they equals or not
	 * @return equal or not based on their names
	 */
	@Override
	public boolean equals(Object i) {
		if (name.equals(((Instructor)i).name)) return true;
		return false;
	}
	/**get the name of the instructor
	 * @return abstract class information to string
	 * 
	 * @author Jeff, zzhangcl
	 */
	public String toString() {
		return name;
	}
	/**
	 * set the name of instructor
	 * @author Jeff
	 * @param name instructor name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get the instructor's name
	 * @author Jeff
	 * @return instructor's name
	 */
	public String getName() {
		return name;
	}
	 /**
     * set the sfq value for the instructor
     * 
     *
     * @author zzhangcl
     * @param sfq the sfq value to be set

     */
	public void setSfq(double sfq) {
		this.sfq = sfq;
	}
	 /**
     * get the sfq value for the instructor
     * 
     * @return value of course sfq (double type)
     * @author zzhangcl
     *
     */
	public double getSfq() {
		return sfq;
	}
	 /**
     * get the number of sections does the instructor teach
     * 
     * @return number of how many section does the instructor teach (int type)
     * @author zzhangcl
     *
     */
	public int getNumSection() {
		return numSection;
	}
}
