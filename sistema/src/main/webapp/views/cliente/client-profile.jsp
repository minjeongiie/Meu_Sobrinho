<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.Usuario" %>
<%@ page import="model.entity.Cliente" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null || !(usuario instanceof Cliente)) {
        response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
        return;
    }

    Cliente cliente = (Cliente) usuario;
%>

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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/views/geral/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/logout" method="get" style="display:inline">
                        <button class="btn btn-outline-secondary">Sair</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4">

    <c:if test="${param.contratacao == 'aceita'}">
        <div class="alert alert-success">
            Contraproposta aceita com sucesso.
        </div>
    </c:if>

    <c:if test="${param.contratacao == 'recusada'}">
        <div class="alert alert-warning">
            Contraproposta recusada.
        </div>
    </c:if>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <h5>Olá, <%= usuario.getNomeCompleto() %></h5>
                    <p class="small-muted">Gerencie seu perfil.</p>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <h6>Dados do Cliente</h6>

                    <div class="mb-2">
                        <strong>Nome:</strong> <%= usuario.getNomeCompleto() %>
                    </div>

                    <div class="mb-2">
                        <strong>Email:</strong> <%= usuario.getEmail() %>
                    </div>

                    <div class="mb-2">
                        <strong>CPF:</strong> <%= cliente.getCpf() %>
                    </div>

                    <a href="${pageContext.request.contextPath}/editar-perfil"
                       class="btn btn-primary mt-3">
                        Editar Perfil
                    </a>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Minhas contratações</h6>

                    <c:choose>
                        <c:when test="${empty contratacoes}">
                            <p class="text-muted mb-0">
                                Você ainda não solicitou nenhuma contratação.
                            </p>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="contratacao" items="${contratacoes}">
                                <div class="border rounded p-3 mb-3">

                                    <div class="mb-2">
                                        <strong>Prestador:</strong>
                                            ${nomesPrestadores[contratacao.prestadorId]}
                                    </div>

                                    <div class="mb-2">
                                        <strong>Descrição:</strong>
                                            ${contratacao.descricao}
                                    </div>

                                    <div class="mb-2">
                                        <strong>Preço sugerido:</strong>
                                        <c:choose>
                                            <c:when test="${not empty contratacao.preco}">
                                                R$ ${contratacao.preco}
                                            </c:when>
                                            <c:otherwise>
                                                Não informado
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="mb-2">
                                        <strong>Data solicitada:</strong>
                                        <c:choose>
                                            <c:when test="${not empty contratacao.dataSolicitada}">
                                                ${contratacao.dataSolicitada}
                                            </c:when>
                                            <c:otherwise>
                                                Não informada
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="mb-2">
                                        <strong>Status:</strong>
                                        <c:choose>
                                            <c:when test="${contratacao.status == 'PENDENTE'}">
                                                Aguardando resposta do prestador
                                            </c:when>
                                            <c:when test="${contratacao.status == 'CONTRAPROPOSTA'}">
                                                Contraproposta recebida
                                            </c:when>
                                            <c:when test="${contratacao.status == 'ACEITA'}">
                                                Em andamento
                                            </c:when>
                                            <c:when test="${contratacao.status == 'CONCLUIDA'}">
                                                Concluída
                                            </c:when>
                                            <c:when test="${contratacao.status == 'RECUSADA'}">
                                                Recusada
                                            </c:when>
                                            <c:otherwise>
                                                ${contratacao.status}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <c:if test="${contratacao.status == 'CONTRAPROPOSTA'}">
                                        <div class="alert alert-info mt-3 mb-2">

                                            <div class="mb-2">
                                                <strong>Valor da contraproposta:</strong>
                                                R$ ${contratacao.valorContraproposta}
                                            </div>

                                            <div class="mb-2">
                                                <strong>Mensagem do prestador:</strong>
                                                    ${contratacao.mensagemContraproposta}
                                            </div>

                                            <div class="d-flex gap-2 mt-2">

                                                <form action="${pageContext.request.contextPath}/gerenciar-contratacao"
                                                      method="post">

                                                    <input type="hidden"
                                                           name="contratacaoId"
                                                           value="${contratacao.id}">

                                                    <input type="hidden"
                                                           name="acao"
                                                           value="aceitar">

                                                    <button type="submit"
                                                            class="btn btn-success btn-sm">
                                                        Aceitar contraproposta
                                                    </button>
                                                </form>

                                                <form action="${pageContext.request.contextPath}/gerenciar-contratacao"
                                                      method="post">

                                                    <input type="hidden"
                                                           name="contratacaoId"
                                                           value="${contratacao.id}">

                                                    <input type="hidden"
                                                           name="acao"
                                                           value="recusar">

                                                    <button type="submit"
                                                            class="btn btn-danger btn-sm">
                                                        Recusar contraproposta
                                                    </button>
                                                </form>

                                            </div>
                                        </div>
                                    </c:if>

                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
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