<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
	<%@ include file="header.jsp" %>
    <link href="./assets/css/login.css" rel="stylesheet" />
    <title>Login</title>
  </head>
  <body>
    <div class="container-fluid">
      <header>
        <img class="logo" src="./assets/img/logo.svg" alt="Logo" />
        <h1 class="h1">Spending Guide</h1>
        <p>faça login e comece a usar!</p>
      </header>
      <main>
      	<c:if test="${empty user }"> 

	    	<span class="navbar-text text-danger" style="margin-right:10px" > 

	        ${erro } 

	  		</span>
	        <form action="login" method="post">
	          <label for="inputEmail" class="form-label">Número de CPF </label>
	          <input
	            class="form-contol"
	            type="text"
	            id="inputEmail"
	            name="cpf"
	            placeholder="Digite seu CPF"
	            minlength="8"
	            required
	          />
	
	          <label for="inputPassword" class="form-label">Senha </label>
	          <input
	            class="form-contol"
	            type="password"
	            id="inputPassword"
	            name="senha"
	            placeholder="********"
	            required
	          />

          		<button type="submit">Entrar</button>
        	</form>
        </c:if>
      </main>
      <footer>
        <a href="#">Esqueceu sua senha?</a>
        <a href="usuario?acao=abrir-form-cadastro">Não possui uma conta? Cadastre se</a>
        
        
      </footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>