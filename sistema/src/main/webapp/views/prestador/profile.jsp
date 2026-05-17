<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Perfil do Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
    </div>
</nav>

<main class="container py-4">
    <c:if test="${not empty provider}">
        <div class="card">
            <div class="card-body">
                <h4>${provider.name}</h4>
                <div class="small-muted mb-2">${provider.category} • ${provider.city}</div>
                <p>${provider.bio}</p>
                <div class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/hire.jsp?providerId=${provider.id}" class="btn btn-primary">Contratar</a>
                    <a href="${pageContext.request.contextPath}/search.jsp" class="btn btn-outline-secondary">Voltar</a>
                </div>
            </div>
        </div>
    </c:if>
</main>

<footer class="bg-white footer-small">
    <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho.</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());</script>
</body>
</html>
