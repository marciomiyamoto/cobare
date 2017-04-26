package cobare.core.impl.negocio;

import cobare.core.IStrategy;
import cobare.core.util.EstadosEnum;
import cobare.dominio.EntidadeDominio;
import cobare.dominio.Fornecedor;

public class ValidarDadosObrigatoriosFornecedor implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor)entidade;
			
			String razaoSocial = fornecedor.getRazaoSocial();
			String cnpj = fornecedor.getCnpj();
			String nomeFantasia = fornecedor.getNomeFantasia();
			String inscEstadual = fornecedor.getInscEstadual();
			String logradouro = fornecedor.getEndereco().getLogradouro();
			String numero = fornecedor.getEndereco().getNumero();
			String bairro = fornecedor.getEndereco().getBairro();
			String cidade = fornecedor.getEndereco().getCidade().getNome();
			int uf = fornecedor.getEndereco().getCidade().getEstado().getId();
			String cep = fornecedor.getEndereco().getCep();
			
			if(razaoSocial == null || logradouro == null || cnpj==null || cidade == null || nomeFantasia == null 
					|| inscEstadual == null || numero == null || bairro == null || uf == EstadosEnum.CODE_NULL.getCodigo() || cep == null ){
				return "Os campos marcados com * são de preenchimento obrigatório!";
			}
			
			if(razaoSocial.trim().equals("") || logradouro.trim().equals("") || nomeFantasia.trim().equals("") ||
					cnpj.trim().equals("")|| cidade.trim().equals("") || inscEstadual.trim().equals("") || numero.trim().equals("")
					|| bairro.trim().equals("") || cep.trim().equals("")){
				return "Os campos marcados com *  são de preenchimento obrigatório!";
			}
			
		}else{
			return "Deve ser registrado um fornecedor!";
		}
		return null;
	}

}
