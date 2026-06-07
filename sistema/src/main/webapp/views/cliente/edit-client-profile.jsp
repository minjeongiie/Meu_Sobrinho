<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.Usuario" %>
<%@ page import="model.entity.Cliente" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

  if (usuario == null || !(usuario instanceof Cliente)) {
    response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
    return;
  }

  Cliente cliente = (Cliente) usuario;
%>

<!doctype html>
<html lang="pt-BR">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>Editar perfil - Cliente</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/views/geral/home.jsp">MS Meu Sobrinho</a>
    <div class="collapse navbar-collapse" id="navMain">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/perfil">Meu Perfil</a></li>
        <li class="nav-item">
          <form action="${pageContext.request.contextPath}/logout" method="get" style="display:inline">
            <button class="btn btn-outline-secondary">Sair</button>
          </form>
        </li>
      </ul>
    </div>
  </div>
</nav>

<main class="container py-4">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card mb-3">
        <div class="card-body">
          <h4>Editar perfil do cliente</h4>

          <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">${requestScope.error}</div>
          </c:if>

          <form action="${pageContext.request.contextPath}/editar-perfil" method="post" class="needs-validation" novalidate>

            <input type="hidden" name="userId" value="<%= cliente.getId() %>" />

            <div class="mb-3">
              <label class="form-label">Nome</label>
              <input name="name" class="form-control" value="<%= cliente.getNomeCompleto() %>" required>
            </div>

            <div class="mb-3">
              <label class="form-label">Email (não editável)</label>
              <input name="email" type="email" class="form-control" value="<%= cliente.getEmail() %>" readonly>
            </div>

            <div class="mb-3">
              <label class="form-label">CPF</label>
              <input name="cpf" class="form-control" value="<%= cliente.getCpf() %>" required>
            </div>

            <hr>

            <div class="mb-3">
              <label class="form-label">Senha atual</label>
              <input name="senhaAtual" type="password" class="form-control" required>
              <div class="form-text">Informe sua senha atual para confirmar as alterações.</div>
            </div>

            <div class="d-flex gap-2">
              <button class="btn btn-primary" type="submit">Salvar alterações</button>
              <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/perfil">Cancelar</a>
            </div>

          </form>
        </div>
      </div>

      <div class="small text-muted">
        Alterações só serão aplicadas após confirmação da senha atual.
      </div>
    </div>
  </div>
</main>

<footer class="bg-white footer-small mt-4">
  <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho.</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
  document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());
</script>
</body>
</html>