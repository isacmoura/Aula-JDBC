package br.com.ufc.jdbcteste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.jdbcteste.jdbc.ConnectionFactory;
import br.com.ufc.jdbcteste.pojo.Usuario;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO() {
		
	}
	
	@SuppressWarnings("finally")
	public boolean adicionarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, email, endereco) VALUES (?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getEndereco());
			
			int rowsAffected = stmt.executeUpdate();
			stmt.close();
			if(rowsAffected > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	public ArrayList<Usuario> listarUsuarios(){
		String sql = "SELECT * FROM usuario";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");
				
				Usuario user = new Usuario(id, nome, email, endereco);
				
				usuarios.add(user);
				
			}
			
			stmt.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			try {
				this.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}
	
	public boolean excluirUsuario(int id) {
		String sql = "DELETE FROM usuario WHERE id = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Setar valores
			stmt.setInt(1, id);
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			
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
	
	public Usuario getUserById(int id) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Usuario usuario = new Usuario(id, rs.getString("email"), rs.getString("endereco"), rs.getString("nome"));
			stmt.close();
			
			return usuario;
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
