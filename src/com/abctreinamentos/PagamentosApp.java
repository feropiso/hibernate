package com.abctreinamentos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;


public class PagamentosApp {
	
	private Cursos curso;
	private Clientes cliente;
	private Pagamentos pag;
	private CursosHome home_cursos;
	private ClientesHome home_clientes;
	private PagamentosHome home_pagamentos;
	
	public PagamentosApp(){
		this.curso = new Cursos();
		this.cliente = new Clientes();
		this.pag = new Pagamentos();
		this.home_cursos = new CursosHome();
		this.home_clientes = new ClientesHome();
		this.home_pagamentos = new PagamentosHome();
	}
	
	public void iniciar() {
	
		String cpf, option = "";
		List<Cursos> lista = home_pagamentos.buscarPorCursos();
		
		while (!option.equalsIgnoreCase("Sair")) {
				
				String[] opcao = { "Cadastrar pagamento", "Mostrar faturamento", 
						"Mostrar pagamento por cliente", "Deletar pagamento", "Sair" };
				
				Object escolha = JOptionPane.showInputDialog(null,
			             "Escolha a operação:", "Sistema de gerenciamento de pagamentos",
			             JOptionPane.INFORMATION_MESSAGE, null,
			             opcao, opcao[0]);
				
				option = escolha.toString();
				
				switch (option) {
										
					case "Cadastrar pagamento":
						
						System.out.println("[1]: Cadastrando pagamento:");
						
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro de pagamento",
					             JOptionPane.INFORMATION_MESSAGE);
						
						
						cadastraPagamento(cpf, lista);
						
						break;
					
					case "Mostrar faturamento":
						
						System.out.println("[2]: Faturamento:");
						
						faturamento(lista);
						break;
						
					case "Mostrar pagamento por cliente":
						
						System.out.println("[3]: Faturamento por cliente:");
						cpf = JOptionPane.showInputDialog(null, "Digite cpf:", "Cadastro de pagamento",
					             JOptionPane.INFORMATION_MESSAGE);
						
						faturamentoPorCliente(cpf);					
						break;
					
					case "Deletar pagamento":
						System.out.println("Encerrando...");
						
						int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id pagamento:", "Cadastro de pagamento",
					             JOptionPane.INFORMATION_MESSAGE));
						
						pag = home_pagamentos.buscar(id);
						
						home_pagamentos.deletar(pag);
						
						System.out.println("Pagamento deletado...");
						
						break;
						
					case "Sair":
						System.out.println("Encerrando...");
						home_pagamentos.getSession().close();
						break;					
				}
				
			}
	}
	
	private void cadastraPagamento(String cpf, List<Cursos> lista) {
				
		cliente = home_clientes.buscar(cpf);
		
		if (cliente == null) {
			System.out.println("Cliente ainda não cadastrado!");
			return;
		}
						
		curso = escolherCurso(lista);
				
		int id_cliente = cliente.getIdCliente();
		int id_curso = curso.getIdCurso();
		
		List <Pagamentos> pgcliente = home_pagamentos.buscarPorCliente(id_cliente);
			
		if (!pgcliente.stream().anyMatch(l->l.getFk_curso() == id_curso)) {
			
			pag.setFk_cliente(id_cliente);
			pag.setFk_curso(id_curso);			
			pag.setData(dataAtual());
			
			home_pagamentos.inserir(pag);
			
			System.out.println("Pagamento cadastrado com sucesso!");
			
			return;
		}
		
		System.out.println("Cliente já possui esse curso!");
	}
	
	private Cursos escolherCurso(List<Cursos> lista) {
		
		String [] opcao;
		
		Set<Cursos> setcursos = new HashSet<>(lista);
				
		opcao = new String[setcursos.size()];
		
		int index = 0;
		
		for(Iterator<Cursos> iter = setcursos.iterator(); iter.hasNext();) {			
			Cursos cursoAtual = iter.next();
			opcao[index] = cursoAtual.getNome();
			index++;
		}
		
	
		Object escolha = JOptionPane.showInputDialog(null,
	             "Escolha o curso:", "Sistema de gerenciamento de pagamentos",
	             JOptionPane.INFORMATION_MESSAGE, null,
	             opcao, opcao[0]);
		
		Cursos cursoescolhido = new Cursos();
		
		for(Cursos curso: lista) {
			if(curso.getNome().equalsIgnoreCase(escolha.toString())) {
				cursoescolhido = curso;
				break;
			}
		}
							
		return cursoescolhido;
					
	}
	
	private java.sql.Date dataAtual() {
		
		return new java.sql.Date(new Date().getTime()); 
		
	}
	
	private void faturamentoPorCliente(String cpf) {
		
		cliente = home_clientes.buscar(cpf);
		
		double valor_bruto = 0.0;
		
		List<Pagamentos> lista = home_pagamentos.buscarPorCliente(cliente.getIdCliente());
				
		for(Pagamentos pg: lista) {
			
			Cursos curso = home_cursos.buscarPorId(pg.getFk_curso()); 
			String v = curso.getValor().replace(",", ".");
			BigDecimal valor = converteParaBigDecimal(v);
			
			valor_bruto += valor.doubleValue();			
		}
		
		System.out.println("\nCliente: "+cliente.getNome()+""
				+ "\nPagamento no total de: R$ "+formataPreco(""+valor_bruto));
		
	}

	private void faturamento(List<Cursos> lista2) {
		
		double valor_bruto = 0.0;
				
		for(Cursos pg: lista2) {
			 
			String v = pg.getValor().replace(",", ".");
			BigDecimal valor = converteParaBigDecimal(v);
			
			valor_bruto += valor.doubleValue();			
		}
		
		System.out.println("\nO faturamento total é: R$ "+formataPreco(""+valor_bruto));
		
	}
	
	private String formataPreco(String s) {
		
		String aux = s;
		
		if(aux.contains(".")) {				
			aux = aux.replace(".", ",");				
			if(aux.indexOf(",") == aux.length()-1)
				aux += "00";
			else if(aux.indexOf(",") == aux.length()-2)
				aux += "0";
			else if(aux.length() - aux.indexOf(",")>3)
				aux = aux.substring(0, aux.indexOf(",")+3);
		}
		else
			aux += ",00";			
		
		return aux;
	}
	
	private BigDecimal converteParaBigDecimal(String s) {
		
		return new BigDecimal(s);
	}


}
