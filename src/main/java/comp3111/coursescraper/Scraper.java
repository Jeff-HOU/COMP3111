package comp3111.coursescraper;
import java.util.AbstractCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.net.URLEncoder;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javafx.scene.layout.AnchorPane;

import com.gargoylesoftware.htmlunit.html.DomText;
import java.util.Vector;

/**
 * WebScraper provide a sample code that scrape web content. After it is constructed, you can call the method scrape with a keyword, 
 * the client will go to the default url and parse the page by looking at the HTML DOM.  
 * <br>
 * In this particular sample code, it access to HKUST class schedule and quota page (COMP). 
 * <br>
 * https://w5.ab.ust.hk/wcq/cgi-bin/1830/subject/COMP
 *  <br>
 * where 1830 means the third spring term of the academic year 2018-19 and COMP is the course code begins with COMP.
 * <br>
 * Assume you are working on Chrome, paste the url into your browser and press F12 to load the source code of the HTML. You might be freak
 * out if you have never seen a HTML source code before. Keep calm and move on. Press Ctrl-Shift-C (or CMD-Shift-C if you got a mac) and move your
 * mouse cursor around, different part of the HTML code and the corresponding the HTML objects will be highlighted. Explore your HTML page from
 * body &rarr; div id="classes" &rarr; div class="course" &rarr;. You might see something like this:
 * <br>
 * <pre>
 * {@code
 * <div class="course">
 * <div class="courseanchor" style="position: relative; float: left; visibility: hidden; top: -164px;"><a name="COMP1001">&nbsp;</a></div>
 * <div class="courseinfo">
 * <div class="popup attrword"><span class="crseattrword">[3Y10]</span><div class="popupdetail">CC for 3Y 2010 &amp; 2011 cohorts</div></div><div class="popup attrword"><span class="crseattrword">[3Y12]</span><div class="popupdetail">CC for 3Y 2012 cohort</div></div><div class="popup attrword"><span class="crseattrword">[4Y]</span><div class="popupdetail">CC for 4Y 2012 and after</div></div><div class="popup attrword"><span class="crseattrword">[DELI]</span><div class="popupdetail">Mode of Delivery</div></div>	
 *    <div class="courseattr popup">
 * 	    <span style="font-size: 12px; color: #688; font-weight: bold;">COURSE INFO</span>
 * 	    <div class="popupdetail">
 * 	    <table width="400">
 *         <tbody>
 *             <tr><th>ATTRIBUTES</th><td>Common Core (S&amp;T) for 2010 &amp; 2011 3Y programs<br>Common Core (S&amp;T) for 2012 3Y programs<br>Common Core (S&amp;T) for 4Y programs<br>[BLD] Blended learning</td></tr><tr><th>EXCLUSION</th><td>ISOM 2010, any COMP courses of 2000-level or above</td></tr><tr><th>DESCRIPTION</th><td>This course is an introduction to computers and computing tools. It introduces the organization and basic working mechanism of a computer system, including the development of the trend of modern computer system. It covers the fundamentals of computer hardware design and software application development. The course emphasizes the application of the state-of-the-art software tools to solve problems and present solutions via a range of skills related to multimedia and internet computing tools such as internet, e-mail, WWW, webpage design, computer animation, spread sheet charts/figures, presentations with graphics and animations, etc. The course also covers business, accessibility, and relevant security issues in the use of computers and Internet.</td>
 *             </tr>	
 *          </tbody>
 *      </table>
 * 	    </div>
 *    </div>
 * </div>
 *  <h2>COMP 1001 - Exploring Multimedia and Internet Computing (3 units)</h2>
 *  <table class="sections" width="1012">
 *   <tbody>
 *    <tr>
 *        <th width="85">Section</th><th width="190" style="text-align: left">Date &amp; Time</th><th width="160" style="text-align: left">Room</th><th width="190" style="text-align: left">Instructor</th><th width="45">Quota</th><th width="45">Enrol</th><th width="45">Avail</th><th width="45">Wait</th><th width="81">Remarks</th>
 *    </tr>
 *    <tr class="newsect secteven">
 *        <td align="center">L1 (1765)</td>
 *        <td>We 02:00PM - 03:50PM</td><td>Rm 5620, Lift 31-32 (70)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td></tr><tr class="newsect sectodd">
 *        <td align="center">LA1 (1766)</td>
 *        <td>Tu 09:00AM - 10:50AM</td><td>Rm 4210, Lift 19 (67)</td><td><a href="/wcq/cgi-bin/1830/instructor/LEUNG, Wai Ting">LEUNG, Wai Ting</a></td><td align="center">67</td><td align="center">0</td><td align="center">67</td><td align="center">0</td><td align="center">&nbsp;</td>
 *    </tr>
 *   </tbody>
 *  </table>
 * </div>
 *}
 *</pre>
 * <br>
 * The code 
 * <pre>
 * {@code
 * List<?> items = (List<?>) page.getByXPath("//div[@class='course']");
 * }
 * </pre>
 * extracts all result-row and stores the corresponding HTML elements to a list called items. Later in the loop it extracts the anchor tag 
 * &lsaquo; a &rsaquo; to retrieve the display text (by .asText()) and the link (by .getHrefAttribute()).   
 * 
 *
 */
public class Scraper {
	/**
     * the web client
     */
	private WebClient client;

	/**
	 * Default Constructor 
	 */
	public Scraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}
	 /**
     * add a slot to a section
     * 
     * 
     * @author Ziyue
     * @param e - html element
     * @param sec - section to be added
     * @param secondRow - if the section has second row
     *
     */
	
	
	private void addSlot(HtmlElement e, Section sec, boolean secondRow) {
		String times[] =  e.getChildNodes().get(secondRow ? 0 : 3).asText().split(" ");
		String venue = e.getChildNodes().get(secondRow ? 1 : 4).asText();
		if (times[0].equals("TBA"))
			return;
		for (int j = 0; j < times[0].length(); j+=2) {
			String code = times[0].substring(j , j + 2);
			if (Slot.DAYS_MAP.get(code) == null)
				break;
			Slot s = new Slot();
			s.setDay(Slot.DAYS_MAP.get(code));
			s.setStart(times[1]);
			s.setEnd(times[3]);
			s.setVenue(venue);
			sec.addSlot(s);	
		}

	}

	
//	private boolean checkCourseCodeValid(String s) {
//		if (s.length() != 8 && s.length() != 9) return false;
//		for (Character c: s.substring(0, 4)) {
//			
//		}
//		return true;
//	}
	/**
	 * Check if an input section code 's' is valid or not
	 * @author Jeff
	 * @param s input section code
	 * @return valid or not
	 */
	private boolean checkSectionCodeValid(String s) {
		Character[] valid1 = {'A','0','1','2','3','4','5','6','7','8','9'};
		Character[] valid2 = {'0','1','2','3','4','5','6','7','8','9'};
		if (s.isEmpty()) return false;
		if (s.charAt(0) != 'T' && s.charAt(0) != 'L') return false;
//		if (s.charAt(0) == 'L' && (!Arrays.asList(valid1).contains(s.charAt(1)))) return false;
//		if (s.charAt(0) == 'L') {
//			int i = (s.charAt(1) == 'A') ? 2 : 1;
//			for (; i < s.length() - 1; i++) {
//				if (!Arrays.asList(valid2).contains(s.charAt(i))) return false;
//			}
//		}
		if (s.charAt(0) == 'L') return true;
		return true;
	}
	/**
	 * Check if an input url 'url' is valid or not
	 * @author Jeff
	 * @param url input url
	 * @return valid or not
	 */
	private static boolean isValidUrl(String url) { 
        /* Try creating a valid URL */
        try { 
            new URL(url).toURI(); 
            return true; 
        } catch (Exception e) { 
            return false; 
        }
    }
	/**
	 * Check if an input term 'term' is valid or not
	 * @author Jeff
	 * @param term input term
	 * @return valid or not
	 */
	private static boolean isValidTerm(String term) {
		Character[] thirdPosition = {'1', '2', '3', '4'};
		if (term.length() != 4) return false;
		if (!Arrays.asList(thirdPosition).contains(term.charAt(2))) return false;
		if (term.charAt(3) != '0') return false;
		return true;
	}
	/**
	 * Check if an input subject 'subject' is valid or not
	 * @author Jeff
	 * @param subject input subject
	 * @return valid or not
	 */
	private static boolean isValidSubject(String subject) {
		if (subject.length() != 4) return false;
		if (!subject.matches("[A-Z]*")) return false;
		return true;
	}
	/**
	 * Check if accessing an input url 'url' will receive 404
	 * @author Jeff
	 * @param url input url
	 * @return 404 no or yes
	 */
	private static boolean isPageFound(String url) {
		try {
			URL urlStr = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();
			int state = connection.getResponseCode();
			if (state == 404){
				return false;
			}
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	/**
	 * Check if a string is valid for Tutorial or Lab.
	 * @author Jeff
	 * @param s input string
	 * @return valid or not
	 */
	private boolean hasTorLA(String s) {
		if (s.charAt(0) == 'T') return true;
		if (s.charAt(0) == 'L' && s.charAt(1) == 'A') return true;
		return false;
	}
	/**
	 * scrape all the subjects
	 * @author zxiaac
	 * @param baseurl input base url
	 * @param term input term
	 * @return a vector of all subjects like ACCT in type string
	 * @throws PageNotFoundError the error is thrown when accessing baseurl/term/ returns 404
	 * @throws UrlNotValidError the error is thrown when the baseurl is invalid
	 * @throws TermNotValidError the error is thrown when the term is invalid
	 * @throws UnknownHostException the error is thrown when the baseurl is an unknown host
	 */
	public Vector<String> scrapeSubject(String baseurl, String term) throws PageNotFoundError, UrlNotValidError, TermNotValidError, UnknownHostException {
		try {
			//System.out.println("enter");
			if (!isValidUrl(baseurl)) throw new UrlNotValidError(baseurl);
			if (!isValidTerm(term)) throw new TermNotValidError(term);
			if (!isPageFound(baseurl + "/" + term + "/")) throw new PageNotFoundError(baseurl + "/" + term + "/");
			HtmlPage page = client.getPage(baseurl + "/" + term+"/");
			//HtmlPage page = client.getPage(baseurl);
			//System.out.println(page.asText());
			List<?> items = (List<?>) page.getByXPath("//div[@class='depts']");
			Vector<String> subjects = new Vector<String>();
			HtmlElement htmlItem = (HtmlElement) items.get(0);
			List<?> titles = htmlItem.getByXPath(".//a");
			for(int i=0;i<titles.size();i++) {
				//System.out.println(titles.get(i).toString().substring(57, 61));
				subjects.add(titles.get(i).toString().substring(57, 61));
			}
			return subjects;
		}catch (Exception e) {
			if (e instanceof UrlNotValidError) {
				throw new UrlNotValidError(e.getMessage());
			} else if (e instanceof TermNotValidError) {
				throw new TermNotValidError(e.getMessage());
			} else if (e instanceof UnknownHostException) {
				throw new UnknownHostException(e.getMessage());
			} else if (e instanceof PageNotFoundError) {
				throw new PageNotFoundError(e.getMessage());
//			String msg = e.getMessage();
//			if (msg.contains("404")) {
//				throw new PageNotFoundError("404");
			} else {
//				System.out.println(e.);
//				System.out.println(e);
//			     StackTraceElement[] arr = e.getStackTrace();
//			     for(int i=0; i<arr.length; i++){
//			       System.out.println(arr[i].toString());
//			     }
			}
		}
		return null;

	}
	/**
	 * core function for scraping the web
	 * @author Jeff
	 * @param baseurl input base url
	 * @param term input term
	 * @param sub input subject
	 * @return courses and instructors
	 * @throws PageNotFoundError the error is thrown when accessing baseurl/term/subject returns 404
	 * @throws UrlNotValidError the error is thrown when the baseurl is invalid
	 * @throws TermNotValidError the error is thrown when the term is invalid
	 * @throws SubjectNotValidError the error is thrown when the subject is invalid
	 * @throws UnknownHostException the error is thrown when the baseurl is an unknown host
	 */
	public Vector<AbstractCollection> scrape(String baseurl, String term, String sub) throws PageNotFoundError, UrlNotValidError, TermNotValidError, SubjectNotValidError, UnknownHostException {
		try {
			if (!isValidUrl(baseurl)) throw new UrlNotValidError(baseurl);
			if (!isValidTerm(term)) throw new TermNotValidError(term);
			if (!isValidSubject(sub)) throw new SubjectNotValidError(sub);
			if (!isPageFound(baseurl + "/" + term + "/subject/" + sub)) throw new PageNotFoundError(baseurl + "/" + term + "/subject/" + sub);

			HtmlPage page = client.getPage(baseurl + "/" + term + "/subject/" + sub);

			
			List<?> items = (List<?>) page.getByXPath("//div[@class='course']");
			
			Vector<Course> course = new Vector<Course>();
			HashSet<Instructor> ins = new HashSet<Instructor>();
			Vector<AbstractCollection> result = new Vector<AbstractCollection>();
			for (int i = 0; i < items.size(); i++) {
				Course c = new Course();
				HtmlElement htmlItem = (HtmlElement) items.get(i);
				
				HtmlElement title = (HtmlElement) htmlItem.getFirstByXPath(".//h2");
//				if (!checkCourseCodeValid(title.asText())) continue;
				c.setTitle(title.asText());
				List<?> popupdetailslist = (List<?>) htmlItem.getByXPath(".//div[@class='popupdetail']/table/tbody/tr");
				HtmlElement exclusion = null;
				for ( HtmlElement e : (List<HtmlElement>)popupdetailslist) {
					HtmlElement t = (HtmlElement) e.getFirstByXPath(".//th");
					HtmlElement d = (HtmlElement) e.getFirstByXPath(".//td");
					if (t.asText().equals("EXCLUSION")) {
						exclusion = d;
					}
				}
				c.setExclusion((exclusion == null ? "null" : exclusion.asText()));
				List<?> crseattrwordlist = (List<?>) htmlItem.getByXPath(".//span[@class='crseattrword']");
				for (HtmlElement e: (List<HtmlElement>)crseattrwordlist) {
					if (e.asText().equals("[4Y]")) c.setcc4y(true);
				}
//				if (c.getcc4y()) System.out.println(c.getTitle());
				List<?> sections = (List<?>) htmlItem.getByXPath(".//tr[contains(@class,'newsect')]");
				for ( HtmlElement e: (List<HtmlElement>)sections) {
					// create a new section and set basic information
					Section sec = new Section();
					String [] section_info = e.getChildNodes().get(1).asText().split(" ");
					if (!checkSectionCodeValid(section_info[0])) {
//						System.out.println("INVALID SECTION CODE FOUND: " + section_info[0] + " of course " + c.getTitle());
						continue;
					}
					if (c.gettla() == false && hasTorLA(section_info[0])) {
						//System.out.println(c.getTitle() + " " + sec.getcode() + " " + section_info[0]);
						c.settla(true);
					}
					sec.setCourseCode(title.asText().split(" - ")[0]);
					sec.setCourseName(title.asText().split(" - ")[1].split(" \\(")[0]);
					sec.setcode(section_info[0]);
					sec.setid(section_info[1].substring(1, 5));
					String insturctor_s = e.getChildNodes().get(5).asText();
					if (insturctor_s.indexOf('\n') != -1) {
						String [] instructors = insturctor_s.split("\n");
						for (String s: instructors) {
							boolean notexist = true; // should be unnecessary???
							for (Instructor inst_hash: ins) {
								if (inst_hash.getName() == s) notexist = false;
							}
							Instructor inst = new Instructor(s);
							if (s != "TBA" && notexist) ins.add(inst);
							sec.addInstructor(inst);
						}
					} else {
						boolean notexist = true; // should be unnecessary???
						for (Instructor inst_hash: ins) {
							if (inst_hash.getName() == insturctor_s) notexist = false;
						}
						Instructor inst = new Instructor(insturctor_s);
						if (insturctor_s != "TBA" && notexist) ins.add(inst);
						sec.addInstructor(inst);
					}
					// creation of the new section finishes here
					addSlot(e, sec, false);
					e = (HtmlElement)e.getNextSibling();
					if (e != null && !e.getAttribute("class").contains("newsect"))
						addSlot(e, sec, true);
					c.addSection(sec);
				}
				if (c.getNumSections() > 0) course.add(c);
			}
			client.close();
			result.add(course);
			result.add(ins);
			return result;
		} catch (Exception e) {
			if (e instanceof UrlNotValidError) {
				throw new UrlNotValidError(e.getMessage());
			} else if (e instanceof TermNotValidError) {
				throw new TermNotValidError(e.getMessage());
			} else if (e instanceof SubjectNotValidError) {
				throw new SubjectNotValidError(e.getMessage());
			} else if (e instanceof UnknownHostException) {
				throw new UnknownHostException(e.getMessage());
			} else if (e instanceof PageNotFoundError) {
				throw new PageNotFoundError(e.getMessage());
//			String msg = e.getMessage();
//			if (msg.contains("404")) {
//				throw new PageNotFoundError("404");
			} else {
//				System.out.println(e.);
//				System.out.println(e);
//			     StackTraceElement[] arr = e.getStackTrace();
//			     for(int i=0; i<arr.length; i++){
//			       System.out.println(arr[i].toString());
//			     }
			}
		}
		return null;
	}

}
