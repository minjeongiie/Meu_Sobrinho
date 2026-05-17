<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Avaliar prestador - Meu Sobrinho</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
    <style>
        .star-btn { font-size: 1.4rem; color: #ddd; border: none; background: transparent; }
        .star-btn.checked { color: #ffc107; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/client-profile.jsp">Meu Perfil</a></li>
                        <li class="nav-item">
                            <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="display:inline">
                                <button class="btn btn-outline-secondary">Sair</button>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Entrar</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Deixe sua avaliação</h5>

                    <c:if test="${not empty requestScope.error}">
                    <div class="alert alert-danger">${requestScope.error}</div>
                    </c:if>
                    <c:if test="${not empty requestScope.success}">
                    <div class="alert alert-success">${requestScope.success}</div>
                    </c:if>

                    <!-- Expecting providerId and jobId as request parameters or attributes -->
                    <c:set var="providerId" value="${param.providerId != null ? param.providerId : requestScope.providerId}" />
                    <c:set var="jobId" value="${param.jobId != null ? param.jobId : requestScope.jobId}" />

                    <form id="reviewForm" action="${pageContext.request.contextPath}/reviews" method="post" class="needs-validation" novalidate>
                        <input type="hidden" name="providerId" value="${providerId}" />
                        <input type="hidden" name="jobId" value="${jobId}" />

                        <div class="mb-3">
                            <label class="form-label">Nota</label>
                            <div id="starGroup" class="d-flex align-items-center">
                                <button type="button" class="star-btn" data-value="1" aria-label="1 estrela">★</button>
                                <button type="button" class="star-btn" data-value="2" aria-label="2 estrelas">★</button>
                                <button type="button" class="star-btn" data-value="3" aria-label="3 estrelas">★</button>
                                <button type="button" class="star-btn" data-value="4" aria-label="4 estrelas">★</button>
                                <button type="button" class="star-btn" data-value="5" aria-label="5 estrelas">★</button>
                                <input id="starsInput" name="stars" type="hidden" required>
                                <div class="ms-3 small text-muted" id="starsLabel">Selecione uma nota</div>
                            </div>
                        </div>

                        <div class="mb-3">