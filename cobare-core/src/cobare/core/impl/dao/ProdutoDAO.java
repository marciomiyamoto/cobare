package cobare.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.CategoriaProduto;
import cobare.dominio.Cidade;
import cobare.dominio.Endereco;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Estado;
import cobare.dominio.Fornecedor;
import cobare.dominio.Produto;
import cobare.dominio.Telefone;
import cobare.dominio.UnidadeMedida;

public class ProdutoDAO extends AbstractJdbcDAO {

	protected ProdutoDAO(String tabela, String idTabela) {
		super("Produto", "id");
	}
	
	public ProdutoDAO(Connection conn) {
		super(conn, "Produto", "id");
	}
	
	public ProdutoDAO() {
		super("Produto", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		if(connection == null) {
			abrirConexao();
		}
		PreparedStatement pst = null;
		Produto produto = (Produto) entidade;
		StringBuilder sql = new StringBuilder();
		
		// SALVANDO PRODUTO
		sql.setLength(0);
		sql.append("INSERT INTO Produto ");
		sql.append("(id_un_medida, ");
		sql.append("id_cat_produto, ");
		sql.append("nome, ");
		sql.append("qtde, ");
		sql.append("custo_un, ");
		sql.append("valor_venda, ");
		sql.append("data_val,");
		sql.append("produto_composto) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?)");
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
			pst.setInt(1, produto.getUnidadeMedida().getId());
			pst.setInt(2, produto.getCategoria().getId());
			pst.setString(3, produto.getNome());
			pst.setDouble(4, produto.getQtde());
			pst.setDouble(5, produto.getCustoUnitario());
			pst.setDouble(6, produto.getValorVenda());
			pst.setDate(7, new java.sql.Date(produto.getDataValidade().getTime()));
			pst.setBoolean(8, produto.getProdutoComposto());
			pst.executeUpdate();			
			
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
			     produto.setId(generatedKeys.getInt(1));
			}
			// SALVANDO INSUMOS
			if(produto.getProdutoComposto() && !produto.getInsumos().isEmpty()) {
				while(produto.getInsumos().iterator().hasNext()) {
					sql.setLength(0);
					sql.append("INSERT INTO Produto_composto ");
					sql.append("(id_produto_composto, ");
					sql.append("id_produto_insumo) ");
					sql.append("VALUES(?,?)");
					try {
						connection.setAutoCommit(false);
						
						pst = connection.prepareStatement(sql.toString(), new String[] {"id"});
						pst.setInt(1, produto.getId());
						pst.setInt(2, produto.getInsumos().iterator().next().getId());
						pst.executeUpdate();		
						
						generatedKeys = pst.getGeneratedKeys();
						if (null != generatedKeys && generatedKeys.next()) {
						     produto.getInsumos().iterator().next().setId(generatedKeys.getInt(1));
						}
					} catch(SQLException e) {
						try {
							connection.rollback();
						} catch(SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
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
//		
//		if(connection == null) {
//			abrirConexao();
//		}
//		PreparedStatement pst = null;
//		Fornecedor fornecedor = (Fornecedor) entidade;
//		StringBuilder sql = new StringBuilder();
//		
//		// EXCLUINDO ENDERECO
//		sql.append("DELETE endereco ");
//		sql.append("WHERE id = (select id_endereco from fornecedor where id = "+fornecedor.getId()+")");
//		try {
//			connection.setAutoCommit(false);
//			pst = connection.prepareStatement(sql.toString());
//			pst.executeUpdate();			
//		} catch(SQLException e) {
//			try {
//				connection.rollback();
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//		// EXCLUINDO TELEFONE
////		telefones = fornecedor.getTelefones();
////		telDao.salvar(telefones.get(0));
//		// EXCLUINDO FORNECEDOR
//		sql.setLength(0);
//		sql.append("DELETE Fornecedor ");
//		sql.append("WHERE id = ?");
//		
//		try {
//			connection.setAutoCommit(false);
//			
//			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, fornecedor.getId());
//			pst.executeUpdate();			
//			connection.commit();
//		} catch(SQLException e) {
//			try {
//				connection.rollback();
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
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
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
//		
//		if(connection == null) {
//			abrirConexao();
//		}
//		PreparedStatement pst = null;
//		Fornecedor fornecedor = (Fornecedor) entidade;
//		EnderecoDAO endDao = new EnderecoDAO();
//		TelefoneDAO telDao = new TelefoneDAO();
//		Endereco endereco = new Endereco();
//		Telefone telefone = new Telefone();
//		List<Telefone> telefones = new ArrayList<Telefone>();
//		StringBuilder sql = new StringBuilder();
//		
//		// ATUALIZANDO ENDERECO
//		
//		sql.append("Select id_endereco from fornecedor where id = ?");
//		pst = connection.prepareStatement(sql.toString());
//		pst.setInt(1, fornecedor.getId());
//		ResultSet rs = pst.executeQuery();
//		int idEnd = 0;
//		while(rs.next()) {
//			idEnd = rs.getInt("id_endereco");
//		}
//		endereco = fornecedor.getEndereco();
//		endereco.setId(idEnd);
//		endDao.alterar(endereco);
//		
//		// ATUALIZANDO TELEFONE
////		telefones = fornecedor.getTelefones();
////		telDao.salvar(telefones.get(0));
////		sql.setLength(0);
////		sql.append("Select id_endereco.nextval from dual");
////		pst = connection.prepareStatement(sql.toString());
////		ResultSet rs2 = pst.executeQuery();
////		int idTel = 0;
////		while(rs.next()) {
////			idTel = rs2.getInt("nextval") -1;
////		}
//		
//		// ATUALIZANDO FORNECEDOR
//		sql.setLength(0);
//		sql.append("UPDATE Fornecedor SET ");
//		sql.append("razao_social = ?, ");
//		sql.append("cnpj = ?, ");
//		sql.append("nome_fantasia = ?,");
//		sql.append("insc_estadual = ?, ");
//		sql.append("email = ? ");
//		sql.append("WHERE id = ?");
//		try {
//			connection.setAutoCommit(false);
//			pst = connection.prepareStatement(sql.toString());
//			pst.setString(1, fornecedor.getRazaoSocial());
//			pst.setString(2, fornecedor.getCnpj());
//			pst.setString(3, fornecedor.getNomeFantasia());
//			pst.setString(4, fornecedor.getInscEstadual());
//			pst.setString(5, fornecedor.getEmail());
//			pst.setInt(6, fornecedor.getId());
//			pst.executeUpdate();			
//			connection.commit();
//		} catch(SQLException e) {
//			try {
//				connection.rollback();
//			} catch(SQLException e1) {
//				e1.printStackTrace();
//			}
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
//
	}

	@Override
	public EntidadeDominio visualizar(EntidadeDominio entidade) throws SQLException {
//
//		if(connection == null) {
//			abrirConexao();
//		}
//		Fornecedor forn = (Fornecedor) entidade;
//		PreparedStatement pst = null;
//		StringBuilder sql = new StringBuilder();
//		sql.append("select "
//				+ "fo.ID, fo.RAZAO_SOCIAL, fo.CNPJ, fo.NOME_FANTASIA, fo.INSC_ESTADUAL, "
//				+ "en.RUA as logradouro, en.NUM as numero, en.COMPLEMENTO, en.BAIRRO, "
//				+ "ci.NOME as cidade, "
//				+ "es.id as ID_Estado, "
//				+ "es.nome as UF, "
//				+ "en.CEP, "
//				+ "fo.EMAIL "
//				+ "from fornecedor fo join endereco en on fo.id_endereco = en.id "
//				+ "join cidade ci on en.id_cidade = ci.id "
//				+ "join estado es on ci.id_estado = es.id "
//				+ "where fo.id = " + forn.getId());
//		
//		try {
//			pst = connection.prepareStatement(sql.toString());			
//			ResultSet rs = pst.executeQuery();
//			
//			Estado estado = new Estado();
//			Cidade cidade = new Cidade();
//			Endereco endereco = new Endereco();
//			Fornecedor fornecedor = new Fornecedor();
//			while(rs.next() ) {
//				estado.setId(rs.getInt("ID_Estado"));
//				estado.setNome(rs.getString("UF"));
//				cidade.setEstado(estado);
//				cidade.setNome(rs.getString("cidade"));
//				endereco.setCidade(cidade);
//				endereco.setLogradouro(rs.getString("logradouro"));
//				endereco.setNumero(rs.getString("numero"));
//				endereco.setComplemento(rs.getString("complemento"));
//				endereco.setBairro(rs.getString("bairro"));
//				endereco.setCep(rs.getString("cep"));
//				fornecedor.setEndereco(endereco);
//				fornecedor.setId(rs.getInt("id"));
//				fornecedor.setRazaoSocial(rs.getString("razao_social"));
//				fornecedor.setCnpj(rs.getString("cnpj"));
//				fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
//				fornecedor.setInscEstadual(rs.getString("insc_estadual"));
//				fornecedor.setEmail(rs.getString("email"));
//			}
//			rs.close();
//			return fornecedor;
//		} catch (SQLException ex) {
//			System.out.println("\n--- SQLException ---\n");
//			while( ex != null ) {
//				System.out.println("Mensagem: " + ex.getMessage());
//				System.out.println("SQLState: " + ex.getSQLState());
//				System.out.println("ErrorCode: " + ex.getErrorCode());
//				ex = ex.getNextException();
//				System.out.println("");
//			}
//		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		if(connection == null) {
			abrirConexao();
		}
		Produto prodFiltro = (Produto) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id AS ID, ");
		sql.append("p.DATAINSERCAO,");
		sql.append("p.NOME,");
		sql.append("p.qtde,");
		sql.append("p.CUSTO_UN,");
		sql.append("p.VALOR_VENDA,");
		sql.append("p.DATA_VAL,");
		sql.append("p.PRODUTO_COMPOSTO,");
		sql.append("p.ID_UN_MEDIDA,");
		sql.append("p.ID_CAT_PRODUTO,");
		sql.append("u.UNIDADE,");
		sql.append("c.NOME AS CATEGORIA ");
		sql.append("FROM produto P ");
		sql.append("JOIN UNIDADE_MEDIDA u ");
		sql.append("ON p.ID_UN_MEDIDA = u.id ");
		sql.append("JOIN categoria_produto c ");
		sql.append("ON p.ID_CAT_PRODUTO  = c.id ");
		sql.append("WHERE 1=1 ");
		
		if(prodFiltro.getId() != 0) {
			sql.append("AND p.ID = ? ");
		}
		if(prodFiltro.getNome() != null && !prodFiltro.getNome().equals("")) {
			sql.append("AND p.NOME = ? ");
		}
		if(prodFiltro.getCategoria() != null && prodFiltro.getCategoria().getId() != 0) {
			sql.append("AND p.ID_CAT_PRODUTO = ? ");
		}
		if(prodFiltro.getUnidadeMedida() != null && prodFiltro.getUnidadeMedida().getId() != 0) {
			sql.append("AND p.ID_UN_MEDIDA = ? ");
		}
		
		try {
			pst = connection.prepareStatement(sql.toString());
			int i = 1;
			if(prodFiltro.getId() != 0) {
				pst.setInt(i, prodFiltro.getId());
				i++;
			}
			if(prodFiltro.getNome() != null && !prodFiltro.getNome().equals("")) {
				pst.setString(i, prodFiltro.getNome());
				i++;
			}
			if(prodFiltro.getCategoria() != null && prodFiltro.getCategoria().getId() != 0) {
				pst.setInt(i, prodFiltro.getCategoria().getId());
				i++;
			}
			if(prodFiltro.getUnidadeMedida() != null && prodFiltro.getUnidadeMedida().getId() != 0) {
				pst.setInt(i, prodFiltro.getUnidadeMedida().getId());
				i++;
			}
			ResultSet rs = pst.executeQuery();
			
			List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
			while(rs.next() ) {
				UnidadeMedida un = new UnidadeMedida();
				CategoriaProduto catProd = new CategoriaProduto();
				Produto produto = new Produto();
				un.setId(rs.getInt("id_un_medida"));
				un.setUnidade(rs.getString("UNIDADE"));
				catProd.setId(rs.getInt("ID_CAT_PRODUTO"));
				catProd.setNome(rs.getString("CATEGORIA"));
				produto.setUnidadeMedida(un);
				produto.setCategoria(catProd);
				produto.setCustoUnitario(rs.getDouble("CUSTO_UN"));
				produto.setDataValidade(rs.getDate("DATA_VAL"));
				produto.setId(rs.getInt("ID"));
				produto.setNome(rs.getString("NOME"));
				produto.setQtde(rs.getDouble("qtde"));
				produto.setValorVenda(rs.getDouble("VALOR_VENDA"));
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

}
