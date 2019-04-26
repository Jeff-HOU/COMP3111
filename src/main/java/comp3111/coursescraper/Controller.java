package comp3111.coursescraper;
import java.util.HashSet;
import java.net.URLEncoder;
import java.net.URL;
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
import java.util.HashSet;
import java.net.URLEncoder;
import java.net.URL;
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
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.Node;

import java.util.Random;
import java.util.Vector;
import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
public class Controller {

    @FXML
    private Tab tabMain;

    @FXML
    private TextField textfieldTerm;

    @FXML
    private TextField textfieldSubject;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textfieldURL;

    @FXML
    private Tab tabStatistic;

    @FXML
    private ComboBox<?> comboboxTimeSlot;

    @FXML
    private Tab tabFilter;

    @FXML
    private Tab tabList;

    @FXML
    private Tab tabTimetable;

    @FXML
    private Tab tabAllSubject;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private TextField textfieldSfqUrl;

    @FXML
    private Button buttonSfqEnrollCourse;

    @FXML
    private Button buttonInstructorSfq;

    @FXML
    private TextArea textAreaConsole;
    
    private Scraper scraper = new Scraper();
    
    private Vector<Course> course = new Vector<Course>();
    private HashMap<String, Boolean> filterCheckBox = new HashMap<String, Boolean>();
    
    private static String[] filterCheckBoxName = {
    "#filterMON", "#filterTUE", "#filterWED", "#filterTHU", "#filterFRI", "#filterSAT",
    "#filterAM", "#filterPM",
    "#filterCCC", "#filterNOEx", "#filterTLA"
    };
    private Vector<Section> selectedSection = new Vector<Section>();
    
    @FXML
    void selectCourse() {
//    	if (filterCheckBox.isEmpty()) {
//    		for (String s: filterCheckBoxName) {
//        		filterCheckBox.put(s, false);
//        	}
//    	}
    	AnchorPane ap = (AnchorPane)tabFilter.getContent();
    	for (String s: filterCheckBoxName) {
    		filterCheckBox.put(s, ((CheckBox) ap.lookup(s)).isSelected());
    	}
    	Vector<Section> AMPM = new Vector<Section>();
    	if (filterCheckBox.get("#filterAM") && filterCheckBox.get("#filterPM")) {
    		for (Course c: course) {
    			AMPM.addAll(c.getSectionsThatHaveAMandPMSlots());
    		}
    	} else if (filterCheckBox.get("#filterAM") && !(filterCheckBox.get("#filterPM"))) {
    		for (Course c: course) {
    			AMPM.addAll(c.getSectionsThatHaveAMSlots());
    		}
    	} else if (!(filterCheckBox.get("#filterAM")) && filterCheckBox.get("#filterPM")) {
    		for (Course c: course) {
    			AMPM.addAll(c.getSectionsThatHavePMSlots());
    		}
    	} else {
    		for (Course c: course) {
    			for (int i = 0; i < c.getNumSections(); i++) {
    				AMPM.add(c.getSection(i));
    			}
    		}
    	}
    	Vector<Section> selectAMPM = new Vector<Section>();

    	for(Course c:course)
    	{ 
    		for(Section s:AMPM) {

	    		if(c.hasSection(s))
	    		{
	    			selectAMPM.addAll(c.getAllSections());
	    			break;
	    		}
    		}
    	
    	}


    	Vector<Section> Day = new Vector<Section>();
    	for (Course c: course) {
//    		System.out.println(c.getTitle());
    		Vector<Section> selectDayForCourse = new Vector<Section>();
    		int count=0;
    		for (int i = 0; i < Slot.DAYS.length; i++) {
    			if (filterCheckBox.get(filterCheckBoxName[i])) {
    				count++;
    				System.out.println(filterCheckBoxName[i] + " " + selectDayForCourse.isEmpty());
//    				if (c.getSectionsThatHaveSlotOnDay(i).isEmpty()) {
//    					selectDayForCourse.clear();
//    					break;
//    				}
    				if (count==1) selectDayForCourse.addAll(c.getSectionsThatHaveSlotOnDay(i));
//    				if (c.getSectionsThatHaveSlotOnDay(i).isEmpty())
//    				{
//    					selectDayForCourse.clear();
//    					break;
//    				}
    				else selectDayForCourse.retainAll(c.getSectionsThatHaveSlotOnDay(i));
    			}
    		}
    		Day.addAll(selectDayForCourse);
    	}
    	Vector<Section> selectDay = new Vector<Section>();
    	for(Course c:course)
    	{
    		for(Section s:Day)
    		{
    			if(c.hasSection(s))
    			{
    				selectDay.addAll(c.getAllSections());
    				break;
    			}
    		}
    	}
//    	Vector<Section> selectDay = new Vector<Section>();
//    	Vector<Section> selectDayForCourses = new Vector<Section>();
//    	for (Course c:course)
//    	{
//    		for (int i = 0; i < c.getNumSections(); i++) {
//				selectDayForCourses.add(c.getSection(i));
//			}
//    		
//    	}
//    	System.out.println("hhssh2--\\|/" + selectDayForCourses.size());
//    	for (Course c: course) {
////    		System.out.println(c.getTitle());
//    		
//    		for (int i = 0; i < Slot.DAYS.length; i++) {
//    			if (filterCheckBox.get(filterCheckBoxName[i])) {
////    				System.out.println(filterCheckBoxName[i] + " " + selectDayForCourse.isEmpty());
//    				
//     				selectDayForCourses.retainAll(c.getSectionsThatHaveSlotOnDay(i));
//     				System.out.println("hhssh2--\\|/" +i+ "a"+selectDay.size());
//    			}
//    			else System.out.println("ass"+i);
//    		}
//    		selectDay.addAll(selectDayForCourses);
//    	}
//    	
//    	System.out.println("hhssh2--\\|/" + selectDay.size());
//    	for (Section sec: selectDay) {
//    		System.out.println(sec.getid());
//    	}
//    	System.out.println("hhssh2--/|\\" + selectDay.size());
    	Vector<Section> selectCCC = new Vector<Section>();
    	if (filterCheckBox.get("#filterCCC")) {
    		for (Course c: course) {
        		if (c.getcc4y()) {
        			for (int i = 0; i < c.getNumSections(); i++) {
        				selectCCC.add(c.getSection(i));
        			}
        		}
        	}
    	}
    	Vector<Section> selectNOEx = new Vector<Section>();
    	if (filterCheckBox.get("#filterNOEx")) {
    		for (Course c: course) {
        		if (c.getnoexclusion()) {
        			for (int i = 0; i < c.getNumSections(); i++) {
        				selectNOEx.add(c.getSection(i));
        			}
        		}
        	}
    	}
    	Vector<Section> selectTLA = new Vector<Section>();
    	if (filterCheckBox.get("#filterTLA")) {
    		for (Course c: course) {
        		if (c.gettla()) {
        			for (int i = 0; i < c.getNumSections(); i++) {
        				selectTLA.add(c.getSection(i));
        			}
        		}
        	}
    	}
    	selectedSection.clear();
    	selectedSection.addAll((Vector<Section>)selectAMPM.clone());// selectAMPM.clear();
    	boolean tmp_selectday = filterCheckBox.get("#filterMON")||filterCheckBox.get("#filterTUE") ||filterCheckBox.get("#filterWED") ||filterCheckBox.get("#filterTHU") ||filterCheckBox.get("#filterFRI")||filterCheckBox.get("#filterSAT");
    	if (tmp_selectday)  selectedSection.retainAll((Vector<Section>)selectDay.clone());// selectDay.clear();
    	if (filterCheckBox.get("#filterCCC"))  selectedSection.retainAll((Vector<Section>)selectCCC.clone());// selectCCC.clear();
    	if (filterCheckBox.get("#filterNOEx")) selectedSection.retainAll((Vector<Section>)selectNOEx.clone());// selectNOEx.clear();
    	if (filterCheckBox.get("#filterTLA"))  selectedSection.retainAll((Vector<Section>)selectTLA.clone());// selectTLA.clear();
    	System.out.println("filtered course size and their id list: " + selectedSection.size());
    	for (Section s: selectedSection) {
    		System.out.println(s.getid());
    	}
//    	System.out.println("-------");
//    	for (Section s: selectTLA) {
//    		System.out.println(s.getid());
//    	}
    	textAreaConsole.setText("Filtered Courses:\n");
    	for (Section sec: selectedSection) {
    		textAreaConsole.appendText(sec.getCourseCode() + "\t" + sec.getcode()); // is there any extra information needed to display???
    		textAreaConsole.appendText("\n");
    		for (int i = 0; i < sec.getNumSlots(); i++) {
    			textAreaConsole.appendText("\t" + sec.getSlot(i) + "\n");
    		}
    	}
    }
    
    
    
    
    
    
    
    @FXML
    
    void selectAll() {
    	AnchorPane ap = (AnchorPane)tabFilter.getContent();
    	ObservableList<Node> nodes = ap.getChildren();
    	Button b = (Button) ap.lookup("#filterSelectAll");
    	if (b.getText().equals("Select All")) {
    		b.setText("De-select All");
    		for (Node node: nodes) {
    			if (node instanceof CheckBox) ((CheckBox)node).setSelected(true);
    		}
    	} else {
    		b.setText("Select All");
    		for (Node node: nodes) {
    			if (node instanceof CheckBox) ((CheckBox)node).setSelected(false);
    		}
    	}
    	selectCourse();
    }

    
    @FXML
    void allSubjectSearch() {buttonSfqEnrollCourse.setDisable(false);
    	
    }

    @FXML
    void findInstructorSfq() {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		String searchUrl = textfieldSfqUrl.getText();
		try {
			HtmlPage page = client.getPage(searchUrl);
			List<?> items=(List<?>) page.getByXPath("//table");
			Vector<Instructor> instructor=new Vector<Instructor>();
			for(int i=2;i<items.size()-1;i++)
			{
				HtmlElement htmlItem= (HtmlElement) items.get(i);
//				List<?> trs=(List<?>) htmlItem.getByXPath(".//tr[td[3][string-length(text())>0]]");
				List<?> trs=(List<?>) htmlItem.getByXPath(".//tr[td[contains(.,',')]]");
				for(int j=0;j<trs.size();j=j+1)
				{
					HtmlElement element = (HtmlElement) trs.get(j);
					List<?> tds=(List<?>) element.getByXPath(".//td");
					
					HtmlElement name=(HtmlElement) tds.get(2);
					String insName=name.asText();
					
//					System.out.println(insName);
					
					HtmlElement score = (HtmlElement) tds.get(4);					
					String TxtScore=score.asText();
					String insScore=TxtScore.substring(0, 4);
					if (insScore.charAt(0)!='-')
					{	
						Instructor ins=new Instructor(insName);
						ins.setSfq(Double.valueOf(insScore));
						instructor.add(ins);					
					}		
				}				
				
			}
			Vector<Instructor> instructor2=new Vector<Instructor>();
			for(Instructor i: instructor) {
				int flag=0;
				for(Instructor j:instructor2) 
				{										
					if (i.getName().equals(j.getName()))
					{
						double averageSfq=(j.getSfq()*j.getNumSection()+i.getSfq())/(j.numSection+1);
						j.numSection++;
						j.setSfq(averageSfq);
						flag=1;
						break;
					}		
					
				}
				if(flag==0)
				{
					instructor2.add(i);
				}
			}
			for(Instructor k: instructor2)
			{			
				textAreaConsole.appendText("Instructor Name: "  + k.getName()+ "\t SFQ: " + k.getSfq()+"\n");				
			}
			
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

    	
    	
    	
    }

    @FXML
    void findSfqEnrollCourse() {
    	WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		String searchUrl = textfieldSfqUrl.getText();
		try {
			HtmlPage page = client.getPage(searchUrl);
			List<?> items=(List<?>) page.getByXPath("//table");
			Vector<Section> sectionlist=new Vector<Section>();
			for(int i=2;i<items.size()-1;i++)
			{
				HtmlElement htmlItem= (HtmlElement) items.get(i);
//				List<?> trs=(List<?>) htmlItem.getByXPath(".//tr[td[2][string-length(text())>0]]");	
				List<?> trs=(List<?>) htmlItem.getByXPath(".//*[count(td)=6]");				
				
				for(int j=0;j<trs.size();j=j+1)
				{
					HtmlElement element = (HtmlElement) trs.get(j);
					List<?> tds=(List<?>) element.getByXPath(".//td");
					HtmlElement course=(HtmlElement) tds.get(0);
					String courseCode=course.asText().replace(" ","");				
					HtmlElement numSection=(HtmlElement) tds.get(7);
					String numOfSect=numSection.asText();
					int numOfSection=Integer.valueOf(numOfSection);
					int k=0;
					while(k<numOfSection)
					{
							element=(HtmlElement) element.getByXPath(".//following-sibling::tr[1]");
							List<?> tds2=(List<?>) element.getByXPath(".//td");
							HtmlElement Section=(HtmlElement) tds2.get(1);
							String SectionCode=Section.asText();
							if(SectionCode!=null&&(!SectionCode.isEmpty()))
							{
								HtmlElement score = (HtmlElement) tds.get(3);					
								String TxtScore=score.asText();
								String insScore=TxtScore.substring(0, 4);
								k=k+1;
								if (insScore.charAt(0)!='-')
								{
									Section asection=new Section();
									asection.setCourseCode(courseCode);
									asection.setcode(SectionCode);															
									asection.setSecSfq(Double.valueOf(insScore));
									sectionlist.add(asection);
								}
							}
																
					}
					
						
				}				
				
			}
			Vector<Course> courses=new Vector<Course>();
			for(Section section: sectionlist) {
				int flag=0;
				for(Course c:courses) 
				{			
					if (section.getCourseCode().equals(c.getCourseCode()))
					{
						double averageSfq=(c.getSfq()*c.getNumSections()+section.getSecSfq())/(c.getNumSections()+1);
						c.addSection(section);
						
						c.setSfq(averageSfq);
						flag=1;
						break;
					}
				}	
				if(flag==0)
				{
					Course newcourse=new Course();
					newcourse.addSection(section);
					newcourse.setCourseCode(section.getCourseCode());
					newcourse.setSfq(section.getSecSfq());
					courses.add(newcourse);
				}
			}
		
			
		
			for(Course k: courses)
			{	
				for(Section s: enrolled)
				{
					if(k.getCourseCode().equals(s.getCourseCode()))
					{
						textAreaConsole.appendText("Course Code: "  + k.getCourseCode()+ "\t SFQ: " + k.getSfq()+"\n");				
					}
				}
			}
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

    }

    @FXML
    void search() {
    	buttonSfqEnrollCourse.setDisable(false);
    	try {
    		textAreaConsole.setText("");
    		Vector<AbstractCollection> vec = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    		course.clear();
    		course.addAll((Vector<Course>) vec.get(0));    		HashSet<Instructor> ins = (HashSet<Instructor>) vec.get(1);
    		int allNumSections = 0;
    		LocalTime TU310 = LocalTime.parse("03:10PM", DateTimeFormatter.ofPattern("hh:mma", Locale.US));
    		HashSet<Instructor> ins_tu310 = (HashSet<Instructor>)ins.clone(); //???
    		for (Course c : course) {
    			allNumSections += c.getNumSections();
        		String newline = c.getTitle() + "\n";
        		for (int i = 0; i < c.getNumSections(); i++) {
        			Section sec = c.getSection(i);
//        			newline += "Slot " + i + ": " + t + "\n";
        			newline += sec;
        			for (int j = 0; j < sec.getNumSlots(); j++) {
        				Slot s = sec.getSlot(j);
        				if (s.getDay() == 1 && s.getStart().compareTo(TU310) <= 0 && s.getEnd().compareTo(TU310) >= 0) {
        					for (Instructor inst: sec.getInstructors())
        						ins_tu310.remove(inst);
        				}
        			}
        		}
        		textAreaConsole.setText(textAreaConsole.getText() + "\n" + newline); // remove this and change the appendText below to setText???
        	}
        	
    		String searchInfo = "";
    		searchInfo += "Total Number of difference sections in this search: " + allNumSections + "\n";
    		searchInfo += "Total Number of Course in this search: " + course.size() + "\n";
    		searchInfo += "Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm:\n";
    		for (Instructor inst: ins_tu310) {
    			searchInfo += inst + "\n"; // 说好的不会重复呢？？？
    		}
    		textAreaConsole.appendText(searchInfo);
    		
        	//Add a random block on Saturday
        	AnchorPane ap = (AnchorPane)tabTimetable.getContent();
        	Label randomLabel = new Label("COMP1022\nL1");
        	Random r = new Random();
        	double start = (r.nextInt(10) + 1) * 20 + 40;

        	randomLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        	randomLabel.setLayoutX(600.0);
        	randomLabel.setLayoutY(start);
        	randomLabel.setMinWidth(100.0);
        	randomLabel.setMaxWidth(100.0);
        	randomLabel.setMinHeight(60);
        	randomLabel.setMaxHeight(60);
        
        	ap.getChildren().addAll(randomLabel);
    	} catch (Exception e) {
    		if (e instanceof PageNotFoundError) {
    			AnchorPane ap = (AnchorPane)tabStatistic.getContent();
    			Label msg = new Label("404 NOT FOUND");
    			ap.getChildren().add(msg);
    			
    		}else if (e instanceof UrlNotValidError) {
    			textAreaConsole.setText("URL you entered: \n\t" + e.getMessage() + "\nis invalid");
    		} else if (e instanceof TermNotValidError) {
    			textAreaConsole.setText("Term you entered: \n\t" + e.getMessage() + "\nis invalid");
    		} else if (e instanceof SubjectNotValidError) {
    			textAreaConsole.setText("Subject you entered: \n\t" + e.getMessage() + "\nis invalid");
    		}

    	}
    	
    	
    	
    }

}
