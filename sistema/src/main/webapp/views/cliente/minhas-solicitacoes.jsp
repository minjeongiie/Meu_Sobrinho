<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.SolicitacaoServico" %>
<%@ page import="model.entity.PropostaServico" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Minhas Solicitações</title>
</head>
<body>

<h1>Minhas Solicitações</h1>

<p style="color:red;">${error}</p>

<%
    List<SolicitacaoServico> solicitacoes =
            (List<SolicitacaoServico>) request.getAttribute("solicitacoes");

    Map<Long, List<PropostaServico>> propostasPorSolicitacao =
            (Map<Long, List<PropostaServico>>) request.getAttribute("propostasPorSolicitacao");
%>

<% if (solicitacoes == null || solicitacoes.isEmpty()) { %>

<p>Você ainda não publicou nenhuma solicitação.</p>

<% } else { %>

<% for (SolicitacaoServico solicitacao : solicitacoes) { %>

<div style="border:1px solid #ccc; padding:10px; margin-bottom:15px;">

    <h3><%= solicitacao.getTitulo() %></h3>

    <p><strong>Descrição:</strong> <%= solicitacao.getDescricao() %></p>
    <p><strong>Valor estimado:</strong> R$ <%= solicitacao.getValorEstimado() %></p>
    <p><strong>Categoria ID:</strong> <%= solicitacao.getCategoria().getNome() %></p>
    <p><strong>Data desejada:</strong> <%= solicitacao.getDataDesejada() %></p>
    <p><strong>Status:</strong> <%= solicitacao.getStatus() %></p>

    <h4>Propostas recebidas</h4>

    <%
        List<PropostaServico> propostas =
                propostasPorSolicitacao.get(solicitacao.getId());
    %>

    <% if (propostas == null || propostas.isEmpty()) { %>

    <p>Nenhuma proposta recebida ainda.</p>

    <% } else { %>

    <% for (PropostaServico proposta : propostas) { %>

    <div style="border:1px dashed #999; padding:10px; margin-bottom:10px;">

        <p><strong>Prestador ID:</strong> <%= proposta.getPrestadorId() %></p>
        <p><strong>Valor proposto:</strong> R$ <%= proposta.getValorProposto() %></p>
        <p><strong>Descrição:</strong> <%= proposta.getDescricao() %></p>
        <p><strong>Prazo:</strong> <%= proposta.getPrazoConclusao() %></p>
        <p><strong>Status:</strong> <%= proposta.getStatus() %></p>

        <% if ("PENDENTE".equals(proposta.getStatus().name())
                && "ABERTA".equals(solicitacao.getStatus().name())) { %>

        <form action="${pageContext.request.contextPath}/responder-proposta"
              method="post"
              style="display:inline;">

            <input type="hidden"
                   name="propostaId"
                   value="<%= proposta.getId() %>">

            <input type="hidden"
                   name="acao"
                   value="ACEITAR">

            <button type="submit">Aceitar</button>
        </form>

        <form action="${pageContext.request.contextPath}/responder-proposta"
              method="post"
              style="display:inline;">

            <input type="hidden"
                   name="propostaId"
                   value="<%= proposta.getId() %>">

            <input type="hidden"
                   name="acao"
                   value="RECUSAR">

            <button type="submit">Recusar</button>
        </form>

        <% } %>

    </div>

    <% } %>

    <% } %>

</div>

<% } %>

<% } %>

<br>

<a href="${pageContext.request.contextPath}/views/cliente/publicar-solicitacao.jsp">
    Publicar nova solicitação
</a>

<br><br>

<a href="${pageContext.request.contextPath}/listar-solicitacoes">
    Ver solicitações públicas
</a>

<br><br>

<a href="${pageContext.request.contextPath}/perfil">
    Voltar ao perfil
</a>

</body>
</html>