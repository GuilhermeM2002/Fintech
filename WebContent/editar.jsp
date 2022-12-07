<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>
<link href="./assets/css/css_fintech.css" rel="stylesheet" />
<title>Editar dados</title>
</head>
<body>

<div class="container-fluid">
	<header>
		<%@ include file="menu.jsp" %>
	</header>
	<div class="container">
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
            	<input type="hidden" value="editar" name="acao">
 				<input type="hidden" value="${user }" name="cpf">

                <label for="id-nome" class="form-label">Nome Completo: </label>
                <input class="form-contol" type="text" id="id-nome" name="nome" value="${usuario.nomeUsuario }"> 
 	            
                <label for="id-data" class="form-label">Data de nascimento: </label>
                <input class="form-contol" type="text" id="id-data" name="data" value="${usuario.dataNascimento }">                                                        

                <label for="id-senha" class="form-label">Senha: </label>
                <input class="form-contol" type="password" id="id-senha" name="senha" value="${usuario.senha }">                            
                    
                <button type="submit" class="button-inv">Enviar</button>
            </form>                                   						                						                   
		</main>
	</div>
	<footer>
		<%@ include file="footer.jsp" %>
	</footer>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/action.js"></script>							
</body>
</html>