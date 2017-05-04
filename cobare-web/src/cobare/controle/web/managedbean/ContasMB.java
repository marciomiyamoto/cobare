package cobare.controle.web.managedbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import cobare.controle.web.command.ICommand;
import cobare.controle.web.command.impl.AlterarCommand;
import cobare.controle.web.command.impl.ConsultarCommand;
import cobare.controle.web.command.impl.ExcluirCommand;
import cobare.controle.web.command.impl.SalvarCommand;
import cobare.controle.web.command.impl.VisualizarCommand;
import cobare.core.aplicacao.Resultado;
import cobare.dominio.CategoriaProduto;
import cobare.dominio.UnidadeMedida;

@ManagedBean
public class ContasMB {
	
	private UnidadeMedida unMedida;
	private List <UnidadeMedida> unMedidas;
	private CategoriaProduto catProduto;
	private List<CategoriaProduto> catProdutos;
	private static Map<String, ICommand> commands;
	private ICommand command;
	
	private String contas;
	private boolean flagProdutoComposto;
	private String insumos;
	
	@PostConstruct
	public void init() {
		unMedida = new UnidadeMedida();
		unMedidas = new ArrayList <UnidadeMedida>();
		catProduto = new CategoriaProduto();
		catProdutos = new ArrayList<CategoriaProduto>();
		/* Utilizando o command para chamar a fachada e indexando cada command 
		 * pela operação garantimos que esta servelt atenderá qualquer operação */
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		popularUnidadeMedida();
		popularCategoriaProduto();
	}

	private void popularUnidadeMedida() {
		command = commands.get("CONSULTAR");
		Resultado rs = command.execute(unMedida);
		
		for(int i = 0; i < rs.getEntidades().size(); i++) {
			try {
				unMedidas.add(i, (UnidadeMedida) rs.getEntidades().get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void popularCategoriaProduto() {
		command = commands.get("CONSULTAR");
		Resultado rs = command.execute(catProduto);
		
		for(int i = 0; i < rs.getEntidades().size(); i++) {
			try {
				catProdutos.add(i, (CategoriaProduto) rs.getEntidades().get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public CategoriaProduto getCatProduto() {
		return catProduto;
	}

	public void setCatProduto(CategoriaProduto catProduto) {
		this.catProduto = catProduto;
	}

	public List<CategoriaProduto> getCatProdutos() {
		return catProdutos;
	}

	public void setCatProdutos(List<CategoriaProduto> catProdutos) {
		this.catProdutos = catProdutos;
	}

	public UnidadeMedida getUnMedida() {
		return unMedida;
	}

	public void setUnMedida(UnidadeMedida unMedida) {
		this.unMedida = unMedida;
	}

	public List<UnidadeMedida> getUnMedidas() {
		return unMedidas;
	}

	public void setUnMedidas(List<UnidadeMedida> unMedidas) {
		this.unMedidas = unMedidas;
	}

	public static Map<String, ICommand> getCommands() {
		return commands;
	}

	public static void setCommands(Map<String, ICommand> commands) {
		ContasMB.commands = commands;
	}

	public ICommand getCommand() {
		return command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public String getContas() {
		return contas;
	}

	public void setContas(String contas) {
		this.contas = contas;
	}

	public boolean isFlagProdutoComposto() {
		return flagProdutoComposto;
	}

	public void setFlagProdutoComposto(boolean flagProdutoComposto) {
		this.flagProdutoComposto = flagProdutoComposto;
	}

	public String getInsumos() {
		return insumos;
	}

	public void setInsumos(String insumos) {
		this.insumos = insumos;
	}
	

}
