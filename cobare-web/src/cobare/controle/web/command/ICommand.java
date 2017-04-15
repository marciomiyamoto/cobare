package cobare.controle.web.command;

import cobare.core.aplicacao.Resultado;
import cobare.dominio.EntidadeDominio;

public interface ICommand {
	
	public Resultado execute(EntidadeDominio entidade);

}
