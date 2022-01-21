package br.com.crud.main;

import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

import br.com.crud.beans.Cliente;
import br.com.crud.bo.ClienteBO;
import br.com.crud.factory.ConexaoBD;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		ClienteBO clienteBO = new ClienteBO();

		boolean continuar = true;

		while (continuar) {
			System.out.println("\n* - Escolha a opção:");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Imprimir clientes");
			System.out.println("3 - Alterar cliente");
			System.out.println("4 - Deletar cliente");
			System.out.println("5 - Sair");
			int opc = scan.nextInt();

			switch (opc) {
			case 1:
				System.out.println("\nCadastrar Cliente");
				scan.nextLine();
				System.out.println("Digite o nome: ");
				String nome = scan.nextLine();

				System.out.println("Digite a idade: ");
				int idade = scan.nextInt();

				clienteBO.cadastro(nome, idade);
				break;
			case 2:
				System.out.println("\nImprimir Clientes");
				for (Cliente c : clienteBO.imprimir()) {
					System.out.println("\nID: " + c.getId());
					System.out.println("Nome: " + c.getNome());
					System.out.println("Idade: " + c.getIdade());
					System.out.println("Data de cadastro: " + c.getDataCadastro());
				}

				break;
			case 3:
				System.out.println("\nAlterar Clientes");
				System.out.println("Digite o ID do cliente que você deseja alterar: ");
				int idAltera = scan.nextInt();
				
				if(clienteBO.buscaCliente(idAltera)) {
					scan.nextLine();
					System.out.println("Digite o nome: ");
					String nomeAlterado = scan.nextLine();
					
					System.out.println("Digite a idade: ");
					int idadeAlterada = scan.nextInt();
					
					clienteBO.alterar(idAltera, nomeAlterado, idadeAlterada);
				} else {
					System.out.println("Cliente não encontrado");
				}
				break;
			case 4:
				System.out.println("\nDeletar Clientes");
				System.out.println("Digite o ID do cliente que você deseja deletar: ");
				int idDeleta = scan.nextInt();
				
				if(clienteBO.buscaCliente(idDeleta)) {
					clienteBO.deletar(idDeleta);
				} else {
					System.out.println("Cliente não encontrado");
				}
				break;
			case 5:
				System.out.println("\nSaindo");
				continuar = false;
			default:
				System.out.println("\nOpção inválida");
				break;
			}
		}
		
		System.exit(0);

	}
}
