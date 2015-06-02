package libsys;

import java.io.File;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibraryWindowController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loan;

    @FXML
    private ListView<Book> yourBooks;

    @FXML
    private Text name;

    @FXML
    private Button save;

    @FXML
    private ListView<Book> availableBooks;

    @FXML
    private Button giveBack;

    @FXML
    private Button quit;
    
    @FXML
    private Text noBook;

    @FXML
    void initialize() {
    	name.setText(MainWindowController.getClient());
    	
    	// ACAO DO BOTAO "SAIR SEM SALVAR"
    	quit.setOnAction(event->{
    		System.out.println(new Cliente().getClienteType(MainWindowController.getClient()));
    		Stage stage = (Stage) quit.getScene().getWindow();
            stage.close();
    	});
    	
    	// CRIA A LISTA DE LIVROS DO USUARIO
        ObservableList<Book> yourBooksList = FXCollections.observableArrayList();
        new Books().getBooks(yourBooksList, MainWindowController.getClient());
        yourBooks.setItems(yourBooksList);
        
        // CRIA A LISTA DE LIVROS DA BIBLIOTECA
        ObservableList<Book> availableBooksList = FXCollections.observableArrayList();
        new Books().getBooks(availableBooksList);
        availableBooks.setItems(availableBooksList);
        
        // ACAO DO BOTAO "SALVAR E SAIR"
        save.setOnAction(event->{
        	if(howManyBooks(yourBooksList)){
        		
        	} else {
	        	// ACAO NO BOOKS.CSV
	        	File file = new File("books.csv");
	        	file.delete();
	        	try {
					file.createNewFile();
				} catch (Exception e1) {
					System.out.println("Um erro ocorreu!");
				}
	    		for(Book b : availableBooksList){
	    			new Books().addBook(b.getBookName(), b.getBookType(), "books.csv");
	    		}
	    		
	    		// ACAO NO USUARIO.CSV
	    		File ofile = new File(MainWindowController.getClient()+".csv");
	    		ofile.delete();
	    		try {
					new Cliente().createNewCliente(MainWindowController.getClient(), MainWindowController.getPassword(), MainWindowController.getType());
				} catch (Exception e) {
					System.out.println("Um erro ocorreu!");
				}
	    		for(Book b : yourBooksList){
	    			new Books().addBook(b.getBookName(), b.getBookType(), MainWindowController.getClient()+".csv");
	    		}
	    		Stage stage = (Stage) quit.getScene().getWindow();
	            stage.close();
        	}
    	});
        
        // ACAO DO BOTAO "EMPRESTAR"
        loan.setOnAction(event->{
        	if(availableBooks.getSelectionModel().getSelectedItem() == null){
        	} else {
	        	if(new Cliente().getClienteType(MainWindowController.getClient()) == 3 && availableBooks.getSelectionModel().getSelectedItem().getBookType() == 2)
	        		noBook.setFill(Color.RED);
	        	
	        	else{
	        		noBook.setFill(Color.TRANSPARENT);
		        	yourBooksList.add(availableBooks.getSelectionModel().getSelectedItem());
		        	new Books().removeBook(availableBooksList, availableBooks.getSelectionModel().getSelectedIndex());
	        	}
        	}
        });
        
        // ACAO DO BOTAO "DEVOLVER"
        giveBack.setOnAction(event->{
        	if(yourBooks.getSelectionModel().getSelectedItem() == null){
        	} else {
        		noBook.setFill(Color.TRANSPARENT);
	        	availableBooksList.add(yourBooks.getSelectionModel().getSelectedItem());
	        	new Books().removeBook(yourBooksList, yourBooks.getSelectionModel().getSelectedIndex());
        	}
        });
    }
    
    // FUNCAO QUE EXIBE ERRO CASO O NUMERO DE LIVROS EXCEDA
    public void tooManyBooks(String s){
    	noBook.setText(s);
    	noBook.setFill(Color.RED);
    }
    
    // FUNCAO QUE VERIFICA SE HÁ LIVROS ACIMA DO LIMITE PARA O USUARIO
    public boolean howManyBooks(ObservableList<Book> yourBooksList){
    	int numBooks = 0;
    	for(Book b: yourBooksList){
    		if(b != null)
    			numBooks++;
    	}
    	if(MainWindowController.getType() == 1 && numBooks > 4){
    		tooManyBooks("Limite de emprestimo : 4");
    		return true;
    	} else if (MainWindowController.getType() == 2 && numBooks > 6){
    		tooManyBooks("Limite de emprestimo : 6");
    		return true;
    	} else if (MainWindowController.getType() == 3 && numBooks > 2){
    		tooManyBooks("Limite de emprestimo : 2");
    		return true;
    	}
    	return false;
    }
    
    // LOADER DO FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LibraryWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene((Parent) loader.load()));
		mainStage.show();
	}
}
