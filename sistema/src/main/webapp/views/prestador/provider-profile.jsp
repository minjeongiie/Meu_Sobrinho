<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.Usuario" %>
<%@ page import="model.entity.Prestador" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null || !(usuario instanceof Prestador)) {
        response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
        return;
    }

    Prestador prestador = (Prestador) usuario;
%>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Meu Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/views/geral/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
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
    <div class="row">
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <h5>Olá, <%= usuario.getNomeCompleto() %></h5>
                    <p class="small-muted">Gerencie seu perfil profissional.</p>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <h6>Dados do Prestador</h6>

                    <div class="mb-2">
                        <strong>Nome:</strong> <%= usuario.getNomeCompleto() %>
                    </div>

                    <div class="mb-2">
                        <strong>Email:</strong> <%= usuario.getEmail() %>
                    </div>

                    <div class="mb-2">
                        <strong>CPF/CNPJ:</strong> <%= prestador.getCpfCnpj() %>
                    </div>

                    <div class="mb-2">
                        <strong>Celular:</strong> <%= prestador.getCelular() %>
                    </div>

                    <div class="mb-2">
                        <strong>Categoria:</strong>
                        <%= prestador.getCategoria() != null ? prestador.getCategoria().getNome() : "Não informada" %>
                    </div>

                    <div class="mb-2">
                        <strong>Descrição:</strong> <%= prestador.getDescricao() %>
                    </div>

                    <div class="mb-2">
                        <strong>Valor médio:</strong> R$ <%= prestador.getValorMedio() %>
                    </div>

                    <div class="mb-2">
                        <strong>Perfil:</strong>
                        <%= prestador.isPerfilPublico() ? "Público" : "Privado" %>
                    </div>

                    <a href="${pageContext.request.contextPath}/editar-perfil"
                       class="btn btn-primary mt-3">
                        Editar Perfil
                    </a>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="bg-white footer-small">
    <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho.</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());</script>
</body>
</html>