package br.com.ufc.jdbcteste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;
import br.com.ufc.jdbcteste.pojo.Alocacao;
import br.com.ufc.jdbcteste.pojo.Livro;
import br.com.ufc.jdbcteste.pojo.Usuario;

public class AlocacaoDAO {
	private Connection connection;
	
	public AlocacaoDAO() {
		
	}
	
	public boolean alocar (Livro livro, Usuario usuario) {
		String sql = "INSERT INTO alocacao(id_usuario, id_livro) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getId());
			stmt.setInt(2, livro.getId());
			
			int affectedRows = stmt.executeUpdate();
			
			if(affectedRows > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ArrayList<Alocacao> getListAlocacao(){
		String sql = "SELECT * FROM alocacao";
		ArrayList<Alocacao> alocacoesLivros = new ArrayList<Alocacao>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int idUsuario = Integer.parseInt(rs.getString("id_usuario"));
				UsuarioDAO userDAO = new UsuarioDAO();
				Usuario user = userDAO.getUserById(idUsuario);
				
				int idLivro = Integer.parseInt(rs.getString("id_livro"));
				LivroDAO livroDAO = new LivroDAO();
				Livro livro = livroDAO.getLivroById(idLivro);
				
				Alocacao aloc = new Alocacao(user, livro);
				
				alocacoesLivros.add(aloc);
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
		return alocacoesLivros;
	}
}
