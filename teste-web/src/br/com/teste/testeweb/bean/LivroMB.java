package br.com.teste.testeweb.bean;

import javax.faces.bean.ManagedBean;

import br.com.cobare.produto.core.dao.ProdutoDAO;
import br.com.cobare.produto.dominio.Produto;

@ManagedBean
public class LivroMB {
	
	private Produto produto = new Produto();
	private Livro livro = new Livro();
	private ProdutoDAO produtoDao = new ProdutoDAO();
		
	public Livro getLivro() {
		return livro;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
	}
	
	
	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}

	public void gravarProduto() {
		produtoDao.salvar(produto);
		System.out.println("Produto " + produto.getNome() + " salvo!");
	}

}
