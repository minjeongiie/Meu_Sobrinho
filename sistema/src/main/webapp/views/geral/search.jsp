<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <title>Buscar Profissionais</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="${pageContext.request.contextPath}/assets/css/custom.css"
          rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">

    <div class="container">

        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/views/geral/home.jsp">
            MS Meu Sobrinho
        </a>

        <form class="d-flex ms-3"
              action="${pageContext.request.contextPath}/buscar-prestadores"
              method="get">

            <input name="q"
                   class="form-control me-2"
                   placeholder="Buscar profissional"
                   value="${param.q}">

            <button class="btn btn-outline-secondary"
                    type="submit">
                Buscar
            </button>

        </form>

    </div>

</nav>

<main class="container py-4">

    <div id="resultsList" class="col-12">

        <c:choose>

            <c:when test="${empty results}">

                <div class="card">
                    <div class="card-body text-center">

                        Nenhum profissional encontrado.

                    </div>
                </div>

            </c:when>

            <c:otherwise>

                <c:forEach var="p"
                           items="${results}">

                    <div class="card mb-3">

                        <div class="card-body d-flex justify-content-between align-items-center">

                            <div>

                                <h5 class="mb-1">
                                        ${p.nomeCompleto}
                                </h5>

                                <c:if test="${not empty p.categoria}">
                                    <div class="text-muted mb-2">
                                        Categoria:
                                            ${p.categoria.nome}
                                    </div>
                                </c:if>

                                <div class="mb-2">
                                    <strong>Avaliação:</strong>

                                    <c:choose>
                                        <c:when test="${totaisAvaliacoes[p.id] > 0}">
                                            ${mediasAvaliacoes[p.id]} / 5
                                            (${totaisAvaliacoes[p.id]} avaliações)
                                        </c:when>

                                        <c:otherwise>
                                            Ainda sem avaliações
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div>
                                        ${p.descricao}
                                </div>

                            </div>

                            <div class="text-end">

                                <div class="mb-2">

                                    <strong>
                                        R$ ${p.valorMedio}
                                    </strong>

                                </div>

                                <a href="${pageContext.request.contextPath}/perfil-prestador?id=${p.id}"
                                   class="btn btn-outline-primary">

                                    Ver Perfil

                                </a>

                            </div>

                        </div>

                    </div>

                </c:forEach>

            </c:otherwise>

        </c:choose>

    </div>

</main>

<footer class="bg-white footer-small">

    <div class="container text-center text-muted">
        © <span id="year"></span> Meu Sobrinho.
    </div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById &&
    (document.getElementById('year').textContent =
        new Date().getFullYear());
</script>

</body>
</html>