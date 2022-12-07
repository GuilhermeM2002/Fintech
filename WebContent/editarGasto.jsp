<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp" %>
<link href="./assets/css/css_fintech.css" rel="stylesheet" />
<title>Editar Gasto</title>
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
			<h1>Alterar gasto</h1>
			<form action="gasto" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${gasto.codigo}" name="codigo"> 
						                      
				<label for="id-descricao" class="form-label">Descrição</label>
				<input
					type="text"
					id="id-descricao"
					name="descricao"
					value="${gasto.descricao}"
					
				/>		                          
				<label for="id-data" class="form-label">Data vencimento</label>
				<input
					type="text"
					id="id-data"
					name="data"
					value="<fmt:formatDate value="${gasto.dataOcorrencia.time}" pattern="dd/MM/yyyy"/>"
					
				/>		                        		                        
                      
				<label for="id-valor" class="form-label">Valor do gasto</label>		                      
				<input
					type="number"
					id="id-valor"
					name="valor"
					value="${gasto.valor}"
					
				/>		                        
				
				<c:url value="gasto" var="link"> 

					<c:param name="acao" value="listar"/> 

					<c:param name="cpf" value="${user}"/> 

				</c:url> 
				<a href="${link}">
					<button
					type="button"
					class="btn btn-secondary"
					data-bs-dismiss="modal"
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