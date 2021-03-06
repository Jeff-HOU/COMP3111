/**
 * 
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 * 
 */
package comp3111.coursescraper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class FxTest extends ApplicationTest {

	private Scene s;
	
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Course Scraper");
   		stage.show();
   		s = scene;
	}

	
	@Test
	public void testButton() {
		clickOn("#tabSfq");
		clickOn("#buttonInstructorSfq");
		Button b = (Button)s.lookup("#buttonInstructorSfq");
		sleep(1000);
//		assertTrue(!b.isDisabled());
	}

	
	@Test
	public void testMainTag() {
		clickOn("#tabMain");
		TextField tf_url = (TextField) s.lookup("#textfieldURL");
		tf_url.setText("https://w5.ab.ust.hk/wcq/cgi-bin/");
		TextField tf_term = (TextField) s.lookup("#textfieldTerm");
		tf_term.setText("1830");
		TextField tf_subject = (TextField) s.lookup("#textfieldSubject");
		tf_subject.setText("COMP");
		clickOn("#buttonSearch");
		sleep(5000);
		TextArea ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
	}
	
	@Test
	public void testListTag() {
		clickOn("#tabMain");
		clickOn("#buttonSearch");
		sleep(5000);
//		clickOn("#tabFilter");
//		CheckBox checkboxWED = (CheckBox) s.lookup("filterWED");
//		checkboxWED.setSelected(true);
//		sleep(1000);
		clickOn("#tabList");
	}
	
	@Test
	public void testAllSearchTag() {
		clickOn("#tabAllSubject");
		
		clickOn("#buttonAllSubject");
		sleep(5000);
		TextArea ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
		
		clickOn("#buttonAllSubject");
		sleep(5000);
		ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
		
		//clickOn("#tabMain");
		TextField tf_url = (TextField) s.lookup("#textfieldURL");
		tf_url.setText("htps://w5.ab.ust.hk/wcq/cgi-bin/");
		TextField tf_term = (TextField) s.lookup("#textfieldTerm");
		tf_term.setText("1830");
		TextField tf_subject = (TextField) s.lookup("#textfieldSubject");
		tf_subject.setText("COMP");
		//clickOn("#tabAllSubject");
		clickOn("#buttonAllSubject");
		sleep(5000);
		ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
		
		//clickOn("#tabMain");
		tf_url.setText("https://w5.ab.ust.hk/wcq/cgi-bin/");
		tf_term.setText("1850");
		tf_subject.setText("COMP");
		//clickOn("#tabAllSubject");
		clickOn("#buttonAllSubject");
		sleep(5000);
		ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
		
		//clickOn("#tabMain");
		tf_url.setText("https://w5.ab.ust.hk/wcq/cgi-bin/");
		tf_term.setText("1830");
		tf_subject.setText("ABCD");
		//clickOn("#tabAllSubject");
		clickOn("#buttonAllSubject");
		sleep(5000);
		ta = (TextArea)s.lookup("#textAreaConsole");
		assertTrue(!ta.getText().trim().isEmpty());
		//assertTrue(ta.getText().substring(0, 39)=="Total Number of Categories/Code Prefix: ");
	}
	
	@Test
	public void testShowTable() {
		
		clickOn("#tabMain");
		TextField tf_url = (TextField) s.lookup("#textfieldURL");
		tf_url.setText("https://w5.ab.ust.hk/wcq/cgi-bin/");
		TextField tf_term = (TextField) s.lookup("#textfieldTerm");
		tf_term.setText("1830");
		TextField tf_subject = (TextField) s.lookup("#textfieldSubject");
		tf_subject.setText("COMP");
		clickOn("#buttonSearch");
		sleep(5000);
		TextArea ta = (TextArea)s.lookup("#textAreaConsole");
		
		clickOn("#tabFilter");
		clickOn("#filterSelectAll");
		clickOn("#filterSelectAll");
		
		clickOn("#tabList");
		
		clickOn("#tabTimetable");
		sleep(5000);
	}
	
}
