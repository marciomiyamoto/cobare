package cobare.controle.web.managedbean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class contasMB {
	
	private String contas;
	private boolean flagProdutoComposto;
	private String insumos;

	public String getContas() {
		return contas;
	}

	public void setContas(String contas) {
		this.contas = contas;
	}

	public boolean isFlagProdutoComposto() {
		return flagProdutoComposto;
	}

	public void setFlagProdutoComposto(boolean flagProdutoComposto) {
		this.flagProdutoComposto = flagProdutoComposto;
	}

	public String getInsumos() {
		return insumos;
	}

	public void setInsumos(String insumos) {
		this.insumos = insumos;
	}
	

}
