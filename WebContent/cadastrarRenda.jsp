<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>
<link href="./assets/css/css_fintech.css" rel="stylesheet" />
<title>Cadastrar Renda</title>
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
			<h1>Adicionar renda</h1>
			<form action="renda" method="post">
				<input type="hidden" value="cadastrar" name="acao"> 
				<input type="hidden" value="${user }" name="cpf">	                      
				<label for="id-descricao" class="form-label">Descrição</label>
				<input
					type="text"
					id="id-descricao"
					name="descricao"
					value="${renda.descricao}"
					
				/>		                          
				<label for="id-data" class="form-label">Data Recebimento</label>
				<input
					type="text"
					id="id-data"
					name="data"
					value="<fmt:formatDate value="${renda.dataOcorrencia.time}" pattern="dd/MM/yyyy"/>"
					
				/>		                        		                        
                      
				<label for="id-valor" class="form-label">Valor recebido</label>		                      
				<input
					type="number"
					id="id-valor"
					name="valor"
					value="${renda.valor}"
					
				/>		                        
				
				<c:url value="renda" var="link"> 

					<c:param name="acao" value="listar"/> 

					<c:param name="cpf" value="${user}"/> 

				</c:url> 
				<a href="${link}">
					<button
					type="button"
					class="btn btn-secondary"
					>Cancelar</button>
				</a>		                    	
				<button type="submit" class="button-inv">Salvar</button>		                     
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