<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Recuperar senha</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
    </div>
</nav>

<main class="container py-4 d-flex align-items-center justify-content-center" style="min-height:70vh">
    <div class="card" style="max-width:520px;width:100%">
        <div class="card-body">
            <h5>Recuperar senha</h5>

            <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger">${requestScope.error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/auth/recover" method="post" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input id="recoverEmail" name="email" type="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Pergunta de segurança</label>
                    <div id="recoverQuestionText" class="small-muted mb-2">${recoverQuestion}</div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Resposta</label>
                    <input id="recoverAnswer" name="answer" type="text" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Nova senha</label>
                    <input id="recoverNewPassword" name="newPassword" type="password" class="form-control" required>
                </div>

                <div class="d-flex gap-2">
                    <button class="btn btn-primary" type="submit">Redefinir senha</button>
                    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/login.jsp">Voltar ao login</a>
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
