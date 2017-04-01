package cobare.controle.web.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class mesasMB {	
	
	private String mesa;
	private String pedido;
	private List <String> mesas;
	private List <String> pedidos;
	private List <String> qtde;
	private List <String> valor;
	private List <String> atendente;
	
	
	@PostConstruct
	public void init() {
		mesa = new String();
		pedido = new String();
		mesas = new ArrayList<String>();
		pedidos = new ArrayList<String>();
		qtde = new ArrayList<String>();
		valor = new ArrayList<String>();
		atendente = new ArrayList<String>();
		
		mesas = populaMesas();
		pedidos = populaPedidos();
		qtde = populaQtde();
		valor = populaValor();
		atendente = populAtendentes();
	}
	
	public List<String> populaMesas(){
		List <String> mesas = new ArrayList <String>();
		mesas.add("Mesa 1");
		mesas.add("Mesa 2");
		mesas.add("Mesa 3");
		mesas.add("Mesa 4");
		mesas.add("Mesa 5");
		mesas.add("Mesa 6");
		mesas.add("Mesa 7");
		mesas.add("Mesa 8");
		mesas.add("Mesa 9");
		mesas.add("Mesa 10");
		mesas.add("Mesa 11");
		mesas.add("Mesa 12");
		mesas.add("Mesa 13");
		mesas.add("Mesa 14");
		mesas.add("Mesa 15");
		mesas.add("Mesa 16");
		mesas.add("Mesa 17");
		mesas.add("Mesa 18");
		mesas.add("Mesa 19");
		mesas.add("Mesa 20");
		mesas.add("Mesa 31");
		mesas.add("Mesa 32");
		mesas.add("Mesa 33");
		mesas.add("Mesa 34");
		mesas.add("Mesa 35");
		mesas.add("Mesa 33");
		mesas.add("Mesa 37");
		mesas.add("Mesa 38");
		mesas.add("Mesa 39");
		mesas.add("Mesa 40");
		return mesas;
	}
	
	public List<String> populaPedidos(){
		List <String> pedidos = new ArrayList <String>();
		pedidos.add("Cerveja Bohemia 600 ml");
		pedidos.add("Porção de fritas");
		pedidos.add("Caipirinha");
		pedidos.add("Guaraná Antártica");
		return pedidos;
	}
	public List<String> populaQtde(){
		List <String> qtde = new ArrayList <String>();
		qtde.add("3");
		qtde.add("1");
		qtde.add("2");
		qtde.add("1");
		return qtde;
	}
	public List<String> populaValor(){
		List <String> valor = new ArrayList <String>();
		valor.add("17,00");
		valor.add("11,90");
		valor.add("9,00");
		valor.add("3,50");
		return valor;
	}
	public List<String> populAtendentes(){
		List <String> atendentes = new ArrayList <String>();
		atendentes.add("Carlos");
		atendentes.add("Leandro");
		atendentes.add("Paulo");
		atendentes.add("Carlos");
		return atendentes;
	}
	
	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public List<String> getMesas() {
		return mesas;
	}

	public void setMesas(List<String> mesas) {
		this.mesas = mesas;
	}

	public List<String> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<String> pedidos) {
		this.pedidos = pedidos;
	}

	public List<String> getQtde() {
		return qtde;
	}

	public void setQtde(List<String> qtde) {
		this.qtde = qtde;
	}

	public List<String> getValor() {
		return valor;
	}

	public void setValor(List<String> valor) {
		this.valor = valor;
	}

	public List<String> getAtendente() {
		return atendente;
	}

	public void setAtendente(List<String> atendente) {
		this.atendente = atendente;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	
	
	
}
