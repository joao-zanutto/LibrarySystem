package libsys;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainWindowController extends Application{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirm;

    @FXML
    private PasswordField password;

    @FXML
    private Button newregistry;

    @FXML
    private TextField login;
    
    @FXML
    private TextField year;
    
    @FXML
    private ComboBox<Integer> day;
    
    @FXML
    private ComboBox<Integer> month;
    
    @FXML
    private Button newbook;

    @FXML
    void initialize() {
    	day.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
    			18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);    	
    	
    	month.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    	
        newregistry.setOnAction(event->{
        	try {
				new SignUpWindowController().start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu!");
			}
        });
        
        newbook.setOnAction(event->{
        	try {
				new NewBookWindowController().start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu!");
			}
        });
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
