package br.com.cobare.produto.dominio;

import java.util.Date;

import br.com.cobare.entidade.dominio.EntidadeDominio;

public class Produto extends EntidadeDominio {
	
	private String nome;
	private double custoUnitario;
	private double valorVenda;
	private Date dataValidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
