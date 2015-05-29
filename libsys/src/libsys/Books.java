package libsys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class Books {
	public void getBooks(ObservableList<Book> list){
		File file = new File("books.csv");
		try {
			Scanner fr = new Scanner(file);
			fr.useDelimiter(",");
			while(fr.hasNextLine()){
				String bookname = fr.next();
				fr.skip(",");
				String s = fr.nextLine();
				int booktype = Integer.parseInt(s);
				list.add(new Book(bookname, booktype));
			}
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.print("Um erro ocorreu!");
		}
	}
	
	public void getBooks(ObservableList<Book> bk, String usuario){
		File file = new File(usuario + ".csv");
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine();
			sc.nextLine();
			sc.useDelimiter(",");
			
			while(sc.hasNextLine()){
				String s = sc.next();
				sc.skip(",");
				String s2 = sc.nextLine();
				int i = Integer.parseInt(s2);
				Book b = new Book(s, i);
				bk.add(b);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Um erro ocorreu!");
		}
	}
	
	public void removeBook(ObservableList<Book> list, int index){
		list.remove(index);
	}
	
	public void addBook(String bookname, String type, String filename){
		int intType;
		
		// Tipo do texto foi convertido para int
		if(type.equals("Texto")){
			intType = 2;
		} else {
			intType = 1;
		}
		
		// Arquivo criado (se já não existia)
		File file = new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro!");
		}
		
		// Cria o FileWriter e adiciona um novo livro ao arquivo
		try {
			FileWriter fw = new FileWriter(file, true);
			
			fw.append(bookname);
			fw.append(",");
			fw.append(""+intType);
			fw.append("\n");
			
			fw.close();
		} catch (IOException e) {
			System.out.println("Um erro ocorreu");
		}
	}
}
