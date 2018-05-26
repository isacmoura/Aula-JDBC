package br.com.ufc.jdbcteste;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.jdbcteste.dao.AlocacaoDAO;
import br.com.ufc.jdbcteste.dao.LivroDAO;
import br.com.ufc.jdbcteste.dao.UsuarioDAO;
import br.com.ufc.jdbcteste.pojo.Alocacao;
import br.com.ufc.jdbcteste.pojo.Livro;
import br.com.ufc.jdbcteste.pojo.Usuario;

public class Main {
	public static void main(String[] args) {
		UsuarioDAO userDAO = new UsuarioDAO();
		LivroDAO livroDAO = new LivroDAO();
		AlocacaoDAO alocDAO = new AlocacaoDAO();
		
		int opcao;
		Scanner in = new Scanner(System.in);
		boolean terminar = false;
		
		do {
			System.out.println("| 1 - Cadastrar usuário");
			System.out.println("| 2 - Listar usuários");
			System.out.println("| 3 - Apagar um usuário");
			System.out.println("| 4 - Cadastrar livro");
			System.out.println("| 5 - Alocar um livro a um usuário");
			System.out.println("| 6 - Listar alocações");
			System.out.println("| 0 - Sair");
			
			opcao = in.nextInt();
			in.nextLine(); //Eliminar buffer
			switch(opcao) {
			case 1:
				String nome, email, endereco;
				System.out.println("Digite o nome do usuário:");
				nome = in.nextLine();
				System.out.println("Digite o email do usuário:");
				email = in.nextLine();
				System.out.println("Digite o endereço do usuário:");
				endereco = in.nextLine();
				
				Usuario usuario = new Usuario(nome, email, endereco);
				//Se o retorno do método adicionarUsuario for true, exibirá a mensagem de sucesso
				if(userDAO.adicionarUsuario(usuario)) {
					System.out.println("Usuário adicionado com sucesso!");
				}else {
					System.err.println("Ocorreu algum erro ao inserir o usuário");
				}
				break;
				
			case 2:
				ArrayList<Usuario> listaUsuarios = userDAO.listarUsuarios();
				for(Usuario user : listaUsuarios) {
					System.out.println(user.toString());
				}
				break;
				
			case 3:
				System.out.println("Digite o ID do usuário a ser excluído");
				int id = in.nextInt();
				if(userDAO.excluirUsuario(id)) {
					System.out.println("Usuário excluído com sucesso!");
				}else {
					System.err.println("Erro ao excluir usuário.");
				}
				break;
				
			case 4:
				String titulo, autor, editora;
				System.out.println("Digite o título do livro");
				titulo = in.nextLine();
				System.out.println("Digite a editora do livro");
				editora = in.nextLine();
				System.out.println("Digite o nome do autor do livro");
				autor = in.nextLine();
				
				Livro livro = new Livro(titulo, autor, editora);
				
				if(livroDAO.adicionarLivro(livro)) {
					System.out.println("Livro adicionado");
				}else {
					System.err.println("Ocorreu um erro ao adicionar o livro");
				}
				break;
				
			case 5:
				int idLivro, idUsuario;
				System.out.println("Digite o ID do usuário");
				idUsuario = in.nextInt();
				System.out.println("Digite o ID do livro");
				idLivro = in.nextInt();
				
				Usuario user = userDAO.getUserById(idUsuario);
				Livro livronv = livroDAO.getLivroById(idLivro);
				
				alocDAO.alocar(livronv, user);
				
				break;
				
			case 6:
				ArrayList<Alocacao> livrosAloc = alocDAO.getListAlocacao();
				for(Alocacao aloc : livrosAloc) {
					System.out.println(aloc.getUsuario().getNome() + " alocou o livro " + aloc.getLivro().getTitulo());
				}
				break;
				
				default:
					terminar = true;
					break;
			}
		}while(!terminar);
	}
}
