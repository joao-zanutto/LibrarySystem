package libsys;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibraryWindowController {
	private int day, month, year;
	private String client;
	
	public void setDay(int day){
		this.day = day;
	}
	
	public void setMonth(int month){
		this.month = month;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setClient(String login){
		this.client = login;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loan;

    @FXML
    private ListView<String> yourBooks;

    @FXML
    private Text name;

    @FXML
    private Button save;

    @FXML
    private ListView<String> availableBooks;

    @FXML
    private Button giveBack;

    @FXML
    private Button quit;

    @FXML
    void initialize() {
    	name.setText(client);
    	System.out.print(client);
    	
        ObservableList<String> yourBooksList = FXCollections.observableArrayList(
        		
        		);
        
        ObservableList<String> availableBooksList = FXCollections.observableArrayList(
        		
        		);
        yourBooks.setItems(yourBooksList);
        availableBooks.setItems(availableBooksList);
        
        
    }
    
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LibraryWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene((Parent) loader.load()));
		mainStage.show();
	}
}
