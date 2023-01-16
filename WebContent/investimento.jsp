<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<!DOCTYPE html>
			<html lang="pt-br">
			<head>
				<%@ include file="header.jsp" %>
					<link href="./assets/css/css_fintech.css" rel="stylesheet" />
					<title>Investimentos</title>
			</head>
			<body>
				<div class="container-fluid">
					<header>
						<%@ include file="menu.jsp" %>
					</header>
					<div class="container">
						<main>
							<h1>Seus investimentos</h1>
							<div class="row mb-4 fundos">
								<p>
									Diversificar suas fontes de renda � um �timo caminho para aumentar
									seus lucros, investir em diferentes �reas � primordial para que
									isso aconte�a.
								</p>
							</div>
							<section>
								<c:forEach items="${investimentos}" var="i">
									<div class="card-consulta">
										<div class="card-consulta-item">
											<h1>Tipo: </h1>
											<p>${i.tipoInvestimento}</p>
										</div>

										<div class="card-consulta-item">
											<h1>Nome: </h1>
											<p>${i.nomeInvestimento}</p>
										</div>

										<div class="card-consulta-item">
											<h1>Valor: </h1>
											<p>${i.valorAplicacao}</p>
										</div>

										<div class="card-consulta-item">
											<h1>Corretora: </h1>
											<p>${i.nomeBancoOuCorretora}</p>
										</div>

										<div class="card-consulta-item">
											<h1>Data: </h1>
											<p>
												<fmt:formatDate value="${i.dataInvestimento.time }" pattern="dd/MM/yyyy" />
											</p>
										</div>

										<div class="card-consulta-item">
											<h1>Vencimento: </h1>
											<p>
												<fmt:formatDate value="${i.dataVencimento.time }" pattern="dd/MM/yyyy" />
											</p>
										</div>

										<div class="container-button">
											<c:url value="investimento" var="link">

												<c:param name="acao" value="abrir-form-edicao" />

												<c:param name="codigo" value="${i.codigo}" />

											</c:url>
											<a href="${link}"><button type="button" class="button-inv">Editar</button>
											</a>
											<form action="investimento" method="post">
												<input type="hidden" name="acao" value="excluir">
												<input type="hidden" name="codigo" id="codigoExcluir">
												<button type="submit" class="button-excluir" onclick="codigoExcluir.value = ${i.codigo}">
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
								<h1>Adicionar Investimento</h1>
								<a href="investimento?acao=abrir-form-cadastro">
									<button type="button" class="button-inv">Adicionar</button>
								</a>

							</section>

							<article class="row">
								<div class="p-0">
									<h1>Ainda n�o tem nenhum investimento?</h1>
									<p class="text">
										N�o perca tempo, consulte nossa p�gina "Aprenda a investir" para
										ficar por dentro de tudo e come�ar sua primeira aplica��o.
									</p>
									<p class="text">
										Fique de olho tamb�m em nossas redes sociais, l� voc� pode obter
										v�rias dicas para te ajudar em suas aplica��es.
									</p>
									<h1>Simule agora</h1>
									<p class="text">
										Saiba quanto seu dinheiro pode render ao simular investimentos
										de CDB e LC, LCA, LCI e tesouro direto em rela��o a poupan�a.
									</p>
									<p class="text">
										Compare e comece a investir de forma f�cil e intuitiva:
									</p>
									<a href="#"><i class="bi bi-arrow-right"></i></a>
								</div>
							</article>
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