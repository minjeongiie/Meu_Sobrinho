<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Contratar prestador</title>
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
    <div class="card" style="max-width:720px;margin:auto">
        <div class="card-body">
            <h5>Contratar</h5>

            <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger">${requestScope.error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/solicitar-contratacao" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="providerId" value="${param.providerId}">

                <div class="mb-3">
                    <label class="form-label">Descrição</label>
                    <textarea name="description" class="form-control" rows="4" required></textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Preço sugerido (R$)</label>
                    <input name="price" type="number" step="0.01" min="0" class="form-control">
                </div>

                <div class="mb-3">
                    <label class="form-label">Data solicitada</label>
                    <input name="requestedDate" type="date" class="form-control">
                </div>

                <button class="btn btn-primary" type="submit">Enviar pedido</button>
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
