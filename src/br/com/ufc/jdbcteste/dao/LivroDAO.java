package br.com.ufc.jdbcteste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;
import br.com.ufc.jdbcteste.pojo.Livro;

public class LivroDAO {
	private Connection connection;
	
	public LivroDAO() {
		
	}
	
	public boolean adicionarLivro(Livro livro) {
		String sql = "INSERT INTO livro (titulo, editora, autor) VALUES (?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			//Necessário para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Set values
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getEditora());
			stmt.setString(3, livro.getAutor());
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			
			if(affectedRows > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally{
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ArrayList<Livro> getListLivro(){
		String sql = "SELECT * FROM livro";
		ArrayList<Livro> listLivros = new ArrayList<Livro>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			/*ResultSet é uma interface utilizada pra guardar
			 * dados vindos de um banco de dados.
			*Basicamente, ela guarda o resultado de uma pesquisa
			*numa estrutura de dados que pode ser percorrida,
			*de forma que você possa ler os dados do banco. */
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String titulo = rs.getString("titulo");
				String editora = rs.getString("editora");
				String autor = rs.getString("autor");
				
				Livro livro = new Livro(id, titulo, editora, autor);
				
				listLivros.add(livro);
			}
			stmt.close();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listLivros;
	}
	
	public Livro getLivroById(int id) {
		String sql = "SELECT * FROM livro WHERE id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Livro livro = new Livro(id, rs.getString("titulo"), rs.getString("editora"), rs.getString("autor"));
			
			stmt.close();
			
			return livro;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
