<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%@ include file="header.jsp" %>
    <link href="./assets/css/login.css" rel="stylesheet">
    <title>Cadastro</title>
</head>
<body>
    <div class="container-fluid">
        <header>
            <img class="logo" src="./assets/img/logo.svg" alt="Logo" />
            <h1 class="h1">Spending Guide</h1>
            <p>faça cadastro e comece a usar!</p>
          </header>
        <main>
        	<div class="row">
				<c:if test="${not empty msg }">
					<div class="alert alert-success">${msg}</div>
				</c:if>
				<c:if test="${not empty erro }">
					<div class="alert alert-danger">${erro}</div>
				</c:if>
			</div>
            <form action="usuario" method="post">
            	<input type="hidden" value="cadastrar" name="acao">
                <label for="id-cpf" class="form-label">CPF: </label>
                <input class="form-contol" type="text" id="id-cpf" name="cpf" value="${usuario.cpfUsuario }" aria-describedby="emailHelp" required>

                <label for="id-nome" class="form-label">Nome Completo: </label>
                <input class="form-contol" type="text" id="id-nome" name="nome" value="${usuario.nomeUsuario }" required> 

                <label for="id-genero" class="form-label">Gênero:</label>
				<select name="categoria" id="id-genero"> 

					<option value="0">Selecione</option> 

					<c:forEach items="${categorias}" var="c"> 

						<option value="${c.codigo }" >${c.nome }</option> 

					</c:forEach> 
					
				</select>                            

                <label for="id-data" class="form-label">Data de nascimento: </label>
                <input class="form-contol" type="text" id="id-data" name="data" value="${usuario.dataNascimento }" required>                            

                <label for="id-email" class="form-label">E-mail: </label>
                <input class="form-contol" type="email" id="id-email" name="email" value="${usuario.email }"required>                            

                <label for="id-senha" class="form-label">Senha: </label>
                <input class="form-contol" type="password" minlength="8" id="id-senha" name="senha" value="${usuario.senha }" required>                            
                    
                <button type="submit">Enviar</button>
            </form>
        </main>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>