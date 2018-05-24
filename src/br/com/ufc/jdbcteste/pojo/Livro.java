package br.com.ufc.jdbcteste.pojo;

public class Livro {
	private int id;
	private String titulo;
	private String editora;
	private String autor;
	
	public Livro(int id, String titulo, String editora, String autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
	}

	public Livro(String titulo, String editora, String autor) {
		super();
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", editora=" + editora + ", autor=" + autor + "]";
	}

}
