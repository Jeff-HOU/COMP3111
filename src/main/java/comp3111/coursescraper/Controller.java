package comp3111.coursescraper;


import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Vector;
import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
public class Controller {
	private boolean first=true;
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
    
    
    @FXML
    void allSubjectSearch() {
    	try {
    		if(first) {
    			Vector<String> subjects = scraper.scrapeSubject(textfieldURL.getText(), textfieldTerm.getText());
    			textAreaConsole.setText(subjects.toString());
    			textAreaConsole.setText("Total Number of Categories/Code Prefix: "+subjects.size());
    			first = false;
    		}
    		else {
    			first = true;
    			double progress = 0;
    			int totalcourse = 0;
    			Vector<String> subjects = scraper.scrapeSubject(textfieldURL.getText(), textfieldTerm.getText());
    			for(String a: subjects) {
    				try {
    		    		Vector<AbstractCollection> vec = scraper.scrape(textfieldURL.getText(), textfieldTerm.getText(),a);
    		    		Vector<Course> v = (Vector<Course>) vec.get(0);
    		    		totalcourse+=v.size();
    		    		HashSet<Instructor> ins = (HashSet<Instructor>) vec.get(1);
    		    		int allNumSections = 0;
    		    		LocalTime TU310 = LocalTime.parse("03:10PM", DateTimeFormatter.ofPattern("hh:mma", Locale.US));
    		    		HashSet<Instructor> ins_tu310 = (HashSet<Instructor>)ins.clone(); //???
    		    		for (Course c : v) {
    		    			allNumSections += c.getNumSections();
    		        		String newline = c.getTitle() + "\n";
    		        		for (int i = 0; i < c.getNumSections(); i++) {
    		        			Section sec = c.getSection(i);
//    		        			newline += "Slot " + i + ": " + t + "\n";
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
    		    		/*
    		    		searchInfo += "Total Number of difference sections in this search: " + allNumSections + "\n";
    		    		searchInfo += "Total Number of Course in this search: " + v.size() + "\n";
    		    		searchInfo += "Instructors who has teaching assignment this term but does not need to teach at Tu 3:10pm:\n";
    		    		for (Instructor inst: ins_tu310) {
    		    			searchInfo += inst + "\n"; // è¯´å¥½çš„ä¸�ä¼šé‡�å¤�å‘¢ï¼Ÿï¼Ÿï¼Ÿ
    		    		}
    		    		*/
    		    		//textAreaConsole.appendText(searchInfo);
    		    		
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
    		    System.out.println("SUBJECT is done");
    		    progress+=1/(double)subjects.size();
    		    progressbar.setProgress(progress);
    			}
    			//textAreaConsole.setText("no way");
    			textAreaConsole.setText(textAreaConsole.getText() + "\n" + "Total Number of Courses fetched: "+totalcourse);
    		}
    	}catch (PageNotFoundError e) {
    		AnchorPane ap = (AnchorPane)tabStatistic.getContent();
    		Label msg = new Label("404 NOT FOUND");
    		ap.getChildren().add(msg);
    	}
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
    			searchInfo += inst + "\n"; // è¯´å¥½çš„ä¸�ä¼šé‡�å¤�å‘¢ï¼Ÿï¼Ÿï¼Ÿ
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
