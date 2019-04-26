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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Random;
import java.util.Vector;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.net.UnknownHostException;
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
    private Vector<SectionsToList> enrolledSection = new Vector<SectionsToList>();
    
    @FXML
    void filterSelectAll() {
    	AnchorPane ap = (AnchorPane)tabFilter.getContent();
    	Button selectAllBtn = (Button)ap.lookup("#selectAll");
    	if (selectAllBtn.getText().equals("Select All")) {
    		selectAllBtn.setText("Unselect All");
    		for (String filterName: filterCheckBoxName) {
    			CheckBox filterCheckbox = (CheckBox) ap.lookup(filterName);
    			filterCheckbox.setSelected(true);
    		}
    	} else {
    		selectAllBtn.setText("Select All");
    		for (String filterName: filterCheckBoxName) {
    			CheckBox filterCheckbox = (CheckBox) ap.lookup(filterName);
    			filterCheckbox.setSelected(false);
    		}
    	}
    	selectCourse();
    }
    
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
    	Vector<Section> selectAMPM = new Vector<Section>();
    	if (filterCheckBox.get("#filterAM") && filterCheckBox.get("#filterPM")) {
    		for (Course c: course) {
    			selectAMPM.addAll(c.getSectionsThatHaveAMandPMSlots());
    		}
    	} else if (filterCheckBox.get("#filterAM") && !(filterCheckBox.get("#filterPM"))) {
    		for (Course c: course) {
    			selectAMPM.addAll(c.getSectionsThatHaveAMSlots());
    		}
    	} else if (!(filterCheckBox.get("#filterAM")) && filterCheckBox.get("#filterPM")) {
    		for (Course c: course) {
    			selectAMPM.addAll(c.getSectionsThatHavePMSlots());
    		}
    	} else {
    		for (Course c: course) {
    			for (int i = 0; i < c.getNumSections(); i++) {
    				selectAMPM.add(c.getSection(i));
    			}
    		}
    	}
//    	System.out.println("hhssh1" + selectAMPM.size());
    	Vector<Section> selectDay = new Vector<Section>();
    	for (Course c: course) {
//    		System.out.println(c.getTitle());
    		Vector<Vector<Section>> selectDayForCourse = new Vector<Vector<Section>>();
    		for (int i = 0; i < Slot.DAYS.length; i++) {
    			if (filterCheckBox.get(filterCheckBoxName[i])) {
    				System.out.println(filterCheckBoxName[i] + " " + selectDayForCourse.isEmpty());
    				if (c.getSectionsThatHaveSlotOnDay(i).isEmpty()) {
    					selectDayForCourse.clear();
    					break;
    				}
    				selectDayForCourse.add(c.getSectionsThatHaveSlotOnDay(i));
    			}
    		}
    		Vector<Section> selectDayForCourse_intersect;
    		if (!selectDayForCourse.isEmpty()) {
    			selectDayForCourse_intersect = selectDayForCourse.get(0);
    			for (Vector<Section> vec_sec: selectDayForCourse) {
    				selectDayForCourse_intersect.retainAll(vec_sec);
    			}
    			selectDay.addAll(selectDayForCourse_intersect);
    			for (Section sec: selectDayForCourse_intersect) {
        			System.out.println(sec);
        		}
    		}
    	}
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
    	boolean tmp_selectday = filterCheckBox.get("#filterMON") || filterCheckBox.get("#filterTUE") || filterCheckBox.get("#filterWED") || filterCheckBox.get("#filterTHU") || filterCheckBox.get("#filterFRI");
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
    	listCourse();
    }
    
    
    void listCourse() { // a very important source: https://stackoverflow.com/questions/48590054/javafx-tableview-how-to-add-a-listener-to-checkbox-column
    	AnchorPane ap = (AnchorPane)tabList.getContent();
    	TableView tv = (TableView)ap.lookup("#listTableView");
    	tv.setEditable(true);
    	ArrayList<SectionsToList> sectionsToList = new ArrayList<SectionsToList>();
    	sectionsToList.clear();
    	tv.setItems(FXCollections.observableArrayList(sectionsToList));
    	for (Section sec: selectedSection) {
//    		SectionsToList sectl_new = new SectionsToList(sec.getCourseCode(), sec.getcode(), sec.getCourseName(), sec.getInstructors(), false);
//    		for (SectionsToList sectl: sectionsToList) {
//    			if (!sectl.equals(sectl_new))
//    				sectionsToList.add(sectl_new);
//    		}
    		sectionsToList.add(new SectionsToList(sec.getCourseCode(), sec.getcode(), sec.getCourseName(), sec.getInstructors(), false));
    	}
    	System.out.print("sectionsToList.size() "+selectedSection.size() + " ");
    	for (SectionsToList sectl: enrolledSection) {
    		for (int i = 0; i < sectionsToList.size(); i++) {
    			if (sectionsToList.get(i).equals(sectl)) {
    				System.out.print(i + " ");
    				sectionsToList.remove(i);
    				break;
    			}
    		}
//    		int idx = sectionsToList.indexOf(sectl);
//    		if (idx != -1) sectionsToList.remove(idx);
    		sectionsToList.add(sectl.clone());
    	}
    	System.out.println(sectionsToList.size());
    	Collections.sort(sectionsToList, new SortSectionsToList());
    	tv.setItems(FXCollections.observableArrayList(sectionsToList));
    	tv.refresh();
    	
    	for (Object o: tv.getColumns()) {
    		TableColumn tc = (TableColumn) o;
    		tc.setEditable(true);
    		if (tc.getText().equals("Course Code")) {
    			tc.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
    		} else if (tc.getText().equals("Section")) {
    			tc.setCellValueFactory(new PropertyValueFactory<>("section"));
    		} else if (tc.getText().equals("Course Name")) {
    			tc.setCellValueFactory(new PropertyValueFactory<>("courseName"));
    		} else if (tc.getText().equals("Instructor")) {
    			tc.setCellValueFactory(new PropertyValueFactory<>("instructor"));
    		} else { //if (tc.getText().equals("Checked")) 
    			tc.setCellValueFactory(new PropertyValueFactory<>("checked"));
    			tc.setCellFactory(
    		            CheckBoxTableCell.forTableColumn(
    		                    new Callback<Integer, ObservableValue<Boolean>>() {
    		                        @Override
    		                        public ObservableValue<Boolean> call(Integer param) {
    		                        	SectionsToList sectl = sectionsToList.get(param); // what does the param represent??? if no value in the table???
//    		                        	int idx = enrolledSection.indexOf(sectl);
//    		                        	if (idx != -1) enrolledSection.remove(idx);
//    		                        	else enrolledSection.add(sectl);
    		                            return sectl.enrolledProperty();
    		                        }
    		                    }
    		                )
    		            );
    		}
    	}
    	for (SectionsToList sectl: sectionsToList) {
    		sectl.enrolledProperty().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    				if (newValue) enrolledSection.add(sectl);
    	    		else {
//    	    			int idx = enrolledSection.indexOf(sectl);
//    	    			if (idx != -1) enrolledSection.remove(idx);
    	    			for (int i = 0; i < enrolledSection.size(); i++) {
    	        			if (enrolledSection.get(i).equals(sectl)) {
    	        				System.out.print(i + " ");
    	        				enrolledSection.remove(i);
    	        				break;
    	        			}
    	        		}
    	    		}
    				System.out.println("enrolledSection:");
    		    	for (SectionsToList sectl: enrolledSection) {
    		    		System.out.println(sectl.getCourseCode() + " " + sectl.getSection());
    		    	}
        	    }
    		});
    	}
    }
    
    @FXML
    void allSubjectSearch() {
    	
    }

    @FXML
    void findInstructorSfq() {
//   	buttonInstructorSfq.setDisable(true);
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
				List<?> trs=(List<?>) htmlItem.getByXPath(".//*[count(td)=6 and not(@style)]");				

 				for(int j=0;j<trs.size();j=j+1)
				{
					HtmlElement element = (HtmlElement) trs.get(j);
					List<?> tds=(List<?>) element.getByXPath(".//td");
					HtmlElement course=(HtmlElement) tds.get(0);
					String courseCode=course.asText().replaceAll(" ","");	
//					System.out.println(courseCode);
					HtmlElement numSection=(HtmlElement) tds.get(5);
					String numOfSect=numSection.asText();
					int numOfSection=Integer.valueOf(numOfSect);
					int k=0;
//					System.out.println("numofSection"+numOfSect);
					while(k<numOfSection)
					{
						
							element=(HtmlElement) element.getFirstByXPath(".//following-sibling::tr[1]");
//							System.out.println("ssss~~~~4");
							List<?> tds2=(List<?>) element.getByXPath(".//td");

								HtmlElement Section=(HtmlElement) tds2.get(1);
								String SectionCode=Section.asText().replaceAll(" ", "");
//								System.out.println(SectionCode+"***");
								if(SectionCode!=null&&(!SectionCode.isEmpty()))
								{
									HtmlElement score = (HtmlElement) tds2.get(3);					
									String TxtScore=score.asText();
									String insScore=TxtScore.substring(0, 4);
									k=k+1;
									if (insScore.charAt(0)!='-')
									{
										Section asection=new Section();
										asection.setCourseCode(courseCode);
//										System.out.println(SectionCode);
										asection.setcode(SectionCode);															      
										asection.setSecSfq(Double.valueOf(insScore));
										sectionlist.add(asection);
									}
								}							
						
 					}
					
 				}				

 			}
//			System.out.println("ssss~~~~outsidefor");
			Vector<Course> courses=new Vector<Course>();
			for(Section section: sectionlist) {
//				System.out.println("～～insidefor");
				int flag=0;
				for(Course c:courses) 
				{		
//					System.out.println("～～inside2for");
					if (section.getCourseCode().equals(c.getCourseCode()))
					{
						double averageSfq=(c.getSfq()*c.getNumSections()+section.getSecSfq())/(c.getNumSections()+1);
						c.addSection(section);
 						c.setSfq(averageSfq);
						flag=1;
						break;
					}
				}	
//				System.out.println("～～finish2for");
				if(flag==0)
				{
					Course newcourse=new Course();
					newcourse.addSection(section);
					newcourse.setCourseCode(section.getCourseCode());
					newcourse.setSfq(section.getSecSfq());
					courses.add(newcourse);
				}
			}
//			System.out.println("～～finishthefirstfor");



 			for(Course k: courses)
			{	
				for(SectionsToList s: enrolledSection)
				{
//					System.out.println(k.getCourseCode().replaceAll(" ", "").equals(s.getCourseCode().replaceAll(" ", ""))+k.getCourseCode()+s.getCourseCode());
					if(k.getCourseCode().replaceAll(" ", "").equals(s.getCourseCode().replaceAll(" ", "")))
					{
						textAreaConsole.appendText("Course Code: "  + k.getCourseCode()+ "\t SFQ: " + k.getSfq()+"\n");				     					
//						textAreaConsole.appendText("111++~~~");
//						System.out.println("ssss~~~~");
					}
						
				}
					
			}
			


 		}catch(Exception e) {
			e.printStackTrace();
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
    void search() {
    	try {
    		textAreaConsole.setText("");
    		Vector<AbstractCollection> vec = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    		course.clear();
    		course.addAll((Vector<Course>) vec.get(0));
    		HashSet<Instructor> ins = (HashSet<Instructor>) vec.get(1);
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
        	AnchorPane ap_tabstat = (AnchorPane)tabStatistic.getContent();
        	ap_tabstat.getChildren().clear();
    	} catch (Exception e) {
    		if (e instanceof PageNotFoundError) {
    			AnchorPane ap = (AnchorPane)tabStatistic.getContent();
        		Label msg = new Label("404 NOT FOUND");
        		ap.getChildren().add(msg);
    		} else if (e instanceof UrlNotValidError) {
    			textAreaConsole.setText("URL you entered: \n\t" + e.getMessage() + "\nis invalid");
    		} else if (e instanceof TermNotValidError) {
    			textAreaConsole.setText("Term you entered: \n\t" + e.getMessage() + "\nis invalid");
    		} else if (e instanceof SubjectNotValidError) {
    			textAreaConsole.setText("Subject you entered: \n\t" + e.getMessage() + "\nis invalid");
    		} else if (e instanceof UnknownHostException) {
    			textAreaConsole.setText("Subject you entered: \n\t" + e.getMessage() + "\nis invalid");
    		}
    		
    	}
    	
    }

}
