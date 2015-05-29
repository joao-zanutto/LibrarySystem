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
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Text error;
    
    @FXML
    void initialize() {
    	ToggleGroup group = new ToggleGroup();
    	
    	cancel.setOnAction(event->{
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        });
    	
    	geral.setToggleGroup(group);
    	texto.setToggleGroup(group);
    	
    	confirm.setOnAction(event->{
    		if(group.getSelectedToggle() == null || bookname.getText().equals("")){
        		error.setFill(Color.RED);
        	} else {
        		RadioButton rb = (RadioButton) group.getSelectedToggle();
        		new Books().addBook(bookname.getText(), rb.getText(), "books.csv");
        		Stage stage = (Stage) cancel.getScene().getWindow();
	            stage.close();
        	}
    	});
    }
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewBookWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
}
