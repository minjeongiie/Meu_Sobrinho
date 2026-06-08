<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Entrar - Meu Sobrinho</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/views/geral/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/views/geral/register.jsp">Cadastrar</a></li>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4 d-flex align-items-center justify-content-center" style="min-height:70vh">
    <div class="card" style="max-width:480px;width:100%">
        <div class="card-body">
            <h4 class="card-title mb-3">Entrar</h4>

            <c:if test="${not empty param.registered}">
                <div class="alert alert-success">Conta criada com sucesso. Faça login.</div>
            </c:if>
            <c:if test="${not empty requestScope.erro}">
                <div class="alert alert-danger">${requestScope.error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input name="email" type="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Senha</label>
                    <input name="password" type="password" class="form-control" required>
                </div>
                <div class="d-flex gap-2 mb-3">
                    <button class="btn btn-primary w-100" type="submit">Entrar</button>
                    <a href="${pageContext.request.contextPath}/views/geral/register.jsp" class="btn btn-outline-secondary">Criar conta</a>
                </div>
                <div class="d-flex justify-content-between small-muted">
                    <a href="${pageContext.request.contextPath}/views/geral/recover.jsp">Esqueci minha senha</a>
                    <a href="${pageContext.request.contextPath}/views/prestador/register-provider.jsp">Sou prestador</a>
                </div>
            </form>
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
