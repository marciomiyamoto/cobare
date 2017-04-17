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
import cobare.core.aplicacao.Resultado;
import cobare.dominio.Cidade;
import cobare.dominio.Endereco;
import cobare.dominio.Estado;
import cobare.dominio.Fornecedor;

@ManagedBean
public class FornecedorMB {
	
	private String buscar;
	private Fornecedor fornecedor;
	private Endereco endereco;
	private Cidade cidade;
	private Estado estado;
	private static Map<String, ICommand> commands;
	
	@PostConstruct
	public void init() {
		buscar = new String();
		fornecedor = new Fornecedor();
		endereco = new Endereco();
		cidade = new Cidade();
		estado = new Estado();
		
		/* Utilizando o command para chamar a fachada e indexando cada command 
		 * pela operação garantimos que esta servelt atenderá qualquer operação */
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
	}
	
	public void limpar() {
		buscar = "";
		cidade = null;
		endereco = null;
		fornecedor = null;
	}
	
	public void salvar() {
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		fornecedor.setEndereco(endereco);
		ICommand command = commands.get("SALVAR");
		Resultado rs = command.execute(fornecedor);
	}
	
	public void excluir() {
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		fornecedor.setEndereco(endereco);
		ICommand command = commands.get("EXCLUIR");
		Resultado rs = command.execute(fornecedor);
	}
	
	public void visualizar() {
		ICommand command = commands.get("VISUALIZAR");
		Resultado rs = command.execute(fornecedor);
	
		fornecedor = (Fornecedor) rs.getEntidades().get(0);
		endereco = fornecedor.getEndereco();
		cidade = fornecedor.getEndereco().getCidade();
//		estado = fornecedor.getEndereco().getCidade().getEstado();
	}
	
	// GETTERS E SETTERS
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}


}
