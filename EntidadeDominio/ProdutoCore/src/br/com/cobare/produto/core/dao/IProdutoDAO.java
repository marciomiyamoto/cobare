package br.com.cobare.produto.core.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.cobare.produto.dominio.Produto;

public interface IProdutoDAO {
	void salvar( Produto produto ) throws SQLException;
	List < Produto > listar() throws SQLException;
	void atualizar( Produto produto ) throws SQLException;
	void excluir( Produto produto ) throws SQLException;
}
