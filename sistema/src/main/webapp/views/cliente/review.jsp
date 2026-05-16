<%@ page contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8" %>
<html lang="pt-BR">
<head>
    <meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Avaliação — Meu Sobrinho</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/custom.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="card card-shadow" style="max-width:720px;margin:auto">
        <div class="card-body">
            <h5>Avaliar serviço</h5>
            <p class="small-muted">Avalie o prestador e deixe um comentário.</p>
            <form class="needs-validation" novalidate onsubmit="event.preventDefault(); alert('Avaliação enviada (demo)'); window.location.href='client-profile.html'">
                <div class="mb-3">
                    <label class="form-label">Nota</label>
                    <select class="form-select" required>
                        <option value="">Selecione</option>
                        <option>5</option><option>4</option><option>3</option><option>2</option><option>1</option>
                    </select>
                    <div class="invalid-feedback">Escolha uma nota.</div>
                </div>
                <div class="mb-3">
                    <label class="form-label">Comentário</label>
                    <textarea class="form-control" rows="4"></textarea>
                </div>
                <button class="btn btn-primary" type="submit">Enviar avaliação</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
