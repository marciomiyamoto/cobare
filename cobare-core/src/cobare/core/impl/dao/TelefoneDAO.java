package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Telefone;

public class TelefoneDAO extends AbstractJdbcDAO {
	
	protected TelefoneDAO(String tabela, String idTabela) {
		super("Telefone", "id");
	}
	
	public TelefoneDAO(Connection conn) {
		super(conn, "Telefone", "id");
	}
	
	public TelefoneDAO() {
		super("Telefone", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Telefone tel = (Telefone) entidade;
		StringBuilder sql = new StringBuilder();
		
		// SALVANDO TELEFONE
		sql.append("INSERT INTO Telefone ");
		sql.append("(numero) ");
		sql.append("VALUES(?)");
		
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1,  tel.getNumero());
			
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
					if(ctrlTransacao)
						connection.close();
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
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Telefone tel = (Telefone)entidade;
		Telefone telefone = new Telefone();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM telefone ");
		sql.append("WHERE id = ? ");
		try{
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, tel.getId());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				telefone.setId(rs.getInt("id"));
				telefone.setDtCadastro(rs.getDate("dtCadastro"));
				telefone.setNumero(rs.getInt("numero"));
			}
			return telefone;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ctrlTransacao) {
				try {
					pst.close();
					if(ctrlTransacao)
						connection.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

}
