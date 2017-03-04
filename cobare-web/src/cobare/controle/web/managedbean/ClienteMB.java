package cobare.controle.web.managedbean;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import cobare.core.impl.dao.ClienteDAO;
import cobare.dominio.Cliente;
import cobare.dominio.Endereco;

@ManagedBean(name="clienteMB")
public class ClienteMB {
	
	private Cliente cliente;
	private Endereco endereco;
	private ClienteDAO cliDao;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		endereco = new Endereco();
		cliDao = new ClienteDAO();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void salvar() {
		cliente.setEndereco(endereco);
		try {
			cliDao.salvar(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		cliente.setEndereco(endereco);
		try {
			cliDao.excluir(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
