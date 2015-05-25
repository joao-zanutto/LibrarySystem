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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Text error2;

    // Initialize que monta a configuração do programa
    @FXML
    void initialize() {
    	// Seta os itens da aba "dia"
    	day.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
    			18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);    	
    	
    	// Seta os itens da aba "mês"
    	month.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    	
    	// Botão de LogIn, onde ele verifica se todos os campos estão OK para então prosseguir
    	// 		abrindo uma nova tela para opções do usuário
    	confirm.setOnAction(event->{
    		if(login.getText().equals("") || password.getText().equals("") || year.getText().equals("") || day.getValue() == null || month.getValue() == null){
    			if(login.getText().equals("") || password.getText().equals("")){
    				error2.setText("Erro login ou senha");
    			} else {
    				error2.setText("Erro na data");
    			}
    			
    			error2.setFill(Color.RED);
    		} else {
	    		boolean result = new Cliente().autenticaCliente(login.getText(), password.getText());
	    		if(result == false){
	    			error2.setText("Impossível validar");
	    			error2.setFill(Color.RED);
	    		} else {
	    			try {
						LibraryWindowController lwc = new LibraryWindowController();
						lwc.setDay(day.getValue());
						lwc.setMonth(month.getValue());
						lwc.setYear(Integer.parseInt(year.getText()));
						lwc.setClient(login.getText());
						lwc.start(new Stage());
					} catch (Exception e) {
						System.out.println("Um erro ocorreu!");
					}
	    		}
    		}
    	});
    	
    	// Abre a nova janela para criar um novo registro de pessoa
        newregistry.setOnAction(event->{
        	try {
				new SignUpWindowController().start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu!");
			}
        });
        
        // Abre a nova janela para criar um novo registro de um livro
        newbook.setOnAction(event->{
        	try {
				new NewBookWindowController().start(new Stage());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu!");
			}
        });
    }

    // Loader do FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene( (Parent) loader.load()));
		mainStage.show();
	}
	
	// Método main que começa o programa ao executar
	public static void main(String[] args){
		launch(args);
	}
}
