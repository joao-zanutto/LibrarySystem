package libsys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cliente {
	private String login;
	private String password;
	private int intType; // 1-Aluno, 2-Professor, 3-Comunidade
	
	// Le o arquivo filename e retorna o tipo do cliente
	public int getClienteType(String filename){
		File file = new File(filename+".csv");
		int type = 0;
		try {
			Scanner fr = new Scanner(file);
			fr.nextLine();
			String s = fr.nextLine();
			type = Integer.parseInt(s);
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.print("Um erro ocorreu!");
		}
		
		return type;
	}
	
	// Cria um novo cliente de nome login, senha password e tipo type
	//  AQUI TYPE É UMA STRING, QUE É VERIFICADA E TRANSFORMADA EM INT
	public void createNewCliente(String login, String password, String type) throws IOException{
		this.login = login;
		this.password = password;
		
		if(type.equals("Aluno"))
			this.intType = 1;
		else if(type.equals("Professor"))
			this.intType = 2;
		else
			this.intType = 3;
		
		File newFile = new File(this.login+".csv");
		if(newFile.createNewFile()){
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(this.password);
			bw.newLine();
			bw.write(""+intType);
			bw.newLine();
			
			bw.close();
		} else {
			System.out.println("Cliente já cadastrado no banco de dados");
		}
	}
	
	// Cria um novo cliente de nome login, senha password e tipo type
	//  AQUI TYPE É UM INTEIRO
	public void createNewCliente(String login, String password, int type) throws IOException{
		this.login = login;
		this.password = password;
		
		File newFile = new File(this.login+".csv");
		if(newFile.createNewFile()){
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(this.password);
			bw.newLine();
			bw.write(""+type);
			bw.newLine();
			
			bw.close();
		} else {
			System.out.println("Cliente já cadastrado no banco de dados");
		}
	}
	
	// Verifica se os dados inseridos em username e password batem com os do banco de dados
	public boolean autenticaCliente(String username, String password){
		File file = new File(username+".csv");
		try {
			if(!file.exists()){
				return false;
			}
			
			Scanner sc = new Scanner(file.getAbsoluteFile());
			if(sc.nextLine().equals(password)){
				sc.close();
				return true;
			} else {
				sc.close();
				return false;
			}
			
		} catch (IOException e) {
			System.out.println("Um erro ocorreu!");
		}
		return false;
	}
}