<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Cadastro - Cliente</title>
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
    <div class="row justify-content-center">
        <div class="col-md-7">
            <div class="card">
                <div class="card-body">
                    <h4>Cadastrar como Cliente</h4>

                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger">${requestScope.error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/cadastro" method="post" class="needs-validation" novalidate>
                        <input type="hidden"
                               name="tipoUsuario"
                               value="CLIENTE">
                        <div class="mb-3">
                            <label class="form-label">Nome completo</label>
                            <input name="name" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input name="email" type="email" class="form-control" required>
                        </div>
                        <div class="mb-3 row">
                            <div class="col">
                                <label class="form-label">Senha</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Confirmar senha</label>
                                <input name="password2" type="password" class="form-control" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Pergunta de segurança</label>
                            <select name="securityQuestion" class="form-select" required>
                                <option value="">Selecione uma pergunta</option>
                                <option value="nome_mae">Qual o nome da sua mãe?</option>
                                <option value="cidade_natal">Qual sua cidade natal?</option>
                                <option value="primeiro_pet">Qual o nome do seu primeiro pet?</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Resposta</label>
                            <input name="securityAnswer" type="text" class="form-control" required>
                        </div>

                        <button class="btn btn-primary" type="submit">Criar conta</button>
                    </form>
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
