package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
	
	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bdclientes";
	
	//Conexão
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conexao = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return conexao;
	}
	
	public static void main(String[] args) throws Exception {	
			
			
			Connection conexao = createConnectionToMySQL();
			
			if(conexao != null) {
				System.out.println("Conexão realizada com sucesso!");
				conexao.close();
			}
		}
	
}
