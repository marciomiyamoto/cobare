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
import cobare.dominio.Estado;

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
		int idEstado = 0;
		int idCidade = 0;
		StringBuilder sql = new StringBuilder();
		
		// SALVANDO ESTADO
		sql.append("INSERT INTO Estado ");
		sql.append("(nome) ");
		sql.append("VALUES(?)");
		
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
			pst.setString(1,  end.getCidade().getEstado().getNome());
			
			pst.executeUpdate();
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				idEstado = generatedKeys.getInt(1);
			}
			
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		// SALVANDO CIDADE
		sql.setLength(0);
		sql.append("INSERT INTO Cidade ");
		sql.append("(id_estado, nome) ");
		sql.append("VALUES(?, ?)");
		
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
			pst.setInt(1, idEstado);
			pst.setString(2,  end.getCidade().getNome());
			
			pst.executeUpdate();
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
			     idCidade = generatedKeys.getInt(1);
			}
			
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		// SALVANDO ENDERECO
		sql.setLength(0);
		sql.append("INSERT INTO Endereco ");
		sql.append("(id_cidade, rua, num, cep, complemento, bairro) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?)");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, idCidade);
			pst.setString(2, end.getLogradouro());
			pst.setString(3, end.getNumero());
			pst.setString(4, end.getCep());
			pst.setString(5, end.getComplemento());
			pst.setString(6, end.getBairro());
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
	public void excluir(EntidadeDominio entidade) throws SQLException {
		
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Endereco end = (Endereco) entidade;
//		int idEstado = 0;
//		int idCidade = 0;
		StringBuilder sql = new StringBuilder();
		
//		sql.append("DELETE ENDERECO WHERE ID = ?")
		
		// EXCLUINDO ESTADO
//		sql.append("INSERT INTO Estado ");
//		sql.append("(nome) ");
//		sql.append("VALUES(?)");
//		
//		try {
//			connection.setAutoCommit(false);
//			
//			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1,  end.getId());
//			
//			pst.executeUpdate();
//			ResultSet generatedKeys = pst.getGeneratedKeys();
////			if (null != generatedKeys && generatedKeys.next()) {
////				idEstado = generatedKeys.getInt(1);
////			}
//			
//			connection.commit();
//		} catch(SQLException e) {
//			try {
//				connection.rollback();
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} 
//		
//		// SALVANDO CIDADE
//		sql.setLength(0);
//		sql.append("INSERT INTO Cidade ");
//		sql.append("(id_estado, nome) ");
//		sql.append("VALUES(?, ?)");
//		
//		try {
//			connection.setAutoCommit(false);
//			
//			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
//			pst.setInt(1, idEstado);
//			pst.setString(2,  end.getCidade().getNome());
//			
//			pst.executeUpdate();
//			ResultSet generatedKeys = pst.getGeneratedKeys();
//			if (null != generatedKeys && generatedKeys.next()) {
//			     idCidade = generatedKeys.getInt(1);
//			}
//			
//			connection.commit();
//		} catch(SQLException e) {
//			try {
//				connection.rollback();
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} 
//		
		// EXCLUINDO ENDERECO
		sql.setLength(0);
		sql.append("Delete Endereco ");
		sql.append("(WHERE id = ?");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, end.getId());
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
			pst.setString(6, end.getComplemento());
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
	public EntidadeDominio visualizar(EntidadeDominio entidade) throws SQLException {
//		if(connection == null) {
//			abrirConexao();
//		}
//		PreparedStatement pst = null;
//		Endereco end = (Endereco)entidade;
//		List<EntidadeDominio> enderecos = new ArrayList<>();
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM endereco ");
//		sql.append("WHERE Cidade = ? ");
//		sql.append("OR estado=?, ");
//		sql.append("OR logradouro=?, ");			
//		sql.append("OR cep=?" );
//		sql.append("OR bairro=?" );
//		sql.append("OR dtCadastro=?" );
//		try{
//			pst = connection.prepareStatement(sql.toString());
//			pst.setString(1, end.getCidade().getNome());
//			pst.setString(2, end.getCidade().getEstado().getNome());
//			pst.setString(3, end.getLogradouro());
//			pst.setString(4, end.getCep());
//			pst.setString(5, end.getBairro());
//			pst.setDate(6, new java.sql.Date(end.getDtCadastro().getTime()));
//			ResultSet rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				Endereco endereco = new Endereco();
//				endereco.setId(rs.getInt("id"));
//				endereco.setDtCadastro(rs.getDate("dtCadastro"));
//				endereco.setBairro(rs.getString("bairro"));
//				endereco.setCep(rs.getString("cep"));
//				endereco.getCidade().setNome(rs.getString("cidade"));
//				endereco.getCidade().getEstado().setNome(rs.getString("estado"));
//				endereco.setComplemento(rs.getString("complemento"));
//				endereco.setLogradouro(rs.getString("logradouro"));
//				endereco.setNumero(rs.getString("numero"));
//				enderecos.add(endereco);
//			}
//			return enderecos;
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if(ctrlTransacao) {
//				try {
//					pst.close();
//					if(ctrlTransacao)
//						connection.close();
//				} catch(SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
//		if(connection == null) {
//			abrirConexao();
//		}
//		PreparedStatement pst = null;
//		Endereco end = (Endereco)entidade;
//		Endereco endereco = new Endereco();
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT * FROM endereco ");
//		sql.append("WHERE id = ? ");
//		try{
//			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, end.getId());
//			ResultSet rs = pst.executeQuery();
//			
//			while(rs.next()) {
//				endereco.setId(rs.getInt("id"));
//				endereco.setDtCadastro(rs.getDate("dtCadastro"));
//				endereco.setBairro(rs.getString("bairro"));
//				endereco.setCep(rs.getString("cep"));
//				endereco.getCidade().setNome(rs.getString("cidade"));
//				endereco.getCidade().getEstado().setNome(rs.getString("estado"));
//				endereco.setComplemento(rs.getString("complemento"));
//				endereco.setLogradouro(rs.getString("logradouro"));
//				endereco.setNumero(rs.getString("num"));
//			}
//			return endereco;
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if(ctrlTransacao) {
//				try {
//					pst.close();
//					if(ctrlTransacao)
//						connection.close();
//				} catch(SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		return null;
	}

}
