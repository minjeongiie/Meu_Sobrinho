<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.Usuario" %>
<%@ page import="model.entity.Prestador" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

    if (usuario == null || !(usuario instanceof Prestador)) {
        response.sendRedirect(request.getContextPath() + "/views/geral/login.jsp");
        return;
    }

    Prestador prestador = (Prestador) usuario;
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
            Solicitação aceita com sucesso.
        </div>
    </c:if>
    
    <c:if test="${param.contratacao == 'concluida'}">
        <div class="alert alert-success">
            Contratação concluída com sucesso.
        </div>
    </c:if>
    <c:if test="${param.contratacao == 'recusada'}">
        <div class="alert alert-warning">
            Solicitação recusada com sucesso.
        </div>
    </c:if>
    <c:if test="${param.contratacao == 'contraproposta'}">
        <div class="alert alert-info">
            Contraproposta enviada com sucesso.
        </div>
    </c:if>

    <div class="row">
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <h5>Olá, <%= usuario.getNomeCompleto() %></h5>
                    <p class="small-muted">Gerencie seu perfil profissional.</p>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/servicos-disponiveis"
               class="btn btn-primary mt-3">Ver Serviços Disponíveis</a>

            <div class="card">
                <div class="card-body">
                    <h6>Dados do Prestador</h6>

                    <div class="mb-2">
                        <strong>Nome:</strong> <%= usuario.getNomeCompleto() %>
                    </div>

                    <div class="mb-2">
                        <strong>Email:</strong> <%= usuario.getEmail() %>
                    </div>

                    <div class="mb-2">
                        <strong>CPF/CNPJ:</strong> <%= prestador.getCpfCnpj() %>
                    </div>

                    <div class="mb-2">
                        <strong>Celular:</strong> <%= prestador.getCelular() %>
                    </div>

                    <div class="mb-2">
                        <strong>Categoria:</strong>
                        <%= prestador.getCategoria() != null ? prestador.getCategoria().getNome() : "Não informada" %>
                    </div>

                    <div class="mb-2">
                        <strong>Descrição:</strong> <%= prestador.getDescricao() %>
                    </div>

                    <div class="mb-2">
                        <strong>Valor médio:</strong> R$ <%= prestador.getValorMedio() %>
                    </div>

                    <div class="mb-2">
                        <strong>Perfil:</strong>
                        <%= prestador.isPerfilPublico() ? "Público" : "Privado" %>
                    </div>

                    <a href="${pageContext.request.contextPath}/editar-perfil"
                       class="btn btn-primary mt-3">
                        Editar Perfil
                    </a>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Solicitações recebidas</h6>

                    <c:set var="temPendentes" value="false" />

                    <c:forEach var="contratacao" items="${contratacoes}">
                        <c:if test="${contratacao.status == 'PENDENTE'}">
                            <c:set var="temPendentes" value="true" />

                            <div class="border rounded p-3 mb-3">

                                <div class="mb-2">
                                    <strong>Cliente:</strong>
                                        ${nomesClientes[contratacao.clienteId]}
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
                                    PENDENTE
                                </div>

                                <form action="${pageContext.request.contextPath}/gerenciar-contratacao"
                                      method="post"
                                      class="mt-2">

                                    <input type="hidden"
                                           name="contratacaoId"
                                           value="${contratacao.id}">

                                    <input type="hidden"
                                           name="acao"
                                           value="aceitar">

                                    <button type="submit"
                                            class="btn btn-success btn-sm">
                                        Aceitar
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
                                        Recusar
                                    </button>
                                </form>
                                <button class="btn btn-outline-primary btn-sm"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#contraproposta-${contratacao.id}">
                                    Enviar contraproposta
                                </button>

                                <div class="collapse mt-3"
                                     id="contraproposta-${contratacao.id}">

                                    <form action="${pageContext.request.contextPath}/gerenciar-contratacao"
                                          method="post">

                                        <input type="hidden"
                                               name="contratacaoId"
                                               value="${contratacao.id}">

                                        <input type="hidden"
                                               name="acao"
                                               value="contraproposta">

                                        <div class="mb-2">
                                            <label class="form-label">
                                                Valor da contraproposta
                                            </label>

                                            <input type="number"
                                                   name="valorContraproposta"
                                                   step="0.01"
                                                   min="0"
                                                   class="form-control"
                                                   required>
                                        </div>

                                        <div class="mb-2">
                                            <label class="form-label">
                                                Mensagem da contraproposta
                                            </label>

                                            <textarea name="mensagemContraproposta"
                                                      class="form-control"
                                                      rows="3"
                                                      required></textarea>
                                        </div>

                                        <button type="submit"
                                                class="btn btn-primary btn-sm">
                                            Confirmar contraproposta
                                        </button>
                                    </form>

                                </div>

                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${not temPendentes}">
                        <p class="text-muted mb-0">
                            Nenhuma solicitação pendente.
                        </p>
                    </c:if>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Contrapropostas enviadas</h6>

                    <c:set var="temContrapropostas" value="false" />

                    <c:forEach var="contratacao" items="${contratacoes}">
                        <c:if test="${contratacao.status == 'CONTRAPROPOSTA'}">
                            <c:set var="temContrapropostas" value="true" />

                            <div class="border rounded p-3 mb-3">

                                <div class="mb-2">
                                    <strong>Cliente:</strong>
                                        ${nomesClientes[contratacao.clienteId]}
                                </div>

                                <div class="mb-2">
                                    <strong>Descrição:</strong>
                                        ${contratacao.descricao}
                                </div>

                                <div class="mb-2">
                                    <strong>Preço sugerido pelo cliente:</strong>
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
                                    <strong>Valor da contraproposta:</strong>
                                    R$ ${contratacao.valorContraproposta}
                                </div>

                                <div class="mb-2">
                                    <strong>Mensagem da contraproposta:</strong>
                                        ${contratacao.mensagemContraproposta}
                                </div>

                                <div class="mb-2">
                                    <strong>Status:</strong>
                                    Aguardando resposta do cliente
                                </div>

                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${not temContrapropostas}">
                        <p class="text-muted mb-0">
                            Nenhuma contraproposta enviada.
                        </p>
                    </c:if>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Contratações aceitas</h6>

                    <c:set var="temAceitas" value="false" />

                    <c:forEach var="contratacao" items="${contratacoes}">
                        <c:if test="${contratacao.status == 'ACEITA'}">
                            <c:set var="temAceitas" value="true" />

                            <div class="border rounded p-3 mb-3">

                                <div class="mb-2">
                                    <strong>Cliente:</strong>
                                        ${nomesClientes[contratacao.clienteId]}
                                </div>

                                <div class="mb-2">
                                    <strong>Descrição:</strong>
                                        ${contratacao.descricao}
                                </div>

                                <div class="mb-2">
                                    <strong>Preço:</strong>
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
                                    Em andamento
                                </div>

                                <form action="${pageContext.request.contextPath}/gerenciar-contratacao"
                                      method="post"
                                      class="mt-2">

                                    <input type="hidden"
                                           name="contratacaoId"
                                           value="${contratacao.id}">

                                    <input type="hidden"
                                           name="acao"
                                           value="concluir">

                                    <button type="submit"
                                            class="btn btn-primary btn-sm">
                                        Concluir
                                    </button>
                                </form>
                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${not temAceitas}">
                        <p class="text-muted mb-0">
                            Nenhuma contratação aceita no momento.
                        </p>
                    </c:if>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Contratações concluídas</h6>

                    <c:set var="temConcluidas" value="false" />

                    <c:forEach var="contratacao" items="${contratacoes}">
                        <c:if test="${contratacao.status == 'CONCLUIDA'}">
                            <c:set var="temConcluidas" value="true" />

                            <div class="border rounded p-3 mb-3">

                                <div class="mb-2">
                                    <strong>Cliente:</strong>
                                        ${nomesClientes[contratacao.clienteId]}
                                </div>

                                <div class="mb-2">
                                    <strong>Descrição:</strong>
                                        ${contratacao.descricao}
                                </div>

                                <div class="mb-2">
                                    <strong>Preço:</strong>
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
                                    Concluída
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${not temConcluidas}">
                        <p class="text-muted mb-0">
                            Nenhuma contratação concluída ainda.
                        </p>
                    </c:if>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-body">
                    <h6>Contratações recusadas</h6>

                    <c:set var="temRecusadas" value="false" />

                    <c:forEach var="contratacao" items="${contratacoes}">
                        <c:if test="${contratacao.status == 'RECUSADA'}">
                            <c:set var="temRecusadas" value="true" />

                            <div class="border rounded p-3 mb-3">

                                <div class="mb-2">
                                    <strong>Cliente:</strong>
                                        ${nomesClientes[contratacao.clienteId]}
                                </div>

                                <div class="mb-2">
                                    <strong>Descrição:</strong>
                                        ${contratacao.descricao}
                                </div>

                                <div class="mb-2">
                                    <strong>Preço:</strong>
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
                                    Recusada
                                </div>

                            </div>
                        </c:if>
                    </c:forEach>

                    <c:if test="${not temRecusadas}">
                        <p class="text-muted mb-0">
                            Nenhuma contratação recusada.
                        </p>
                    </c:if>
                </div>
            </div>

        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h6>Minhas Avaliações</h6>

                    <div class="mb-3">
                        <strong>Nota média:</strong><br>

                        <c:choose>
                            <c:when test="${totalAvaliacoes > 0}">
                                ${mediaAvaliacoes} / 5
                                <br>
                                <span class="text-muted">
                            ${totalAvaliacoes} avaliações
                        </span>
                            </c:when>

                            <c:otherwise>
                        <span class="text-muted">
                            Ainda não possui avaliações.
                        </span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <c:if test="${not empty avaliacoes}">
                        <c:forEach var="avaliacao" items="${avaliacoes}">
                            <div class="border rounded p-2 mb-2">
                                <div>
                                    <strong>${avaliacao.nota}/5</strong>
                                </div>

                                <c:if test="${not empty avaliacao.comentario}">
                                    <div class="small text-muted">
                                            ${avaliacao.comentario}
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:if>
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