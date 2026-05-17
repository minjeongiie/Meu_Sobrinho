<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="display:inline">
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
                    <h5>Olá, ${sessionScope.user.name}</h5>
                    <p class="small-muted">Gerencie seus pedidos e perfil.</p>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <h6>Meus pedidos</h6>
                    <c:forEach var="job" items="${myJobs}">
                        <div class="border rounded p-2 mb-2">
                            <div class="d-flex justify-content-between">
                                <div>
                                    <strong>${job.providerName}</strong>
                                    <div class="small-muted">${job.description}</div>
                                </div>
                                <div class="text-end">
                                    <div class="small-muted">${job.status}</div>
                                    <c:if test="${job.status == 'pending'}">
                                        <a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/client-profile.jsp">Acompanhar</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h6>Dados</h6>
                    <div><strong>Email:</strong> ${sessionScope.user.email}</div>
                    <div><strong>Cidade:</strong> ${sessionScope.user.city}</div>
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
