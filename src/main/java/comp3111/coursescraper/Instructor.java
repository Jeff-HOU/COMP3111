package comp3111.coursescraper;

public class Instructor {
	private String name;
	private String sfq;
	public Instructor() {
		name = "";
		sfq = "";
	}
	public Instructor(String s) {
		name = s;
		sfq = "";
	}
	@Override
	public Instructor clone() {
		Instructor i = new Instructor();
		i.name = this.name;
		i.sfq = this.sfq;
		return i;
	}
	
	public boolean equals(Instructor i) {
		if (name == i.name) return true;
		return false;
	}
	public String toString() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSfq(String sfq) {
		this.sfq = sfq;
	}
	public String getSfq() {
		return sfq;
	}
}
