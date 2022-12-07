<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="pt-br">
  <head>
	<%@ include file="header.jsp" %>
	<link href="./assets/css/css_fintech.css" rel="stylesheet" />
    <title>Fintech</title>
  </head>

  <body>
    <div class="container-fluid">
      <header>
        <%@ include file="menu.jsp" %>
      </header>

      <div class="container">
        <p class="h2">Olá, é bom ver você aqui.</p>

        <section class="d-none d-md-flex local_banner">
        	<div class="frase-banner">
        		<h1 >Pronto para mudar sua vida financeira?</h1>
        	</div>		
        </section>

        <main>
          <p class="text">
            Comece definindo um objetivo financeiro, algo que você queira fazer ou possuir, como uma casa própria ou a viagem dos sonhos.
          </p>
          <div class="conteudo-principal">
            <p>
              Em primeiro lugar entenda suas prioridades, assim será muito fácil definir um objetivo.
            </p>
          </div>
          <div class="conteudo-principal">
            <p>
              Para guardar dinheiro, pode ser necessário atacar os gastos como um todo. Pense em como você faria para reorganizar as despesas se tivesse perdido de 5% a 20% da sua remuneração.
            </p>
          </div>
          <div class="conteudo-principal">
            <p>
              Para tirar o planejamento do papel, também é importante não se colocar em situações nas quais você provavelmente tomará uma decisão ruim.
            </p>
          </div>
          <div class="mt-5 mb-5">
            <p class="text">
              Com foco e dedicação seu objetivo fica cada dia mais próximo de se realizar.
            </p>
          </div>
          <a class="mes-corrente" href="Index.html"
            >Seu saldo do mês corrente</a
          >
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