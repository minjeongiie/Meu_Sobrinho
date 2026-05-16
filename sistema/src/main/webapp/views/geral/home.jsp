<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Meu Sobrinho</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/
bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fontawesome/6.5.1/css/all.min.css">
</head>
<body>
<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg bg-white shadow-sm py-3">
  <div class="container">
    <a class="navbar-brand fw-bold text-success" href="#">
      <i class="fa-solid fa-screwdriver-wrench"></i> Meu Sobrinho
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#menu">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="menu">
      <ul class="navbar-nav mx-auto">
        <li class="nav-item"><a class="nav-link" href="#">Como funciona</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Categorias</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Seja um prestador</a></li>
      </ul>
      <div class="d-flex gap-2">
        <a href="${pageContext.request.contextPath}/views/geral/login.jsp" class="btn btn-outline-success">Entrar</a>
        <a href="${pageContext.request.contextPath}/views/cliente/cadastro-cliente.jsp" class="btn btn-success">Cadastrar</a>
      </div>
    </div>
  </div>
</nav>
<!-- HERO -->
<section class="hero py-5">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-lg-6">
        <h1 class="display-5 fw-bold mb-3">
          Encontre o profissional ideal para o que você precisa
        </h1>
        <p class="text-muted mb-4">
          Pesquise pela area de atuação e encontre os melhores prestadores
          perto de você.
        </p>
        <div class="card p-3 shadow-sm border-0">
          <div class="row g-2">
            <div class="col-md-2">
              <select class="form-select">
                <option>frontend</option>
                <option>backend</option>
                <option>Manutenção</option>
                <option>Infraestrutura</option>
              </select>
            </div>
            <div class="col-md-2 d-grid">
              <button class="btn btn-success btn-search">Buscar</button>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-6 text-center mt-4 mt-lg-0">
        <img src="https://images.unsplash.com/photo-1581578731548-
c64695cc6952?q=80&w=1200&auto=format&fit=crop" class="img-fluid rounded-4
shadow" alt="profissional">
      </div>
    </div>
  </div>
</section>
<!-- CATEGORIAS -->
<section class="py-5 bg-light">
  2
  <div class="container">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="fw-bold">Categorias populares</h2>
      <a href="#" class="text-success text-decoration-none">Ver todas</a>
    </div>
    <div class="row g-3">
      <div class="col-md-2 col-6">
        <div class="categoria-card text-center p-3 bg-white rounded-4 shadowsm">
          <i class="fa-solid fa-bolt fa-2x text-warning mb-2"></i>
          <h6>frontend</h6>
        </div>
      </div>
      <div class="col-md-2 col-6">
        <div class="categoria-card text-center p-3 bg-white rounded-4 shadowsm">
          <i class="fa-solid fa-paint-roller fa-2x text-primary mb-2"></i>
          <h6>backend</h6>
        </div>
      </div>
      <div class="col-md-2 col-6">
        <div class="categoria-card text-center p-3 bg-white rounded-4 shadowsm">
          <i class="fa-solid fa-faucet-drip fa-2x text-info mb-2"></i>
          <h6>Manutenção</h6>
        </div>
      </div>
      <div class="col-md-2 col-6">
        <div class="categoria-card text-center p-3 bg-white rounded-4 shadowsm">
          <i class="fa-solid fa-hammer fa-2x text-danger mb-2"></i>
          <h6>Infraestrutura</h6>
        </div>
      </div>
      <div class="col-md-2 col-6">
        <div class="categoria-card text-center p-3 bg-white rounded-4 shadowsm">
          <i class="fa-solid fa-screwdriver fa-2x text-success mb-2"></i>
          <h6>UI/UX</h6>
        </div>
      </div>
    </div>
  </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/
bootstrap.bundle.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>