<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Publicar Solicitação</title>
</head>
<body>

<h1>Publicar Solicitação de Serviço</h1>

<p style="color:red;">${error}</p>

<form action="${pageContext.request.contextPath}/publicar-solicitacao" method="post">

    <label>Título:</label><br>
    <input type="text" name="titulo"><br><br>

    <label>Descrição:</label><br>
    <textarea name="descricao"></textarea><br><br>

    <label>Valor estimado:</label><br>
    <input type="number" step="0.01" name="valorEstimado"><br><br>

    <label>Categoria ID:</label><br>
    <input type="number" name="categoriaId"><br><br>

    <label>Data desejada:</label><br>
    <input type="date" name="dataDesejada"><br><br>

    <button type="submit">Publicar</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/perfil">Voltar ao perfil</a>

</body>
</html>