package br.com.fiap.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	 

	private UsuarioDAO dao; 

   

	public LoginServlet() { 

        dao = DAOFactory.getUsuarioDAO(); 


    } 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		String cpf = request.getParameter("cpf"); 

		String senha = request.getParameter("senha"); 

		Usuario usuario = new Usuario(); 
		
		usuario.setCpfUsuario(cpf);
		usuario.setSenha(senha);

		if (dao.validarUsuario(usuario)) { 

			HttpSession session = request.getSession(); 

			session.setAttribute("user", cpf); 
 

		}else { 

			request.setAttribute("erro", "Usuário e/ou senha inválidos"); 

		} 

		request.getRequestDispatcher("index.jsp").forward(request, response); 

	}
	
}
