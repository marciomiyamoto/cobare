package cobare.controle.web.managedbean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import cobare.controle.web.command.ICommand;
import cobare.controle.web.command.impl.AlterarCommand;
import cobare.controle.web.command.impl.ConsultarCommand;
import cobare.controle.web.command.impl.ExcluirCommand;
import cobare.controle.web.command.impl.SalvarCommand;
import cobare.controle.web.command.impl.VisualizarCommand;
import cobare.dominio.Endereco;
import cobare.dominio.Fornecedor;

@ManagedBean
public class FornecedorMB {
	
	private Fornecedor fornecedor;
	private Endereco endereco;
	private static Map<String, ICommand> commands;
	
	@PostConstruct
	public void init() {
		
		fornecedor = new Fornecedor();
		endereco = new Endereco();
		/* Utilizando o command para chamar a fachada e indexando cada command 
		 * pela operação garantimos que esta servelt atenderá qualquer operação */
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void salvar() {
		fornecedor.setEndereco(endereco);
		ICommand command = commands.get("SALVAR");
		command.execute(fornecedor);
	}

}
