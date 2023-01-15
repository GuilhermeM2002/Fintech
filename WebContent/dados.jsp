<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="header.jsp" %>
    <link href="./assets/css/css_fintech.css" rel="stylesheet" />
    <title>Insert title here</title>
  </head>
  <body>
    <div class="container-fluid">
      <header><%@ include file="menu.jsp" %></header>

      <div class="container">
        <main>
          <c:if test="${not empty msg }">
            <div class="alert alert-success">${msg}</div>
          </c:if>
          <c:if test="${not empty erro }">
            <div class="alert alert-danger">${erro}</div>
          </c:if>
          <h1 class="mb-4">Dados do �suario</h1>
          <section class="row">
            <h1>Nome</h1>
            <p class="text">${usuario.nomeUsuario}</p>
            <h1>CPF</h1>
            <p class="text">${usuario.cpfUsuario}</p>
            <h1>E-mail</h1>
            <p class="text">${usuario.email}</p>
            <h1>Data de Nascimento</h1>
            <p class="text">
              <fmt:formatDate
                value="${usuario.dataNascimento.time }"
                pattern="dd/MM/yyyy"
              />
            </p>
          </section>

          <section class="add">
            <h1>Alterar dados</h1>

            <c:url value="usuario" var="link">
              <c:param name="acao" value="abrir-form-edicao" />

              <c:param name="codigo" value="${usuario.cpfUsuario}" />
            </c:url>
            <a href="${link}"
              ><button type="button" class="button-inv">Editar</button>
            </a>
          </section>
        </main>
      </div>

      <footer><%@ include file="footer.jsp" %></footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/action.js"></script>
  </body>
</html>
