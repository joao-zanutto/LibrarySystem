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