<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="overlay" id="nav-overlay">
  <a href="javascript:void(0)" class="button-close" onclick="closeNav()"
    ><img src="./assets/img/close.svg" alt="Fechar"
  /></a>
  <div class="content-overlay">
    <c:url value="renda" var="link">
      <c:param name="acao" value="listar" />

      <c:param name="cpf" value="${user}" />
    </c:url>
    <a href="${link}" class="nav-link"> Rendas </a>
    <c:url value="gasto" var="link">
      <c:param name="acao" value="listar" />

      <c:param name="cpf" value="${user}" />
    </c:url>
    <a href="${link}" class="nav-link"> Gastos </a>
    <c:url value="objetivo" var="link">
      <c:param name="acao" value="listar" />

      <c:param name="cpf" value="${user}" />
    </c:url>
    <a href="${link}" class="nav-link"> Objetivos </a>
    <c:url value="investimento" var="link">
      <c:param name="acao" value="listar" />

      <c:param name="cpf" value="${user}" />
    </c:url>
    <a href="${link}" class="nav-link"> Investimentos </a>
  </div>
</div>
<button class="d-md-none" onclick="openNav()">
  <img src="./assets/img/list.svg" alt="Imagem lista" />
</button>

<a class="titulo" href="index.jsp"
  ><img class="logo" src="./assets/img/logo.svg" alt="Logo" />
  <h1>Spending Guide</h1>
</a>

<div class="d-md-none">
  <div class="offcanvas offcanvas-start" id="demo">
    <div class="offcanvas-header">
      <h1 class="offcanvas-title">Usu�rio</h1>
      <button
        type="button"
        class="btn-close text-reset"
        data-bs-dismiss="offcanvas"
      ></button>
    </div>
    <div class="offcanvas-body">
      <div class="list-group">
        <c:url value="usuario" var="link">
          <c:param name="acao" value="listar" />

          <c:param name="cpf" value="${user}" />
        </c:url>
        <a href="${link}" class="list-group-item list-group-itemiaction">
          <i class="bi bi-person-fill"></i> Meus dados
        </a>
        <a class="list-group-item list-group-itemiaction" href="#"
          ><i class="bi bi-key-fill"></i> Seguran�a</a
        >
        <a class="list-group-item list-group-itemiaction" href="#"
          ><i class="bi bi-bell-fill"></i> Notifica��es</a
        >
        <a class="list-group-item list-group-itemiaction" href="#"
          ><i class="bi bi-question-circle-fill"></i> Ajuda</a
        >
        <c:if test="${not empty user }">
          <span>
            <a href="login.jsp" class="list-group-item list-group-itemiaction"
              ><i class="bi bi-box-arrow-left"> </i> Sair</a
            >
          </span>
        </c:if>
      </div>
    </div>
  </div>
  <button type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">
    <img src="./assets/img/user-circle.svg" alt="�cone usu�rio" />
  </button>
</div>

<nav class="d-none d-md-flex">
  <ul class="menu">
    <li class="nav-item">
      <c:url value="renda" var="link">
        <c:param name="acao" value="listar" />

        <c:param name="cpf" value="${user}" />
      </c:url>
      <a href="${link}" class="nav-link"> Rendas </a>
    </li>
    <li class="nav-item">
      <c:url value="gasto" var="link">
        <c:param name="acao" value="listar" />

        <c:param name="cpf" value="${user}" />
      </c:url>
      <a href="${link}" class="nav-link"> Gastos </a>
    </li>
    <li class="nav-item">
      <c:url value="objetivo" var="link">
        <c:param name="acao" value="listar" />

        <c:param name="cpf" value="${user}" />
      </c:url>
      <a href="${link}" class="nav-link"> Objetivos </a>
    </li>
    <li class="nav-item">
      <c:url value="investimento" var="link">
        <c:param name="acao" value="listar" />

        <c:param name="cpf" value="${user}" />
      </c:url>
      <a href="${link}" class="nav-link"> Investimentos </a>
    </li>
    <li class="nav-item">
      <c:url value="usuario" var="link">
        <c:param name="acao" value="listar" />

        <c:param name="cpf" value="${user}" />
      </c:url>
      <a href="${link}" class="nav-link"> Meus dados </a>
    </li>
    <li class="nav-item">
      <c:if test="${not empty user }">
        <span>
          <a href="login.jsp" class="nav-link"> Sair</a>
        </span>
      </c:if>
    </li>
  </ul>
</nav>
