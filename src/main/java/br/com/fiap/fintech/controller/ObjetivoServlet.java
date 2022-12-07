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


import br.com.fiap.fintech.bean.ObjetivoUsuario;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/objetivo")
public class ObjetivoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ObjetivoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getObjetivoDAO();
	
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
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.getRequestDispatcher("cadastrarObjetivo.jsp").forward(request, response);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		ObjetivoUsuario objetivo = dao.select(id);
		request.setAttribute("objetivo", objetivo);
	
		request.getRequestDispatcher("editarObjetivo.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		List<ObjetivoUsuario> lista = dao.getAll(cpf);
		request.setAttribute("objetivos", lista);
		request.getRequestDispatcher("objetivo.jsp").forward(request, response);
		
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
			request.setAttribute("msg", "Objetivo removido!");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String descricao = request.getParameter("descricao");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			
			ObjetivoUsuario objetivo = new ObjetivoUsuario(codigo, descricao, data, valor);
			dao.update(objetivo);

			request.setAttribute("msg", "Objetivo atualizado!");
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
			String cpf = request.getParameter("cpf");
			String descricao = request.getParameter("descricao");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			
			ObjetivoUsuario objetivo = new ObjetivoUsuario();
			objetivo.setDescricaoObjetivo(descricao);
			objetivo.setValorObjetivo(valor);
			objetivo.setDataCumprirObjetivo(data);
			
			dao.insert(objetivo, cpf);

			request.setAttribute("msg", "Objetivo cadastrado!");
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


