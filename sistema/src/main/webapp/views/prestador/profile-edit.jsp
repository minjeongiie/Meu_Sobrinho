<%@ page contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8" %>
<html lang="pt-BR">
<head>
    <meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Editar Perfil — Meu Sobrinho</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/custom.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="index.html">Meu Sobrinho</a>
        <div class="ms-auto">
            <button id="logoutBtn" class="btn btn-outline-secondary">Sair</button>
        </div>
    </div>
</nav>

<main class="container py-4">
    <div class="card card-shadow">
        <div class="card-body">
            <h5>Editar Perfil</h5>
            <form class="needs-validation" novalidate>
                <div class="mb-3">
                    <label class="form-label">Descrição</label>
                    <textarea class="form-control" rows="4"></textarea>
                </div>
                <div class="mb-3 row">
                    <div class="col-md-4">
                        <label class="form-label">Categoria</label>
                        <select class="form-select"><option>Manutenção</option><option>Frontend</option><option>Infraestrutura</option><option>Backend</option></select>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Preço médio (R$)</label>
                        <input class="form-control" type="number" value="150">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Cidade</label>
                        <input class="form-control" value="Seropédica">
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Portfólio (links ou arquivos)</label>
                    <input class="form-control" type="file" multiple>
                </div>

                <button class="btn btn-primary" type="submit">Salvar</button>
            </form>
        </div>
    </div>
</main>

<footer class="bg-white footer-small">
    <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/app.js"></script>
</body>
</html>
