<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <%@ include file="header.jsp" %>
    <link href="./assets/css/css_fintech.css" rel="stylesheet" />
    <title>Fintech</title>
  </head>

  <body>
    <div class="container-fluid">
      <header><%@ include file="menu.jsp" %></header>

      <div class="container">
        <p class="h2">Ol�, � bom ver voc� aqui.</p>

        <section class="d-none d-md-flex local_banner">
          <div class="frase-banner">
            <h1>Pronto para mudar sua vida financeira?</h1>
          </div>
        </section>

        <main>
          <p class="text">
            Comece definindo um objetivo financeiro, algo que voc� queira fazer
            ou possuir, como uma casa pr�pria ou a viagem dos sonhos.
          </p>
          <div class="conteudo-principal">
            <p>
              Em primeiro lugar entenda suas prioridades, assim ser� muito f�cil
              definir um objetivo.
            </p>
          </div>
          <div class="conteudo-principal">
            <p>
              Para guardar dinheiro, pode ser necess�rio atacar os gastos como
              um todo. Pense em como voc� faria para reorganizar as despesas se
              tivesse perdido de 5% a 20% da sua remunera��o.
            </p>
          </div>
          <div class="conteudo-principal">
            <p>
              Para tirar o planejamento do papel, tamb�m � importante n�o se
              colocar em situa��es nas quais voc� provavelmente tomar� uma
              decis�o ruim.
            </p>
          </div>
          <div class="mt-5 mb-5">
            <p class="text">
              Com foco e dedica��o seu objetivo fica cada dia mais pr�ximo de se
              realizar.
            </p>
          </div>
          <a class="mes-corrente" href="Index.html"
            >Seu saldo do m�s corrente</a
          >
        </main>
      </div>

      <footer><%@ include file="footer.jsp" %></footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./assets/js/action.js"></script>
  </body>
</html>
