<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Perfil do Prestador</title>

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

    </div>
</nav>

<main class="container py-4">

    <c:if test="${not empty prestador}">

        <div class="card">

            <div class="card-body">

                <c:if test="${param.contratacao == 'sucesso'}">
                    <div class="alert alert-success">
                        Solicitação de contratação enviada com sucesso.
                    </div>
                </c:if>

                <h4>${prestador.nomeCompleto}</h4>

                <div class="small-muted mb-3">

                    <c:if test="${not empty prestador.categoria}">
                        Categoria:
                        ${prestador.categoria.nome}
                    </c:if>

                </div>

                <h6>Sobre</h6>

                <p>
                        ${prestador.descricao}
                </p>

                <c:if test="${not empty prestador.portfolio}">

                    <h6>Portfólio</h6>

                    <p>
                            ${prestador.portfolio}
                    </p>

                </c:if>

                <hr>

                <h6>Avaliações</h6>

                <div class="mb-3">

                    <strong>Nota média:</strong>

                    <c:choose>
                        <c:when test="${totalAvaliacoes > 0}">
                            ${mediaAvaliacoes} / 5
                            (${totalAvaliacoes} avaliações)
                        </c:when>

                        <c:otherwise>
                            Ainda não possui avaliações.
                        </c:otherwise>
                    </c:choose>

                </div>

                <c:if test="${not empty avaliacoes}">

                    <c:forEach var="avaliacao" items="${avaliacoes}">

                        <div class="border rounded p-3 mb-3">

                            <div class="mb-2">
                                <strong>Nota:</strong>
                                    ${avaliacao.nota}/5
                            </div>

                            <c:if test="${not empty avaliacao.comentario}">
                                <div>
                                    <strong>Comentário:</strong>
                                        ${avaliacao.comentario}
                                </div>
                            </c:if>

                        </div>

                    </c:forEach>

                </c:if>

                <div class="mt-4">

                    <c:if test="${ehCliente}">
                        <a href="${pageContext.request.contextPath}/views/cliente/hire.jsp?providerId=${prestador.id}"
                           class="btn btn-primary">
                            Contratar
                        </a>
                    </c:if>

                    <a href="javascript:history.back()"
                       class="btn btn-outline-secondary">
                        Voltar
                    </a>

                </div>

            </div>

        </div>

    </c:if>

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