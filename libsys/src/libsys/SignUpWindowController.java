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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Text error;

    @FXML
    void initialize() {
        ToggleGroup group = new ToggleGroup();
        
        professor.setToggleGroup(group);
        student.setToggleGroup(group);
        comunity.setToggleGroup(group);
        
        cancel.setOnAction(event->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
        
        confirm.setOnAction(event->{
        	if(group.getSelectedToggle() == null || login.getText().equals("") || password.getText().equals("")){
        		error.setFill(Color.RED);
        	} else {
        		RadioButton rb = (RadioButton) group.getSelectedToggle();
        		try {
					new Cliente().createNewCliente(login.getText(), password.getText(), rb.getText());
				} catch (Exception e) {
					System.out.println("Um erro ocorreu!");
				}
        	}
        });
    }

	FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpWindow.fxml"));
	
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
}