<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>Home - Meu Sobrinho</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
  <div class="container">
    <a class="navbar-brand header-brand" href="${pageContext.request.contextPath}/home.jsp">
      <div class="brand-badge">MS</div>
      <div>
        <div class="fw-bold">Meu Sobrinho</div>
        <small class="small-muted">Conectando iniciantes de TI</small>
      </div>
    </a>

    <form class="d-none d-lg-flex ms-3" action="${pageContext.request.contextPath}/search" method="get">
      <div class="input-group search-input">
        <input name="q" class="form-control" placeholder="Buscar por serviço, tecnologia ou cidade" value="${param.q}">
        <button class="btn btn-outline-secondary" type="submit">Buscar</button>
      </div>
    </form>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMain">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navMain">
      <ul class="navbar-nav ms-auto align-items-lg-center">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/search.jsp">Buscar</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home.jsp#about">Sobre</a></li>
        <c:choose>
          <c:when test="${not empty sessionScope.user}">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/client-profile.jsp">Meu Perfil</a></li>
            <li class="nav-item">
              <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="display:inline">
                <button class="btn btn-outline-secondary">Sair</button>
              </form>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="btn btn-outline-primary ms-lg-3" href="${pageContext.request.contextPath}/views/geral/login.jsp">Entrar</a></li>
            <li class="nav-item"><a class="btn btn-primary ms-2" href="${pageContext.request.contextPath}/views/geral/register.jsp">Cadastrar</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<main class="container py-4">
  <div class="row align-items-center">
    <div class="col-lg-7">
      <h1 class="display-6">Encontre profissionais iniciantes com confiança</h1>
      <p class="text-muted">Perfis com portfólio, avaliações e contato — serviços acessíveis para pequenas demandas.</p>
      <div class="mt-3">
        <a href="${pageContext.request.contextPath}/views/geral/search.jsp" class="btn btn-primary me-2">Buscar profissionais</a>
        <a href="${pageContext.request.contextPath}/views/prestador/register-provider.jsp" class="btn btn-outline-secondary">Quero oferecer serviços</a>
      </div>
    </div>
    <div class="col-lg-5">
      <div class="card card-shadow">
        <div class="card-body">
          <h5 class="card-title">Para prestadores</h5>
          <p class="small-muted">Crie seu perfil, adicione portfólio e receba pedidos.</p>
          <a href="${pageContext.request.contextPath}/views/prestador/register-provider.jsp" class="btn btn-primary w-100">Criar perfil</a>
        </div>
      </div>
    </div>
  </div>
</main>

<footer class="bg-white footer-small">
  <div class="container text-center text-muted">
    © <span id="year"></span> Meu Sobrinho. Todos os direitos reservados.
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());</script>
</body>
</html>
