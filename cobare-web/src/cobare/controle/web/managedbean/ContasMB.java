package cobare.controle.web.managedbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import cobare.controle.web.command.ICommand;
import cobare.controle.web.command.impl.AlterarCommand;
import cobare.controle.web.command.impl.ConsultarCommand;
import cobare.controle.web.command.impl.ExcluirCommand;
import cobare.controle.web.command.impl.SalvarCommand;
import cobare.controle.web.command.impl.VisualizarCommand;
import cobare.core.aplicacao.Resultado;
import cobare.dominio.CategoriaProduto;
import cobare.dominio.Produto;
import cobare.dominio.UnidadeMedida;

@ManagedBean
@ViewScoped
public class ContasMB {
	
	private UnidadeMedida unMedida;
	private List <UnidadeMedida> unMedidas;
	private CategoriaProduto catProduto;
	private List<CategoriaProduto> catProdutos;
	private Produto produto;
	private List <Produto> produtos;
	private Produto insumo;
	private List <Produto> insumos;
	
	private static Map<String, ICommand> commands;
	private ICommand command;
	
	private String contas;
	private boolean flagProdutoComposto;
	
	@PostConstruct
	public void init() {
		unMedida = new UnidadeMedida();
		unMedidas = new ArrayList <UnidadeMedida>();
		catProduto = new CategoriaProduto();
		catProdutos = new ArrayList<CategoriaProduto>();
		produto = new Produto();
		produtos = new ArrayList<Produto>();
		insumo = new Produto();
		insumos = new ArrayList<Produto>();
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
		popularInsumos();
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
	private void popularInsumos() {
		Produto filtroProduto = new Produto();
		CategoriaProduto cat = new CategoriaProduto();
		cat.setId(1);
		filtroProduto.setCategoria(cat);
		command = commands.get("CONSULTAR");
		Resultado rs = command.execute(filtroProduto);
		
		for(int i = 0; i < rs.getEntidades().size(); i++) {
			try {
				insumos.add(i, (Produto) rs.getEntidades().get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void salvarProduto() {
		produto.setCategoria(catProduto);
		produto.setUnidadeMedida(unMedida);
		command = commands.get("SALVAR");
		Resultado rs = command.execute(produto);
		if(rs.getMsg() != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", rs.getMsg()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto salvo com sucesso!"));
		}
	}
	public void adicionaInsumo() {
		RequestContext req = RequestContext.getCurrentInstance();
//		Produto insumo = new Produto();
		produtos.add(insumo);
		req.update("formNovoProduto:tabInsumos");
	}
	CategoriaProduto cat = new CategoriaProduto();
	Produto filtro = new Produto();
	public void buscarProdutos() {
		produtos = new ArrayList<Produto>();
		RequestContext req = RequestContext.getCurrentInstance();
		filtro.setCategoria(cat);
		command = commands.get("CONSULTAR");
		Resultado rs = command.execute(filtro);
		
		for(int i = 0; i < rs.getEntidades().size(); i++) {
			try {
				produtos.add(i, (Produto) rs.getEntidades().get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		req.update("formAddProdutos:dtProdutos");
	}
	
	// GETTERS E SETTERS
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Produto> insumos) {
		this.insumos = insumos;
	}

	public Produto getInsumo() {
		return insumo;
	}

	public void setInsumo(Produto insumo) {
		this.insumo = insumo;
	}

	public CategoriaProduto getCat() {
		return cat;
	}

	public void setCat(CategoriaProduto cat) {
		this.cat = cat;
	}

	public Produto getFiltro() {
		return filtro;
	}

	public void setFiltro(Produto filtro) {
		this.filtro = filtro;
	}
	
}
