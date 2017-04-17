package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.EntidadeDominio;
import cobare.dominio.Estado;

public class EstadoDAO extends AbstractJdbcDAO {

	protected EstadoDAO(String tabela, String idTabela) {
		super("Estado", "id");
	}
	
	public EstadoDAO(Connection conn) {
		super(conn, "Estado", "id");
	}
	
	public EstadoDAO() {
		super("Estado", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
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

		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from estado");
		
		try {
			pst = connection.prepareStatement(sql.toString());			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> estados = new ArrayList<EntidadeDominio>()	;
			while(rs.next() ) {
				Estado estado = new Estado();
				estado.setId(rs.getInt("id"));
				estado.setNome(rs.getString("nome"));
				estados.add(estado);
			}
			rs.close();
			return estados;
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
