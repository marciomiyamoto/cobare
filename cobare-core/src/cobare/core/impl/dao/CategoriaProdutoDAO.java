package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.CategoriaProduto;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.UnidadeMedida;

public class CategoriaProdutoDAO extends AbstractJdbcDAO {

	protected CategoriaProdutoDAO(String tabela, String idTabela) {
		super("categoriaProduto", "id");
	}
	public CategoriaProdutoDAO(Connection conn) {
		super(conn, "categoriaProduto", "id");
	}
	public CategoriaProdutoDAO() {
		super("categoriaProduto", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		CategoriaProduto cat = (CategoriaProduto) entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Categoria_produto ");
			sql.append("(nome) VALUES ");
			sql.append("(?);");
			
			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
			pst.setString(1, cat.getNome());
			pst.executeUpdate();
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
			     cat.setId(generatedKeys.getInt(1));
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
		CategoriaProduto cat = (CategoriaProduto) entidade;
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE Categoria_produto ");
			sql.append("SET nome = ? ");
			sql.append("WHERE id = ?;");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cat.getNome());
			pst.setInt(2, cat.getId());
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
		CategoriaProduto cat = (CategoriaProduto) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Categoria_produto ");
		sql.append("WHERE id = ?");
		try {
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cat.getId());
			ResultSet rs = pst.executeQuery();
			
			CategoriaProduto catProduto = new CategoriaProduto();
			while(rs.next() ) {
				catProduto.setId(rs.getInt("id"));
				catProduto.setDtCadastro(rs.getDate("datainsercao"));
				catProduto.setNome(rs.getString("nome"));
			}
			rs.close();
			return catProduto;
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
		CategoriaProduto cat = (CategoriaProduto) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Categoria_produto ");
//		sql.append("WHERE id = ?");
		try {
			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, cat.getId());
			ResultSet rs = pst.executeQuery();
			
			List<EntidadeDominio> catProdutos = new ArrayList<EntidadeDominio>(); 
			while(rs.next() ) {
				CategoriaProduto catProduto = new CategoriaProduto();
				catProduto.setId(rs.getInt("id"));
				catProduto.setDtCadastro(rs.getDate("datainsercao"));
				catProduto.setNome(rs.getString("nome"));
				catProdutos.add(catProduto);
			}
			rs.close();
			return catProdutos;
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
