package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;

public class EnderecoDAO extends AbstractJdbcDAO {
	
	protected EnderecoDAO(String tabela, String idTabela) {
		super("Endereco", "id");
	}
	
	public EnderecoDAO(Connection conn) {
		super(conn, "Endereco", "id");
	}
	
	public EnderecoDAO() {
		super("Endereco", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {

		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Endereco end = (Endereco) entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO Endereco(cidade, estado, ");
		sql.append("logradouro, numero, cep, complemento, bairro, dtCadastro) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, end.getCidade().getNome());
			pst.setString(2, end.getCidade().getEstado().getNome());
			pst.setString(3, end.getLogradouro());
			pst.setString(4, end.getNumero());
			pst.setString(5, end.getCep());
			pst.setString(6, end.getComplento());
			pst.setString(7, end.getBairro());
			Timestamp dtCadastro = new Timestamp(end.getDtCadastro().getTime());
			pst.setTimestamp(8, dtCadastro);
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
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Endereco end = (Endereco) entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE Endereco SET(");
		sql.append("cidade=?, ");
		sql.append("estado=?, ");
		sql.append("logradouro=?, ");
		sql.append("numero=?, ");
		sql.append("cep=?" );
		sql.append("complemento=?" );
		sql.append("bairro=?" );
		sql.append("WHERE idEndereco=?");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, end.getCidade().getNome());
			pst.setString(2, end.getCidade().getEstado().getNome());
			pst.setString(3, end.getLogradouro());
			pst.setString(4, end.getNumero());
			pst.setString(5, end.getCep());
			pst.setString(6, end.getComplento());
			pst.setString(7, end.getBairro());
			pst.setInt(8, end.getId());
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
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Endereco end = (Endereco)entidade;
		List<EntidadeDominio> enderecos = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM endereco ");
		sql.append("WHERE Cidade = ? ");
		sql.append("OR estado=?, ");
		sql.append("OR logradouro=?, ");			
		sql.append("OR cep=?" );
		sql.append("OR bairro=?" );
		sql.append("OR dtCadastro=?" );
		try{
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, end.getCidade().getNome());
			pst.setString(2, end.getCidade().getEstado().getNome());
			pst.setString(3, end.getLogradouro());
			pst.setString(4, end.getCep());
			pst.setString(5, end.getBairro());
			pst.setDate(6, new java.sql.Date(end.getDtCadastro().getTime()));
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setDtCadastro(rs.getDate("dtCadastro"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.getCidade().setNome(rs.getString("cidade"));
				endereco.getCidade().getEstado().setNome(rs.getString("estado"));
				endereco.setComplento(rs.getString("complemento"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				enderecos.add(endereco);
			}
			return enderecos;
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

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
