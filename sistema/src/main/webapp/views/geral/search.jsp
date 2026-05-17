<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Buscar profissionais</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
        <form class="d-flex ms-3" action="${pageContext.request.contextPath}/search" method="get">
            <input name="q" class="form-control me-2" placeholder="Buscar por serviço, tecnologia ou cidade" value="${param.q}">
            <button class="btn btn-outline-secondary" type="submit">Buscar</button>
        </form>
    </div>
</nav>

<main class="container py-4">
    <div id="resultsList" class="col-12">
        <c:choose>
            <c:when test="${empty results}">
                <div class="empty-state card-shadow p-4">Nenhum profissional encontrado. Tente outro filtro.</div>
            </c:when>
            <c:otherwise>
                <c:forEach var="p" items="${results}">
                    <div class="card mb-2">
                        <div class="card-body d-flex justify-content-between align-items-center">
                            <div>
                                <h6 class="mb-0">${p.name} <small class="text-muted">— ${p.category}</small></h6>
                                <div class="small-muted">${p.city} • ${p.rating != null ? p.rating : '—'} ★</div>
                            </div>
                            <div class="text-end">
                                <div class="result-price">R$ ${p.price != null ? p.price : '—'}</div>
                                <a href="${pageContext.request.contextPath}/profile.jsp?providerId=${p.id}" class="btn btn-sm btn-outline-primary mt-2">Ver perfil</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<footer class="bg-white footer-small">
    <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho.</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());</script>
</body>
</html>
