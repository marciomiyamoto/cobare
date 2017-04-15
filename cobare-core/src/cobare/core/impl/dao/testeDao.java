package cobare.core.impl.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cobare.dominio.Cidade;
import cobare.dominio.Endereco;
import cobare.dominio.Estado;
import cobare.dominio.Fornecedor;
import cobare.dominio.Telefone;

public class testeDao {
	
	public static void main(String[] Args)	{
		Fornecedor fornecedor = new Fornecedor();
		Endereco end = new Endereco();
		Cidade cidade = new Cidade();
		Estado uf = new Estado();
		Telefone tel = new Telefone();
		List <Telefone> telefones = new ArrayList<Telefone>();
		FornecedorDAO forDao = new FornecedorDAO();
		
		tel.setNumero(1111);
		telefones.add(tel);
		
		uf.setNome("ES");
		
		cidade.setNome("Vitória");
		cidade.setEstado(uf);
		
		end.setBairro("aaa");
		end.setCep("09090909");
		end.setCidade(cidade);
		end.setLogradouro("Rua x");
		end.setNumero("444");
		
		fornecedor.setCnpj("123123123");
		fornecedor.setEmail("aaaa@aaa.com");
		fornecedor.setEndereco(end);
		fornecedor.setInscEstadual("12312312");
		fornecedor.setNomeFantasia("Barzinho do Pedrinho");
		fornecedor.setRazaoSocial("Pedrinho Ltda");
		fornecedor.setTelefones(telefones);
		
		try {
			forDao.salvar(fornecedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
