package br.com.teste.testeweb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.cobare.produto.core.dao.ProdutoDAO;
import br.com.cobare.produto.dominio.Produto;

@ManagedBean
public class produtoMB {
	
	private Produto produto;
	private List <Produto> produtos;
	private ProdutoDAO produtoDao;
	
	@PostConstruct
	public void init() {
		produto = new Produto();
		produtos = new ArrayList<Produto>();
		produtoDao = new ProdutoDAO();
		
		produtos = produtoDao.listar();
	}
	
	public void gravarProduto() {
		produtoDao.salvar(produto);
	}
	
	public void apagar(Produto produto) {
		produtoDao.excluir(produto);
	}
	
	public void callNovo() {
		this.produto = new Produto();
	}
	
	public void callEditar(Produto produto) {
		this.produto = produto;
//		return "produtoDialog";
	}
	
	public void editar() {
		produtoDao.atualizar(produto);
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}
	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}
	
	

}
