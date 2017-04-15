package cobare.controle.web.command.impl;

import cobare.core.aplicacao.Resultado;
import cobare.dominio.EntidadeDominio;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}
