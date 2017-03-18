package br.com.cobare.produto.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cobare.produto.dominio.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public void salvar(Produto produto) {
		ConexaoOracle dataBase = new ConexaoOracle();
		Connection conn = dataBase.getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Produto ");
		sql.append("(id, nome, custoUnitario, valorVenda, dtCadastro, dtValidade) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ? )");
		try( PreparedStatement stmt = conn.prepareStatement(sql.toString()); ) {
			
			stmt.setInt(1, produto.getId());
			stmt.setString(2, produto.getNome() );
			stmt.setDouble(3,  produto.getCustoUnitario());
			stmt.setDouble(4, produto.getValorVenda());
			stmt.setDate(5, new java.sql.Date( produto.getDtCadastro().getTime() ) );
			stmt.setDate(6, new java.sql.Date(produto.getDataValidade().getTime()));
			
			stmt.executeUpdate();
			System.out.println("Salvo!");
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
	}

	@Override
	public List<Produto> listar() {
		ConexaoOracle dataBase = new ConexaoOracle();
		Connection conn = dataBase.getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Produto");
		try( PreparedStatement stmt = conn.prepareStatement(sql.toString()); ) {
			
			ResultSet rs = stmt.executeQuery();
			
			List <Produto> produtos = new ArrayList <>();
			while(rs.next() ) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDtCadastro(rs.getDate("dtCadastro"));
				produto.setNome(rs.getString("nome"));
				produto.setCustoUnitario(rs.getDouble("custoUnitario"));
				produto.setValorVenda(rs.getDouble("valorVenda"));
				produto.setDataValidade(rs.getDate("dtValidade"));
				produtos.add(produto);
			}
			rs.close();
			return produtos;
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
	public void atualizar(Produto produto) {
		ConexaoOracle dataBase = new ConexaoOracle();
		Connection conn = dataBase.getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Produto ");
		sql.append("SET ID= ?, ");
		sql.append("NOME = ?, ");
		sql.append("CUSTOUNITARIO = ?, ");
		sql.append("VALORVENDA = ?, ");
		sql.append("DTCADASTRO = ?, ");
		sql.append("DTVALIDADE = ? ");
		sql.append("WHERE ID = ?");
		
		try( PreparedStatement stmt = conn.prepareStatement(sql.toString()); ) {
			stmt.setInt(1, produto.getId());
			stmt.setString(2, produto.getNome() );
			stmt.setDouble(3,  produto.getCustoUnitario());
			stmt.setDouble(4, produto.getValorVenda());
			stmt.setDate(5, new java.sql.Date( produto.getDtCadastro().getTime() ) );
			stmt.setDate(6, new java.sql.Date(produto.getDataValidade().getTime() ) );
			stmt.setInt(7, produto.getId());
			int contador = stmt.executeUpdate();
			System.out.println("Atualizado! Linhas atualizadas: " + contador);
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
	}

	@Override
	public void excluir(Produto produto) {
		ConexaoOracle database = new ConexaoOracle();
		Connection conn = database.getConnection();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Produto ");
		sql.append("WHERE id = ? ");
		
		try( PreparedStatement stmt = conn.prepareStatement(sql.toString()); ) {
			stmt.setInt(1, produto.getId());
			stmt.executeUpdate();
			System.out.println("Excluido!");
		}
		catch(SQLException ex) {
			System.out.println("\n--- SQLException ---\n");
			while( ex != null ) {
				System.out.println("Mensagem: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		}
	}

}
