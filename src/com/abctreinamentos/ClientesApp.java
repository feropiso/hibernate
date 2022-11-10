package com.abctreinamentos;


import java.util.List;
import javax.swing.JOptionPane;

public class ClientesApp {
	
	public void iniciar() {
		
		ClientesHome home = new ClientesHome();
		Clientes cliente = new Clientes();
		
		String cpf, nome, email, option = "";
		
			while (!option.equalsIgnoreCase("Sair")) {
				
				String[] opcao = { "Mostrar todos os clientes", "Mostrar cliente",
						"Cadastrar novo cliente", "Atualizar cadastro de cliente", 
						"Deletar cliente", "Sair" };
				
				Object escolha = JOptionPane.showInputDialog(null,
			             "Escolha a operação:", "Sistema de gerenciamento de clientes",
			             JOptionPane.INFORMATION_MESSAGE, null,
			             opcao, opcao[0]);
				
				option = escolha.toString();
				
				switch (option) {
				
					case "Mostrar todos os clientes":
						
						System.out.println("[1]: Todos os clientes:");
						List<Clientes> lista = home.buscarTodos();
						lista.forEach(e->System.out.println(e.getCpf()+" <=> "+e.getNome()));
						
						break;
					
					case "Mostrar curso":
						
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						System.out.println("[2]: Cliente:");
						
						cliente = home.buscar(cpf);
						
						System.out.println(cliente.getCpf()+" <=> "+cliente.getNome());
						
						break;
						
					case "Cadastrar novo cliente":
						
						System.out.println("[3]: Novo cliente:");
						
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						nome = JOptionPane.showInputDialog(null, "Digite nome:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						email = JOptionPane.showInputDialog(null, "Digite email:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);	
						
						cliente.setCpf(cpf);
						cliente.setEmail(email);
						cliente.setNome(nome);
						
						home.inserir(cliente);
						
						System.out.println("Cliente cadastrado com sucesso!");
						
						
						break;
						
					case "Atualizar cadastro de cliente":
						
						System.out.println("[4]: Atualizando cadastro...");
						
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						nome = JOptionPane.showInputDialog(null, "Digite nome:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						email = JOptionPane.showInputDialog(null, "Digite email:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);	
						
						Clientes clienteatual = new Clientes();
						clienteatual = home.buscar(cpf);
						
						cliente.setIdCliente(clienteatual.getIdCliente());
						cliente.setNome(nome);
						cliente.setCpf(cpf);
						cliente.setEmail(email);
												
						home.alterar(cliente);
						
						System.out.println("Atualização de cadastro com sucesso!");
						
						break;
						
					case "Deletar cliente":
						
						System.out.println("[5]: Deletando...");
						
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						cliente = home.buscar(cpf);
						
						home.deletar(cliente);
						
						System.out.println("Atualização de cadastro com sucesso!");
						
						break;
										
					case "Sair":
						System.out.println("Encerrando...");
						home.getSession().close();
						break;					
				}
				
			}
	}
	
	
}
