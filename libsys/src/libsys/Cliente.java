package libsys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {
	private String login;
	private String password;
	private int intType; // 1-Aluno, 2-Professor, 3-Comunidade
	
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
}