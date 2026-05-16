<%@ page contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8" %>
<html lang="pt-BR">
<head>
    <meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Cadastro — Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/custom.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card card-shadow">
                <div class="card-body">
                    <h4>Cadastrar como Prestador</h4>
                    <form id="registerForm" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label class="form-label">Nome completo</label>
                            <input name="name" class="form-control" required>
                            <div class="invalid-feedback">Informe seu nome.</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input name="email" type="email" class="form-control" required>
                            <div class="invalid-feedback">Informe um email válido.</div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-md-6">
                                <label class="form-label">Documento (CPF/CNPJ)</label>
                                <input name="doc" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Categoria</label>
                                <select name="category" class="form-select" required>
                                    <option value="">Selecione</option>
                                    <option>Frontend</option><option>Manutenção</option><option>Infraestrutura</option><option>Backend</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col">
                                <label class="form-label">Senha</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Confirmar senha</label>
                                <input name="password2" type="password" class="form-control" required>
                            </div>
                        </div>
                        <!-- Pergunta de segurança (sem opção "Outra") -->
                        <div class="mb-3">
                            <label class="form-label">Pergunta de segurança</label>
                            <select name="securityQuestion" class="form-select js-security-question" required>
                                <option value="">Selecione uma pergunta</option>
                                <option value="nome_mae">Qual o nome da sua mãe?</option>
                                <option value="cidade_natal">Qual sua cidade natal?</option>
                                <option value="primeiro_pet">Qual o nome do seu primeiro pet?</option>
                            </select>
                            <div class="invalid-feedback">Escolha uma pergunta de segurança.</div>
                        </div>

                        <!-- Resposta da pergunta de segurança -->
                        <div class="mb-3">
                            <label class="form-label">Resposta</label>
                            <input name="securityAnswer" type="text" class="form-control" required>
                            <div class="invalid-feedback">Informe a resposta para recuperação de senha.</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Descrição curta</label>
                            <textarea name="bio" class="form-control" rows="3"></textarea>
                        </div>

                        <button class="btn btn-primary" type="submit">Criar perfil</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/app.js"></script>
</body>
</html>
