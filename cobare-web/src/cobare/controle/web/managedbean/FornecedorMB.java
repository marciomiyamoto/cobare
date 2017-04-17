package cobare.controle.web.managedbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import cobare.controle.web.command.ICommand;
import cobare.controle.web.command.impl.AlterarCommand;
import cobare.controle.web.command.impl.ConsultarCommand;
import cobare.controle.web.command.impl.ExcluirCommand;
import cobare.controle.web.command.impl.SalvarCommand;
import cobare.controle.web.command.impl.VisualizarCommand;
import cobare.core.aplicacao.Resultado;
import cobare.dominio.Cidade;
import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Estado;
import cobare.dominio.Fornecedor;

@ManagedBean
public class FornecedorMB {
	
	private int indexBusca;
	private Fornecedor fornecedor;
	private Endereco endereco;
	private Cidade cidade;
	private Estado estado;
	private List<Estado> estados;
	private static Map<String, ICommand> commands;
	private ICommand command;
	
	@PostConstruct
	public void init() {
		indexBusca = 0;
		fornecedor = new Fornecedor();
		endereco = new Endereco();
		cidade = new Cidade();
		estado = new Estado();
		estados = new ArrayList<Estado>();
		
		/* Utilizando o command para chamar a fachada e indexando cada command 
		 * pela operação garantimos que esta servelt atenderá qualquer operação */
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		
		command = commands.get("CONSULTAR");
		Resultado rs = command.execute(estado);
		
		for(int i = 0; i < rs.getEntidades().size(); i++) {
			try {
				estados.add(i, (Estado) rs.getEntidades().get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void limpar() {
		indexBusca = 0;
		cidade = null;
		endereco = null;
		fornecedor = null;
	}
	
	public void salvar() {
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		fornecedor.setEndereco(endereco);
		command = commands.get("SALVAR");
		Resultado rs = command.execute(fornecedor);
		if(rs.getMsg() != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", rs.getMsg()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Fornecedor salvo com sucesso!"));
		}
	}
	
	public void atualizar() {
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		fornecedor.setEndereco(endereco);
		fornecedor.setId(indexBusca);
		command = commands.get("ALTERAR");
		Resultado rs = command.execute(fornecedor);
		if(rs.getMsg() != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", rs.getMsg()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Fornecedor atualizado com sucesso!"));
		}
	}
	
	public void excluir() {
		cidade.setEstado(estado);
		endereco.setCidade(cidade);
		fornecedor.setEndereco(endereco);
		fornecedor.setId(indexBusca);
		command = commands.get("EXCLUIR");
		Resultado rs = command.execute(fornecedor);
		if(rs.getMsg() != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", rs.getMsg()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Fornecedor excluído com sucesso!"));
		}
	}
	
	public void visualizar() {
		command = commands.get("VISUALIZAR");
		fornecedor.setId(indexBusca);
		Resultado rs = command.execute(fornecedor);
		
		if(rs.getEntidades().get(0).getId() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Nenhum fornecedor com o código " + fornecedor.getId()));
		} else {
			fornecedor = (Fornecedor) rs.getEntidades().get(0);
			endereco = fornecedor.getEndereco();
			cidade = fornecedor.getEndereco().getCidade();
	//		estado = fornecedor.getEndereco().getCidade().getEstado();
		}
	}
	
	// GETTERS E SETTERS
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public int getIndexBusca() {
		return indexBusca;
	}

	public void setIndexBusca(int indexBusca) {
		this.indexBusca = indexBusca;
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

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
}
