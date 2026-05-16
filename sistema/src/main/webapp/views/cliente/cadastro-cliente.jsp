<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/
bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card border-0 shadow rounded-4 p-4">
                <h2 class="fw-bold text-center mb-4">Criar conta (Cliente)</h2>
                <form id="cadastroForm" action="${pageContext.request.contextPath}/cadastro"
                method="post">
                    <input type="hidden"
                           name="tipoUsuario"
                           value="CLIENTE">
                    <div class="mb-3">
                        <label class="form-label">Nome completo</label>
                        <input type="text" name="nomeCompleto" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">E-mail</label>
                        <input type="email" name="email" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Senha</label>
                        <input type="password" name="senha" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Confirmar senha</label>
                        <input type="password" name="confirmarSenha" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Pergunta de segurança</label>
                        <select name="perguntaSeguranca" class="form-select" >
                            <option value="">
                                Selecione uma pergunta
                            </option>

                            <option value="nome_pet">
                                Qual o nome do seu primeiro pet?
                            </option>

                            <option value="cidade_nascimento">
                                Em qual cidade você nasceu?
                            </option>

                            <option value="comida_favorita">
                                Qual sua comida favorita?
                            </option>
                        </select>
                    </div>
                    <div class="mb-4">
                        <label class="form-label">Resposta</label>
                        <input type="text" name="respostaSeguranca" class="form-control">
                    </div>
                    <div class="d-grid">
                        <button class="btn btn-success">Criar conta</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>