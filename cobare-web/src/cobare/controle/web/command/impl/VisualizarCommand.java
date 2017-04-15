package cobare.controle.web.command.impl;

import cobare.core.aplicacao.Resultado;
import cobare.dominio.EntidadeDominio;

public class VisualizarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.visualizar(entidade);
	}

}
