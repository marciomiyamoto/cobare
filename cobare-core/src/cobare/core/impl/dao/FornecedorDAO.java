package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.Cidade;
import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Estado;
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
//			pst.setInt(1, //COMO PEGAR ID ENDEREÇO???);
					
//			pst.setInt(2, telDao.consultar(fornecedor).getId());
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
	public void excluir(EntidadeDominio entidade) throws SQLException {
		
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
		
		// EXCLUINDO ENDERECO
		sql.append("DELETE endereco ");
		sql.append("WHERE id = (select id_endereco from fornecedor where id = "+fornecedor.getId()+")");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, fornecedor.getId());
			
			pst.executeUpdate();			
			
//			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		// EXCLUINDO TELEFONE
//		telefones = fornecedor.getTelefones();
//		telDao.salvar(telefones.get(0));
		// EXCLUINDO FORNECEDOR
		sql.setLength(0);
		sql.append("DELETE Fornecedor ");
		sql.append("WHERE id = ?");
		
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, fornecedor.getId());
			
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
	public EntidadeDominio visualizar(EntidadeDominio entidade) throws SQLException {

		if(connection == null) {
			abrirConexao();
		}
		Fornecedor forn = (Fornecedor) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select "
				+ "fo.ID, fo.RAZAO_SOCIAL, fo.CNPJ, fo.NOME_FANTASIA, fo.INSC_ESTADUAL, "
				+ "en.RUA as logradouro, en.NUM as numero, en.COMPLEMENTO, en.BAIRRO, "
				+ "ci.NOME as cidade, "
//				+ "es.nome as UF, "
				+ "en.CEP, "
				+ "fo.EMAIL "
				+ "from fornecedor fo join endereco en on fo.id_endereco = en.id "
				+ "join cidade ci on en.id_cidade = ci.id "
//				+ "join estado es on ci.id_estado = es.id "
				+ "where fo.id = " + forn.getId());
		
		try {
			pst = connection.prepareStatement(sql.toString());			
			ResultSet rs = pst.executeQuery();
			
//			Estado estado = new Estado();
			Cidade cidade = new Cidade();
			Endereco endereco = new Endereco();
			Fornecedor fornecedor = new Fornecedor();
			while(rs.next() ) {
//				estado.setNome(rs.getString("estado"));
				cidade.setNome(rs.getString("cidade"));
				endereco.setCidade(cidade);
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				fornecedor.setEndereco(endereco);
				fornecedor.setId(rs.getInt("id"));
				fornecedor.setRazaoSocial(rs.getString("razao_social"));
				fornecedor.setCnpj(rs.getString("cnpj"));
				fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
				fornecedor.setInscEstadual(rs.getString("insc_estadual"));
				fornecedor.setEmail(rs.getString("email"));
			}
			rs.close();
			return fornecedor;
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
		
		return null;
	}

}
