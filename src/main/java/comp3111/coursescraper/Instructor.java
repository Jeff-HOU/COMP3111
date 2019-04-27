package comp3111.coursescraper;

public class Instructor {
	private String name;
	private double sfq;
	public int numSection;
	public Instructor() {
		name = "";
		sfq = 0.0;
		numSection=1;
	}
	public Instructor(String s) {
		name = s;
		sfq = 0.0;
		numSection=1;
	}
	@Override
	public Instructor clone() {
		Instructor i = new Instructor();
		i.name = this.name;
		i.sfq = this.sfq;
		i.numSection=this.numSection;
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
