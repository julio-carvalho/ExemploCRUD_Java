package br.com.crud.bo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.crud.beans.Cliente;
import br.com.crud.factory.ConexaoBD;

public class ClienteBO {
	
	public void cadastro(String nome, int idade) {
		Cliente cliente = new Cliente();
		
		//comando sql
		String sql = "INSERT INTO clientes(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		Connection conexaoBanco = null;
		PreparedStatement pstm = null;
		
		try {
			//conexao com o bd
			conexaoBanco = ConexaoBD.createConnectionToMySQL();
		
			pstm = (PreparedStatement) conexaoBanco.prepareStatement(sql);
			pstm.setString(1, nome);
			pstm.setInt(2, idade);
			pstm.setDate(3, new Date(cliente.getDataCadastro().getTime()));
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conexaoBanco != null) {
					conexaoBanco.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void alterar(int id, String nome, int idade) {
		String sql = "UPDATE clientes SET nome = ?, idade = ? WHERE id = ?";
		
		Connection conexaoBanco = null;
		PreparedStatement pstm = null;
		
		try {
			conexaoBanco = ConexaoBD.createConnectionToMySQL();
			pstm = (PreparedStatement) conexaoBanco.prepareStatement(sql);
			
			pstm.setString(1, nome);
			pstm.setInt(2, idade);
			
			pstm.setInt(3, id);
			
			pstm.execute();
			
			System.out.println("Alteração feita com sucesso!");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conexaoBanco != null) {
					conexaoBanco.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	public List<Cliente> imprimir() {
		String sql = "SELECT * FROM clientes";
		
		List<Cliente> listClientes = new ArrayList<Cliente>();
		
		Connection conexaoBanco = null;
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		try {
			conexaoBanco = ConexaoBD.createConnectionToMySQL();
			pstm = (PreparedStatement) conexaoBanco.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				//armazena a variavel do banco
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getInt("idade"));
				cliente.setDataCadastro(rs.getDate("dataCadastro"));
				
				listClientes.add(cliente);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conexaoBanco != null) {
					conexaoBanco.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		return listClientes;
	}
	
	public void deletar(int id) {
		String sql = "DELETE FROM clientes WHERE id = ?";
		
		Connection conexaoBanco = null;
		PreparedStatement pstm = null;
		
		try {
			conexaoBanco = ConexaoBD.createConnectionToMySQL();
			pstm = (PreparedStatement) conexaoBanco.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println("Cliente deletado com sucesso!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conexaoBanco != null) {
					conexaoBanco.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	public boolean buscaCliente(int id) {
		boolean valida = false;
		String sql = "SELECT * FROM clientes WHERE id = ?";
		
		Connection conexaoBanco = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conexaoBanco = ConexaoBD.createConnectionToMySQL();
			pstm = (PreparedStatement) conexaoBanco.prepareStatement(sql);
			
			//armazena a variavel do banco
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				valida = true;
			} else {
				return valida;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conexaoBanco != null) {
					conexaoBanco.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return valida;
	}



}
