<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.SolicitacaoServico" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Serviços Disponíveis</title>
    <!-- Bootstrap + CSS custom -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand header-brand" href="${pageContext.request.contextPath}/views/geral/home.jsp">
            <div class="brand-badge">
                <img src="${pageContext.request.contextPath}/assets/img/icon.png" alt="Meu Sobrinho">
            </div>
            <div>
                <div class="fw-bold">Meu Sobrinho</div>
                <small class="small-muted">Conectando iniciantes de TI</small>
            </div>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMain">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto align-items-lg-center">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/perfil">Meu Perfil</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Sair</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Conteúdo principal -->
<main class="container my-4">
    <h1>Serviços Disponíveis</h1>
    <p class="text-danger">${error}</p>

    <%
        List<SolicitacaoServico> solicitacoes =
                (List<SolicitacaoServico>) request.getAttribute("solicitacoes");
    %>

    <% if (solicitacoes == null || solicitacoes.isEmpty()) { %>
    <div class="empty-state">Nenhum serviço disponível no momento.</div>
    <% } else { %>
    <% for (SolicitacaoServico solicitacao : solicitacoes) { %>
    <div class="card-shadow mb-3 p-3">
        <h3><%= solicitacao.getTitulo() %></h3>
        <p><strong>Descrição:</strong> <%= solicitacao.getDescricao() %></p>
        <p><strong>Valor estimado:</strong> R$ <%= solicitacao.getValorEstimado() %></p>
        <p><strong>Categoria:</strong> <%= solicitacao.getCategoria().getNome() %></p>
        <p><strong>Data desejada:</strong> <%= solicitacao.getDataDesejada() %></p>
        <p><strong>Status:</strong> <%= solicitacao.getStatus() %></p>

        <!-- Botão para enviar proposta -->
        <a href="${pageContext.request.contextPath}/enviar-proposta?solicitacaoId=<%= solicitacao.getId() %>"
           class="btn btn-primary mt-2">Enviar Proposta</a>
    </div>
    <% } %>
    <% } %>
</main>

<!-- Footer -->
<footer>
    <div class="footer-small">© 2026 Meu Sobrinho - Todos os direitos reservados</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
