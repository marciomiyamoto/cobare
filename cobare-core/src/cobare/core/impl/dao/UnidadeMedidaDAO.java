package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.EntidadeDominio;
import cobare.dominio.UnidadeMedida;

public class UnidadeMedidaDAO extends AbstractJdbcDAO{

	protected UnidadeMedidaDAO(String tabela, String idTabela) {
		super("unidadeMedida", "id");
	}
	public UnidadeMedidaDAO(Connection conn) {
		super(conn, "unidadeMedida", "id");
	}
	public UnidadeMedidaDAO() {
		super("unidadeMedida", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		UnidadeMedida un = (UnidadeMedida) entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Unidade_medida ");
			sql.append("(unidade) VALUES ");
			sql.append("(?);");
			
			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
			pst.setString(1, un.getUnidade());
			pst.executeUpdate();
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
			     un.setId(generatedKeys.getInt(1));
			}
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
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		UnidadeMedida un = (UnidadeMedida) entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Unidade_medida ");
			sql.append("SET unidade = ? ");
			sql.append("WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, un.getUnidade());
			pst.setInt(2, un.getId());
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
	public EntidadeDominio visualizar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		UnidadeMedida un = (UnidadeMedida) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Unidade_medida ");
		sql.append("WHERE id = ?");
		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, un.getId());
			ResultSet rs = pst.executeQuery();
			
			UnidadeMedida unidadeMedida = new UnidadeMedida();
			while(rs.next() ) {
				unidadeMedida.setId(rs.getInt("id"));
				unidadeMedida.setDtCadastro(rs.getDate("datainsercao"));
				unidadeMedida.setUnidade(rs.getString("unidade"));
			}
			rs.close();
			return unidadeMedida;
		} catch (SQLException ex) {
			System.out.println("\n--- SQLException ---\n");
			while( ex != null ) {
				System.out.println("Mensagem: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		UnidadeMedida un = (UnidadeMedida) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Unidade_medida ");
//		sql.append("WHERE id = ?");
		try {
			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, un.getId());
			ResultSet rs = pst.executeQuery();
			
			List<EntidadeDominio> unidades = new ArrayList<EntidadeDominio>();
			while(rs.next() ) {
				UnidadeMedida unidadeMedida = new UnidadeMedida();
				unidadeMedida.setId(rs.getInt("id"));
				unidadeMedida.setDtCadastro(rs.getDate("datainsercao"));
				unidadeMedida.setUnidade(rs.getString("unidade"));
				unidades.add(unidadeMedida);
			}
			rs.close();
			return unidades;
		} catch (SQLException ex) {
			System.out.println("\n--- SQLException ---\n");
			while( ex != null ) {
				System.out.println("Mensagem: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		}
		return null;
	}

}
