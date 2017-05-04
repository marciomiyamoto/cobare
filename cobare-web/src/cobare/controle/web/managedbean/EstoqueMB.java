package cobare.controle.web.managedbean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class EstoqueMB {
	
	private String[] produtos = {"1","2","3","4"};

	public String[] getProdutos() {
		return produtos;
	}

	public void setProdutos(String[] produtos) {
		this.produtos = produtos;
	}
	
	

}
