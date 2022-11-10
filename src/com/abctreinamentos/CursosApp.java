package com.abctreinamentos;

import java.util.List;

import javax.swing.JOptionPane;

public class CursosApp {
	
	public void iniciar() {
		
		CursosHome home = new CursosHome();
		Cursos curso = new Cursos();
		
		String nome, valor, url, option = "";
		
			while (!option.equalsIgnoreCase("Sair")) {
				
				String[] opcao = { "Mostrar todos os cursos", "Mostrar curso",
						"Cadastrar novo curso", "Atualizar curso", 
						"Deletar curso", "Sair" };
				
				Object escolha = JOptionPane.showInputDialog(null,
			             "Escolha a operação:", "Sistema de gerenciamento de cursos",
			             JOptionPane.INFORMATION_MESSAGE, null,
			             opcao, opcao[0]);
				
				option = escolha.toString();
				
				switch (option) {
				
					case "Mostrar todos os cursos":
						
						System.out.println("[1]: Todos os cursos:");
						
						List<Cursos> lista = home.buscarTodos();
						
						lista.forEach(e->System.out.println(e.getNome()+" <=> "+e.getUrl()));
						
						break;
					
					case "Mostrar curso":
						
						nome = JOptionPane.showInputDialog(null, "Digite o nome do curso:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						System.out.println("[2]: Curso:");
						
						curso = home.buscar(nome);
						
						System.out.println(curso.getNome()+" <=> "+curso.getUrl());
						
						break;
						
					case "Cadastrar novo curso":
						
						System.out.println("[3]: Novo curso...");
						
						nome = JOptionPane.showInputDialog(null, "Digite nome do curso:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						valor = JOptionPane.showInputDialog(null, "Digite valor em reais (R$):", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						url = JOptionPane.showInputDialog(null, "Digite url:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);		
						
						curso.setNome(nome);
						curso.setValor(valor);
						curso.setUrl(url);
						
						home.inserir(curso);
						
						System.out.println("Curso cadastrado com sucesso!");
						
						
						break;
						
					case "Atualizar curso":
						
						System.out.println("[4]: Atualizando curso...");
						
						nome = JOptionPane.showInputDialog(null, "Digite nome do curso:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						valor = JOptionPane.showInputDialog(null, "Digite valor em reais (R$):", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						url = JOptionPane.showInputDialog(null, "Digite url:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);	
						
						Cursos cursoatual = new Cursos();
						
						cursoatual = home.buscar(nome);
						
						curso.setIdCurso(cursoatual.getIdCurso());						
						curso.setNome(nome);
						curso.setValor(valor);
						curso.setUrl(url);
												
						home.alterar(curso);
						
						System.out.println("Atualização de cadastro com sucesso!");
						
						break;
						
					case "Deletar curso":
						
						System.out.println("[5]: Deletando...");
						
						nome = JOptionPane.showInputDialog(null, "Digite nome do curso:", "Cadastro",
					             JOptionPane.INFORMATION_MESSAGE);
						
						curso = home.buscar(nome);
						
						home.deletar(curso);
						
						System.out.println("Curso deletado com sucesso!");
						break;
										
					case "Sair":
						System.out.println("Encerrando...");
						home.getSession().close();
						break;					
				}
				
			}
	}
	
	

}
