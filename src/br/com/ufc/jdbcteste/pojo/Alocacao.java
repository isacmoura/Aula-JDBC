package br.com.ufc.jdbcteste.pojo;

public class Alocacao {
	private Usuario usuario;
	private Livro livro;
	
	public Alocacao(Usuario usuario, Livro livro) {
		super();
		this.usuario = usuario;
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
}
