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
    	
    	quit.setOnAction(event->{
    		System.out.println(new Cliente().getClienteType(MainWindowController.getClient()));
    		Stage stage = (Stage) quit.getScene().getWindow();
            stage.close();
    	});
    	
        ObservableList<Book> yourBooksList = FXCollections.observableArrayList();
        new Books().getBooks(yourBooksList, MainWindowController.getClient());
        yourBooks.setItems(yourBooksList);
        
        ObservableList<Book> availableBooksList = FXCollections.observableArrayList();
        new Books().getBooks(availableBooksList);
        availableBooks.setItems(availableBooksList);
        
        save.setOnAction(event->{
        	// ACAO NO BOOKS.CSV
        	File file = new File("books.csv");
        	file.delete();
        	try {
				file.createNewFile();
			} catch (Exception e1) {
				System.out.println("Um erro ocorreu!");
			}
    		for(Book b : availableBooksList){
    			new Books().addBook(b.getBookName(), ""+b.getBookType(), "books.csv");
    		}
    		
    		// ACAO NO USUARIO.CSV
    		File ofile = new File(MainWindowController.getClient()+".csv");
    		ofile.delete();
    		try {
    			System.out.println(MainWindowController.getClient() + " " + MainWindowController.getPassword() + " " + MainWindowController.getType());
				new Cliente().createNewCliente(MainWindowController.getClient(), MainWindowController.getPassword(), MainWindowController.getType());
			} catch (Exception e) {
				System.out.println("Um erro ocorreu!");
			}
    		for(Book b : yourBooksList){
    			new Books().addBook(b.getBookName(), ""+b.getBookType(), MainWindowController.getClient()+".csv");
    		}
    		Stage stage = (Stage) quit.getScene().getWindow();
            stage.close();
    	});
        
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
        
        giveBack.setOnAction(event->{
        	if(yourBooks.getSelectionModel().getSelectedItem() == null){
        	} else {
        		noBook.setFill(Color.TRANSPARENT);
	        	availableBooksList.add(yourBooks.getSelectionModel().getSelectedItem());
	        	new Books().removeBook(yourBooksList, yourBooks.getSelectionModel().getSelectedIndex());
        	}
        });
    }
    
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LibraryWindow.fxml"));
	public void start(Stage mainStage) throws Exception {
		mainStage.setScene(new Scene((Parent) loader.load()));
		mainStage.show();
	}
}
