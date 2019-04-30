package comp3111.coursescraper;


import org.junit.Test;

import comp3111.coursescraper.Course;
import javafx.scene.control.Label;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;


public class ItemTest {

	@Test
	public void testSetTitle() {
		Course i = new Course();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	
	@Test
	public void testSlotTimeStart() {
		Slot s = new Slot();
		s.setStart("02:00AM");
		assertEquals(s.getStartHour(), 2);
		assertEquals(s.getStartMinute(), 0);
	}
	
	@Test
	public void testSlotTimeEnd() {
		Slot s = new Slot();
		s.setEnd("02:00AM");
		assertEquals(s.getEndHour(), 2);
		assertEquals(s.getEndMinute(), 0);
	}
	
	@Test
	public void testSlotDay() {
		Slot s = new Slot();
		s.setDay(2);
		assertEquals(s.getDay(), 2);
	}
	
	@Test
	public void testSectionToStr() {
		Section s = new Section();
		Slot s1 = new Slot();
		s1.setDay(0);
		s1.setStart("09:00AM");
		s1.setEnd("10:00AM");
		s1.setVenue("LTA");
		s.setcode("LA1");
		s.setCourseCode("COMP1001");
		s.setCourseName("Introduction");
		s.setid("1909");
		assertEquals(s.toString(),"LA1:\n");
		s.addSlot(s1);
		assertEquals(s.toString(),"LA1:\n\t"+s1+"\n");
		
		
	}
	
	@Test
	public void testSlotEqual() {
		Slot s1 = new Slot();
		s1.setDay(0);
		s1.setStart("09:00AM");
		s1.setEnd("10:00AM");
		s1.setVenue("LTA");
		Slot s2 = s1.clone();
		assertTrue(s1.equals(s2));
		Slot s3 = s1.clone();
		s3.setDay(1);
		assertFalse(s1.equals(s3));
		Slot s4 = s1.clone();
		s4.setStart("11:00AM");
		assertFalse(s1.equals(s4));
		Slot s5 = s1.clone();
		s5.setEnd("11:00AM");
		assertFalse(s1.equals(s5));
	}
	@Test
	public void testCourseName() {
		Section s = new Section();
		s.setCourseName("Introduction to Computer Science");
		assertEquals(s.getCourseName(),"Introduction to Computer Science");
	}
	
	@Test
	public void testSectionEqual() {
		Section s1 = new Section();
		s1.setCourseCode("COMP1001");
		s1.setcode("L1");
		s1.setid("1808");
		Section s2 = s1.clone();
		assertTrue(s1.equals(s2));
		Section s3 = new Section();
		s3.setCourseCode("COMP4621");
		s3.setid("1202");
		assertFalse(s1.equals(s3));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSectionSfq() {
		Section s1 = new Section();
		s1.setSecSfq(0.9);
		assertEquals(s1.getSecSfq(),0.9,0.0001);
	}
	
	@Test
	public void testSecid() {
		Section s1 = new Section();
		Section s2 = new Section();
		s1.setid("1908");
		s2.setid("1909");
		Assert.assertNotEquals(s1.getid(), s2.getid());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestCourseSfq() {
		Course c1 = new Course();
		c1.setSfq(0.9);
		assertEquals(c1.getSfq(),0.9,0.0001);
	}
	
	@Test
	public void testCourseSection() {
		Course c = new Course();
		c.setCourseCode("COMP1001");
		assertEquals(c.getCourseCode(),"COMP1001");
		Section s = new Section();
		s.setCourseCode("COMP1001");
		assertEquals(s.getCourseCode(),"COMP1001");
		s.setcode("L1");
		assertEquals(s.getcode(),"L1");
		s.setid("1809");
		assertEquals(s.getid(),"1809");
		Slot s1 = new Slot();
		s1.setDay(0);
		s.addSlot(s1);
		c.addSection(s);
		
		assertNotNull(c.getAllSections());
		Section s6 = c.getSection(0);
		//assertEquals(c.getSection(0),s);
		
		assertTrue(c.getSectionsThatHaveSlotOnDay(0).size()==1);
		assertFalse(c.getSectionsThatHaveSlotOnDay(1).size()==1);
		
		assertEquals(c.getNumSections(),1);
		assertTrue(c.hasSection(s));
		Section s2 = new Section();
		s2.setCourseCode("COMP4411");
		assertFalse(c.hasSection(s2));
		
		Section s3 = s.clone();
		s3.setcode("L2");
		assertFalse(c.hasSection(s3));
		
		Section s4 = s.clone();
		s4.setCourseCode("COMP1991");
		assertFalse(c.hasSection(s4));
	}
	
	@Test
	public void testCourse2() {
		Course c2 = new Course();
		assertNull(c2.getSection(2));
		assertNull(c2.getSection(-3));
		
		c2.setDescription("This is a good course");
		assertEquals(c2.getDescription(),"This is a good course");
		c2.setExclusion("COMP1001");
		assertEquals(c2.getExclusion(),"COMP1001");
	}
	
	@Test
	public void testCourse3() {
		Course c3 = new Course();
		c3.setcc4y(true);
		assertTrue(c3.getcc4y());
		c3.setnoexclusion(true);
		assertTrue(c3.getnoexclusion());
	}
	
	@Test
	public void testSlotVenue() {
		Slot s = new Slot();
		s.setVenue("LTB");
		assertEquals(s.getVenue(), "LTB");
	}
	
	
	@Test
	public void testSlotClone() {
		Slot s = new Slot();
		s.setDay(1);s.setStart("02:00AM");s.setEnd("04:00AM");s.setVenue("LTJ");
		Slot s2 = s.clone();
		Slot s3 = new Slot();
		s3.setDay(1);s3.setStart("02:00AM");s3.setEnd("04:00AM");s3.setVenue("LTK");
		assertEquals(s2.getDay(), s.getDay());
		assertEquals(s2.getEnd(), s.getEnd());
		assertEquals(s2.getStart(), s.getStart());
		assertEquals(s2.getVenue(), s.getVenue());
		assertTrue(s.equals(s2));
		assertFalse(s.equals(s3));
	}
	
	@Test
	public void testSectionSlot() {
		Slot s = new Slot();
		Slot s1 = new Slot();
		Slot s2 = new Slot();
		Slot s3 = new Slot();
		Section sec = new Section();
		sec.setid("1809");
		sec.addSlot(s);
		assertEquals(sec.getNumSlots(), 1);
		sec.addSlot(s1);sec.addSlot(s2);sec.addSlot(s3);
		assertEquals(sec.getNumSlots(), 3);
		assertEquals(sec.getSlot(6), null);
		assertEquals(sec.getSlot(-8), null);
		Section sec2 = new Section();
		sec2.setid("1809");
		assertTrue(sec2.equals(sec));
	}
	
	@Test
	public void testInstructor() {
		Instructor inst = new Instructor("HHHH");
		Instructor inst2 = new Instructor("HH");
		assertTrue(inst.equals(inst.clone()));
		assertFalse(inst.equals(inst2));
	}
	
	@Test
	public void testCourse() {
		Course c = new Course();
		c.setExclusion("null");
		assertEquals(c.getnoexclusion(), true);
		Course c2 = new Course();
		c2.setExclusion("123");
		assertEquals(c2.getnoexclusion(), false);
	}
	
	@Test
	public void testScraperSubject() {
		Scraper s = new Scraper();
		try {
			s.scrapeSubject("htps://w5.ab.ust.hk/wcq/cgi-bin/", "1830");
		} catch (Exception e) {
			assertTrue(e instanceof UrlNotValidError);
		}
		try {
			s.scrapeSubject("https://w5.ab.ust.hk/wcq/cgi-bin/", "180");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrapeSubject("https://w5.ab.ust.hk/wcq/cgi-bin/", "1831");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrapeSubject("https://w5.ab.ust.hk/wcq/cgi-bin/", "1850");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrapeSubject("https://w5.ab.ust.hk/wcq/cgi-bin/", "1830");
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test()
	public void testScraper() {
		Scraper s = new Scraper();
		try {
			s.scrape("htps://w5.ab.ust.hk/wcq/cgi-bin/", "1830", "COMP");
		} catch (Exception e) {
			assertTrue(e instanceof UrlNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "180", "COMP");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "1831", "COMP");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "1850", "COMP");
		} catch (Exception e) {
			assertTrue(e instanceof TermNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "1830", "CMP");
		} catch (Exception e) {
			assertTrue(e instanceof SubjectNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "1830", "C9MP");
		} catch (Exception e) {
			assertTrue(e instanceof SubjectNotValidError);
		}
		try {
			s.scrape("https://w5.ab.ust.hk/wcq/cgi-bin/", "1830", "COMP");
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testSectionToList1() {
		SectionsToList slt = new SectionsToList("COMP1991","L1","Industrial Training","TBD",false);
		SectionsToList slt2 = slt.clone();
		SectionsToList slt3 = slt.clone();
		slt3.setSection("L2");
		slt3.setEnrolled(true);
		assertEquals(slt.toString(),slt.courseCodeProperty() + " " + slt.courseNameProperty() + " " + slt.sectionProperty()+" not enrolled");
		assertEquals(slt3.toString(),slt3.courseCodeProperty() + " " + slt3.courseNameProperty() + " " + slt3.sectionProperty()+" enrolled");
		assertTrue(slt.equals(slt2));
		assertFalse(slt.equals(slt3));
		slt3.setCourseCode("COMP1001");
		assertFalse(slt.equals(slt3));
		assertEquals(slt3.getCourseCode(),"COMP1001");
		assertEquals(slt3.getSection(),"L2");
		slt3.setCourseName("Introduction");
		assertEquals(slt3.getCourseName(),"Introduction");
		slt3.setInstructor("LLL");
		assertEquals(slt3.getInstructor(),"LLL");
		assertTrue(slt3.getEnrolled());
	}
	
	@Test
	public void testSectionToList2() {
		Instructor i1 = new Instructor();
		Instructor i2 = new Instructor();
		i1.setName("AAA");
		i2.setName("BBB");
		ArrayList<Instructor> iList =  new ArrayList();
		iList.add(i2);
		iList.add(i1);
		SectionsToList slt = new SectionsToList("COMP1991","L1","Industrial Training",iList,false);
	}
	
	@Test
	public void testSort() {
		SectionsToList slt = new SectionsToList("COMP1991","L1","Industrial Training","TBD",false);
		SectionsToList slt3 = new SectionsToList("COMP1991","L2","Industrial Training","TBD",false);
		SectionsToList slt2 = new SectionsToList("COMP1001","L2","Introduction","AAA",true);
		ArrayList<SectionsToList> sectionsToList = new ArrayList<SectionsToList>();
		sectionsToList.add(slt);
		sectionsToList.add(slt2);
		sectionsToList.add(slt3);
		Collections.sort(sectionsToList, new SortSectionsToList());
		assertEquals(sectionsToList.get(0),slt2);
	}
	@Test
	public void testAmPm() {
		Slot s = new Slot();
		s.setDay(1);s.setStart("02:00AM");s.setEnd("04:00AM");s.setVenue("LTJ");
		Slot s2 = s.clone();
		Slot s3 = new Slot();
		s3.setDay(1);s3.setStart("01:00PM");s3.setEnd("03:00PM");s3.setVenue("LTK");
		
		Section sec = new Section();
		sec.setid("1809");sec.setcode("L1");sec.setCourseCode("COMP1001");sec.setCourseName("Introduction");
		sec.addSlot(s);sec.addSlot(s2);sec.addSlot(s3);
		Section sec2 = new Section();
		sec2.addSlot(s2);sec2.addSlot(s3);
		Section sec3 = new Section();
		sec3.addSlot(s3);
		
		Course c = new Course();
		c.setCourseCode("COMP1001");
		c.addSection(sec); c.addSection(sec2);c.addSection(sec3);
		//c.getSectionsThatHaveAMSlots();
		c.getSectionsThatHaveAMandPMSlots();
		assertTrue(c.getSectionsThatHaveAMSlots().size()==2);
		assertTrue(c.getSectionsThatHavePMSlots().size()==3);
		
	}
	
	
}
