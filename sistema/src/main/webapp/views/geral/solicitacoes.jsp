<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.entity.SolicitacaoServico" %>
<%@ page import="model.entity.Prestador" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Solicitações de Serviço</title>
</head>
<body>

<h1>Solicitações Abertas</h1>

<p style="color:red;">${error}</p>

<%
  List<SolicitacaoServico> solicitacoes =
          (List<SolicitacaoServico>) request.getAttribute("solicitacoes");

  Object usuarioLogado =
          session.getAttribute("usuarioLogado");
%>

<% if (solicitacoes == null || solicitacoes.isEmpty()) { %>

<p>Nenhuma solicitação aberta no momento.</p>

<% } else { %>

<% for (SolicitacaoServico solicitacao : solicitacoes) { %>

<div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">

  <h3><%= solicitacao.getTitulo() %></h3>

  <p><strong>Descrição:</strong> <%= solicitacao.getDescricao() %></p>
  <p><strong>Valor estimado:</strong> R$ <%= solicitacao.getValorEstimado() %></p>
  <p><strong>Categoria ID:</strong> <%= solicitacao.getCategoriaId() %></p>
  <p><strong>Data desejada:</strong> <%= solicitacao.getDataDesejada() %></p>
  <p><strong>Status:</strong> <%= solicitacao.getStatus() %></p>

  <% if (usuarioLogado instanceof Prestador) { %>

  <form action="${pageContext.request.contextPath}/enviar-proposta" method="post">

    <input type="hidden" name="solicitacaoId"
           value="<%= solicitacao.getId() %>">

    <label>Valor proposto:</label><br>
    <input type="number" step="0.01" name="valorProposto"><br><br>

    <label>Descrição da proposta:</label><br>
    <textarea name="descricao"></textarea><br><br>

    <label>Prazo de conclusão:</label><br>
    <input type="date" name="prazoConclusao"><br><br>

    <button type="submit">Enviar proposta</button>
  </form>

  <% } %>

</div>

<% } %>

<% } %>

<br>
<a href="${pageContext.request.contextPath}/perfil">Voltar ao perfil</a>

</body>
</html>