package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cobare.dominio.Cliente;
import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;

public class ClienteDAO extends AbstractJdbcDAO{

	protected ClienteDAO(String tabela, String idTabela) {
		super("cliente", "id");
	}
	public ClienteDAO(Connection conn) {
		super(conn, "cliente", "id");
	}
	public ClienteDAO() {
		super("cliente", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Cliente cli = (Cliente)entidade;
		Endereco end = cli.getEndereco();
		EnderecoDAO endDao = new EnderecoDAO();
		try {
			connection.setAutoCommit(false);
			endDao.connection = connection;
			endDao.ctrlTransacao = false;
			endDao.salvar(end);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cliente ");
			sql.append("(nome, cpf, idEndereco, dtCadastro) VALUES ");
			sql.append("(?, ?, ?, ?);");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cli.getNome());
			pst.setString(2, cli.getCpf());
			pst.setInt(3, end.getId());
			pst.setDate(4, new java.sql.Date(cli.getDtCadastro().getTime()));
			pst.executeUpdate();
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(ctrlTransacao) {
				try {
					pst.close();
					if(ctrlTransacao) {
						connection.close();
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntidadeDominio visualizar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
