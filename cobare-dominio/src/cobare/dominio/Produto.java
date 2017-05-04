package cobare.dominio;

import java.util.Date;
import java.util.List;

public class Produto extends EntidadeDominio {
	
	private UnidadeMedida unidadeMedida;
	private CategoriaProduto categoria;
	private List <Produto> produtoComposto;
	private String nome;
	private double qtde;
	private double custoUnitario;
	private double valorVenda;
	private Date dataValidade;
	
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	public List<Produto> getProdutoComposto() {
		return produtoComposto;
	}
	public void setProdutoComposto(List<Produto> produtoComposto) {
		this.produtoComposto = produtoComposto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getQtde() {
		return qtde;
	}
	public void setQtde(double qtde) {
		this.qtde = qtde;
	}
	public double getCustoUnitario() {
		return custoUnitario;
	}
	public void setCustoUnitario(double custoUnitario) {
		this.custoUnitario = custoUnitario;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
