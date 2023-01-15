<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="header.jsp" %>
    <link href="./assets/css/css_fintech.css" rel="stylesheet" />
    <title>Editar Investimento</title>
  </head>
  <body>
    <div class="container-fluid">
      <header><%@ include file="menu.jsp" %></header>
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
          <h1>Alterar Investimento</h1>
          <form action="investimento" method="post">
            <input type="hidden" value="editar" name="acao" />
            <input type="hidden" value="${investimento.codigo}" name="codigo" />

            <label for="id-tipo" class="form-label">Tipo da aplica��o</label>
            <input
              type="text"
              id="id-tipo"
              name="tipo"
              value="${investimento.tipoInvestimento}"
              required
            />
            <label for="id-nome" class="form-label"
              >Nome da aplica��o financeira</label
            >
            <input
              type="text"
              id="id-nome"
              name="nome"
              value="${investimento.nomeInvestimento}"
              required
            />
            <label for="id-categoria" class="form-label"
              >None Banco/Corretora</label
            >
            <select name="categoria" id="id-categoria">
              <option value="0">Selecione</option>

              <c:forEach items="${categorias}" var="c">
                <c:if
                  test="${c.codigo == investimento.nomeBancoOuCorretora.codigo }"
                >
                  <option value="${c.codigo }" selected>${c.nome }</option>
                </c:if>

                <c:if
                  test="${c.codigo != investimento.nomeBancoOuCorretora.codigo }"
                >
                  <option value="${c.codigo }">${c.nome }</option>
                </c:if>
              </c:forEach>
            </select>
            <label for="id-valor" class="form-label">Valor da aplica��o</label>
            <input
              type="number"
              id="id-valor"
              name="valor"
              value="${investimento.valorAplicacao}"
              required
            />
            <label for="id-data" class="form-label">Data da aplica��o</label>
            <input type="text" id="id-data" name="data" value="<fmt:formatDate
              value="${investimento.dataInvestimento.time}"
              pattern="dd/MM/yyyy"
            />" required />
            <label for="id-vencimento" class="form-label"
              >Data de vencimento</label
            >
            <input type="text" id="id-vencimento" name="vencimento"
            value="<fmt:formatDate
              value="${investimento.dataVencimento.time}"
              pattern="dd/MM/yyyy"
            />" />
            <c:url value="investimento" var="link">
              <c:param name="acao" value="listar" />

              <c:param name="cpf" value="${user}" />
            </c:url>
            <a href="${link}">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Cancelar
              </button>
            </a>
            <button type="submit" class="button-inv">Salvar</button>
          </form>
        </main>
      </div>
      <footer><%@ include file="footer.jsp" %></footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/action.js"></script>
  </body>
</html>
