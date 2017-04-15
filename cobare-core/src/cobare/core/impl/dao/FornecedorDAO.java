package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Fornecedor;
import cobare.dominio.Telefone;

public class FornecedorDAO extends AbstractJdbcDAO {
	
	protected FornecedorDAO(String tabela, String idTabela) {
		super("Fornecedor", "id");
	}
	
	public FornecedorDAO(Connection conn) {
		super(conn, "Fornecedor", "id");
	}
	
	public FornecedorDAO() {
		super("Fornecedor", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Fornecedor fornecedor = (Fornecedor) entidade;
		EnderecoDAO endDao = new EnderecoDAO();
		TelefoneDAO telDao = new TelefoneDAO();
		Endereco endereco = new Endereco();
		Telefone telefone = new Telefone();
		List<Telefone> telefones = new ArrayList<Telefone>();
		StringBuilder sql = new StringBuilder();
		
		// SALVANDO ENDERECO
		endereco = fornecedor.getEndereco();
		endDao.salvar(endereco);
		// SALVANDO TELEFONE
		telefones = fornecedor.getTelefones();
		telDao.salvar(telefones.get(0));
		// SALVANDO FORNECEDOR
		sql.append("INSERT INTO Fornecedor ");
		sql.append("(id_endereco, "
				+ "idtelefone, "
				+ "razao_social, "
				+ "cnpj, "
				+ "nome_fantasia, "
				+ "INSC_ESTADUAL, "
				+ "EMAIL) ");
		sql.append("VALUES(?,?,?,?,?,?,?)");
		
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, //COMO PEGAR ID ENDEREÇO???);
					
			pst.setInt(2, telDao.consultar(fornecedor).getId());
			pst.setString(3, fornecedor.getRazaoSocial());
			pst.setString(4, fornecedor.getCnpj());
			pst.setString(5, fornecedor.getNomeFantasia());
			pst.setString(6, fornecedor.getInscEstadual());
			pst.setString(7, fornecedor.getEmail());
			
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
		// TODO Auto-generated method stub
		return null;
	}

}
