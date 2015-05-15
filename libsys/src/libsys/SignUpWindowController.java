package libsys;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SignUpWindowController extends Application{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton professor;

    @FXML
    private RadioButton student;

    @FXML
    private RadioButton comunity;

    @FXML
    private TextField login;

    @FXML
    void initialize() {
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert professor != null : "fx:id=\"professor\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert student != null : "fx:id=\"student\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert comunity != null : "fx:id=\"comunity\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'SignUpWindow.fxml'.";
        
        ToggleGroup group = new ToggleGroup();
        
        professor.setToggleGroup(group);
        student.setToggleGroup(group);
        comunity.setToggleGroup(group);
        
        cancel.setOnAction(event->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
    }

	FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
	
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
}