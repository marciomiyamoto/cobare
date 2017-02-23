package br.com.teste.testeweb.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LivroMB {
	
	private Livro livro = new Livro();
	
	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
	}

}
