package comp3111.coursescraper;


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
    		Vector<Section> selectDayForCourse = new Vector<Section>();
    		for (int i = 0; i < Slot.DAYS.length; i++) {
    			if (filterCheckBox.get(filterCheckBoxName[i])) {
//    				System.out.println(filterCheckBoxName[i] + " " + selectDayForCourse.isEmpty());
    				if (c.getSectionsThatHaveSlotOnDay(i).isEmpty()) {
    					selectDayForCourse.clear();
    					break;
    				}
    				if (selectDayForCourse.isEmpty()) selectDayForCourse.addAll(c.getSectionsThatHaveSlotOnDay(i));
    				else selectDayForCourse.retainAll(c.getSectionsThatHaveSlotOnDay(i));
    			}
    		}
    		selectDay.addAll(selectDayForCourse);
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
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void allSubjectSearch() {
    	
    }

    @FXML
    void findInstructorSfq() {
    	buttonInstructorSfq.setDisable(true);
    }

    @FXML
    void findSfqEnrollCourse() {

    }

    @FXML
    void search() {
    	try {
    		Vector<AbstractCollection> vec = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),textfieldSubject.getText());
    		Vector<Course> v = (Vector<Course>) vec.get(0);
    		HashSet<Instructor> ins = (HashSet<Instructor>) vec.get(1);
    		int allNumSections = 0;
    		LocalTime TU310 = LocalTime.parse("03:10PM", DateTimeFormatter.ofPattern("hh:mma", Locale.US));
    		HashSet<Instructor> ins_tu310 = (HashSet<Instructor>)ins.clone(); //???
    		for (Course c : v) {
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
    		searchInfo += "Total Number of Course in this search: " + v.size() + "\n";
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
    	} catch (PageNotFoundError e) {
    		AnchorPane ap = (AnchorPane)tabStatistic.getContent();
    		Label msg = new Label("404 NOT FOUND");
    		ap.getChildren().add(msg);
    	}
    	
    }

}
