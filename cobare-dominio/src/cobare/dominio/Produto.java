package cobare.dominio;

import java.util.Date;
import java.util.List;

public class Produto extends EntidadeDominio {
	
	private UnidadeMedida unidadeMedida;
	private CategoriaProduto categoria;
	private boolean produtoComposto;
	private String nome;
	private double qtde;
	private double custoUnitario;
	private double valorVenda;
	private Date dataValidade;
	private List <Produto> insumos;
	
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
	public boolean getProdutoComposto() {
		return produtoComposto;
	}
	public void setProdutoComposto(boolean produtoComposto) {
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
	public List<Produto> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<Produto> insumos) {
		this.insumos = insumos;
	}
}
