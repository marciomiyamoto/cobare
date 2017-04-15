package cobare.controle.web.command.impl;

import cobare.controle.web.command.ICommand;
import cobare.core.IFachada;
import cobare.core.impl.controle.Fachada;

public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();

}
