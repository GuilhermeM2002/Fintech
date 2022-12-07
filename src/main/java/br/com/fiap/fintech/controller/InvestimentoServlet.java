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

import br.com.fiap.fintech.bean.InvestimentoUsuario;
import br.com.fiap.fintech.bean.NomeBancoCategoria;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.dao.NomeBancoCategoriaDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private InvestimentoDAO dao;
	private NomeBancoCategoriaDAO categoriaDao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getInvestimentoDAO();
		categoriaDao = DAOFactory.getNomeBancoCategoriaDAO();
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
		List<NomeBancoCategoria> lista = categoriaDao.getAll();
		request.setAttribute("categorias", lista);
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastroInvestimento.jsp").forward(request, response);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		InvestimentoUsuario investimento = dao.select(id);
		request.setAttribute("investimento", investimento);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("editarInvestimento.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		List<InvestimentoUsuario> lista = dao.getAll(cpf);
		request.setAttribute("investimentos", lista);
		request.getRequestDispatcher("investimento.jsp").forward(request, response);
		
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
		case "excluir":
			excluir(request, response);
			break;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.delete(codigo);
			request.setAttribute("msg", "Investimento removido!");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			String tipo = request.getParameter("tipo");
			String nome = request.getParameter("nome");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			Calendar vencimento = Calendar.getInstance();
			vencimento.setTime(format.parse(request.getParameter("vencimento")));

			NomeBancoCategoria categoria = new NomeBancoCategoria();
			categoria.setCodigo(codigoCategoria);
			
			InvestimentoUsuario investimento = new InvestimentoUsuario(tipo, nome, valor, data, vencimento);
			investimento.setNomeBancoOuCorretora(categoria);
			investimento.setCodigo(codigo);
			dao.update(investimento);

			request.setAttribute("msg", "Investimento atualizado!");
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
			String tipo = request.getParameter("tipo");
			String nome = request.getParameter("nome");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			Calendar vencimento = Calendar.getInstance();
			vencimento.setTime(format.parse(request.getParameter("vencimento")));

			NomeBancoCategoria categoria = new NomeBancoCategoria();
			categoria.setCodigo(codigoCategoria);
			
			Usuario usuario = new Usuario();
			usuario.setCpfUsuario(cpf);
			
			InvestimentoUsuario investimento = new InvestimentoUsuario(tipo, nome, valor, data, vencimento);
			investimento.setNomeBancoOuCorretora(categoria);
			investimento.setCpf(usuario);
			
			
			dao.insert(investimento);

			request.setAttribute("msg", "Investimento cadastrado!");
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
