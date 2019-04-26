package comp3111.coursescraper;


import org.junit.Test;

import comp3111.coursescraper.Course;

import static org.junit.Assert.*;


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
}
