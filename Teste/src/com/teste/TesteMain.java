package com.teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.cobare.produto.core.dao.ProdutoDAO;
import br.com.cobare.produto.dominio.Produto;

public class TesteMain {
	public static void main(String[] args) {
		
		Produto cerveja = new Produto();
		
			salvar(cerveja);
			listar();
			
			atualizar(cerveja);
			listar();
			
			excluir(cerveja);
			listar();
	}

	private static void excluir(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		produto.setId(2);
		dao.excluir(produto);
	}
	private static void atualizar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		produto.setId(2);
		produto.setNome("Original");
		produto.setCustoUnitario(3.25);
		produto.setValorVenda(7.40);
		dao.atualizar(produto);
	}

	private static void listar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos;
		produtos = dao.listar();
		Iterator<Produto> i = produtos.iterator();
		while(i.hasNext()) {
			Produto produto = i.next();
			System.out.println("ID: " + produto.getId());
			System.out.println("Data de cadastro: " + produto.getDtCadastro());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Custo Unitario: " + produto.getCustoUnitario());
			System.out.println("Valor de Venda: " + produto.getValorVenda());
			System.out.println("Data de Validade: "  + produto.getDataValidade());
		}
		System.out.println("Fim da lista");
	}

	private static void salvar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		produto.setId(1);
		produto.setNome("Skol");
		produto.setCustoUnitario(2.59);
		produto.setValorVenda(5.90);
		produto.setDtCadastro(converteData("18/02/2017"));
		produto.setDataValidade(converteData("01/12/2017"));
		dao.salvar(produto);
	}

	private static Date converteData(String dataString ) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = (Date) formatter.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
}
