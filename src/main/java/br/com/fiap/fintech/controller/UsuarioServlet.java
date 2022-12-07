package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.GeneroCategoria;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.GeneroCategoriaDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	private GeneroCategoriaDAO categoriaDao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
		categoriaDao = DAOFactory.getGeneroCategoriaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastro":
			abrirFormCadastro(request, response);
			break;
		}
		
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<GeneroCategoria> lista = categoriaDao.getAll();
		request.setAttribute("categorias", lista);
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro.jsp").forward(request, response);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		Usuario usuario = dao.select(cpf);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cpf = request.getParameter("cpf");
		Usuario usuario = dao.select(cpf);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("dados.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request,response);
			break;
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cpf = request.getParameter("cpf");
			
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			
			Usuario usuario = new Usuario();
			usuario.setCpfUsuario(cpf);
			usuario.setNomeUsuario(nome);
			usuario.setSenha(senha);
			usuario.setDataNascimento(data);

			dao.update(usuario);

			request.setAttribute("msg", "Dados atualizados!");
		} catch (SQLException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			String cpf = request.getParameter("cpf");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));

			GeneroCategoria categoria = new GeneroCategoria();
			categoria.setCodigo(codigoCategoria);
			
			Usuario usuario = new Usuario(cpf, nome, data, categoria, email, senha);
			usuario.setGeneroUsuario(categoria);
			
			
			dao.insert(usuario);

			request.setAttribute("msg", "Usuário cadastrado!");
		} catch (SQLException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		abrirFormCadastro(request, response);
	}
}