package libsys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Books {
	public void addBook(String bookname, String type){
		int intType;
		
		// Tipo do texto foi convertido para int
		if(type.equals("Texto")){
			intType = 2;
		} else {
			intType = 1;
		}
		
		// Arquivo criado (se já não existia)
		File file = new File("books.csv");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro!");
		}
		
		// Cria o FileWriter e adiciona um novo livro ao arquivo
		try {
			FileWriter fw = new FileWriter(file, true);
			
			fw.append(bookname);
			fw.append(", ");
			fw.append(""+intType);
			fw.append("\n");
			
			fw.close();
		} catch (IOException e) {
			System.out.println("Um erro ocorreu");
		}
	}
}
