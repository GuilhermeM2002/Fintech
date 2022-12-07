<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%@ include file="header.jsp" %>
	<link href="./assets/css/css_fintech.css" rel="stylesheet" />
    <title>Gastos</title>
</head>
<body> 
  <div class="container-fluid">
    <header>
		<%@ include file="menu.jsp" %>
    </header>

      <div class="container">
        <main>
          <h1>Seus gastos</h1>

          <section >
          	<c:forEach items="${gastos}" var="g">
				<div class="card-consulta">
					<div class="card-consulta-item">
						<h1>Descrição</h1>
						<p>${g.descricao}</p>
					</div>
					
		            <div class="card-consulta-item">
			            <h1>Valor</h1>
			            <p>${g.valor}</p>
		            </div>	
		            <div class="card-consulta-item">
			            <h1>Data limite</h1>
			            <p><fmt:formatDate value="${g.dataOcorrencia.time }" pattern="dd/MM/yyyy"/></p>
		            </div>
		            	
		            
		            	
					<div class="container-button">
						<c:url value="gasto" var="link"> 
		
							<c:param name="acao" value="abrir-form-edicao"/> 
		
							<c:param name="codigo" value="${g.codigo}"/> 
		
						</c:url> 
						<a href="${link}"><button
							type="button"
							class="button-inv"
							>Editar</button>
						</a>		
						<form action="gasto" method="post"> 
		
		      				<input type="hidden" name="acao" value="excluir"> 
		
		      				<input type="hidden" name="codigo" id="codigoExcluir"> 
							<button type="submit" class="button-excluir" onclick="codigoExcluir.value = ${g.codigo}"> 
		
		  						Excluir 
		
							</button>
						</form>				
					</div>
				</div>
	            
								
							
		</c:forEach> 
				
	</section>
            
            
          <section class="add">
            <c:if test="${not empty msg }"> 
				<div class="alert alert-success">${msg}</div> 
			</c:if>
				
			<c:if test="${not empty erro }"> 
				<div class="alert alert-danger">${erro}</div> 
			</c:if>          
            <h1>Adicionar objetivo</h1>
            <a href="gasto?acao=abrir-form-cadastro">
            	<button type="button" class="button-inv">Adicionar</button>
            </a>
 				
          </section>

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