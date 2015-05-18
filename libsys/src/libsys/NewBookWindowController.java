package libsys;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewBookWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton texto;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    private RadioButton geral;

    @FXML
    private TextField bookname;

    @FXML
    void initialize() {
    	cancel.setOnAction(event->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
    }
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewBookWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
}
