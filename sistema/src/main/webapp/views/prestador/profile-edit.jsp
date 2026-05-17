<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Editar perfil - Prestador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-white bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">MS Meu Sobrinho</a>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/client-profile.jsp">Meu Perfil</a></li>
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="display:inline">
                        <button class="btn btn-outline-secondary">Sair</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <h4>Editar perfil do prestador</h4>

                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger">${requestScope.error}</div>
                    </c:if>
                    <c:if test="${not empty requestScope.success}">
                        <div class="alert alert-success">${requestScope.success}</div>
                    </c:if>

                    <!-- Espera-se que o Servlet coloque 'provider' no request com os campos abaixo -->
                    <c:set var="p" value="${requestScope.provider != null ? requestScope.provider : sessionScope.user}" />

                    <form action="${pageContext.request.contextPath}/provider/update" method="post" class="needs-validation" novalidate>
                        <!-- id do usuário (não editável) -->
                        <input type="hidden" name="userId" value="${p.id}" />

                        <div class="mb-3">
                            <label class="form-label">Nome</label>
                            <input name="name" class="form-control" value="${p.name}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email (não editável)</label>
                            <input name="email" type="email" class="form-control" value="${p.email}" readonly>
                        </div>

                        <div class="mb-3 row">
                            <div class="col-md-6">
                                <label class="form-label">Documento (CPF/CNPJ)</label>
                                <input name="doc" class="form-control" value="${p.doc}">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Cidade</label>
                                <input name="city" class="form-control" value="${p.city}">
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Categoria</label>
                            <select name="category" class="form-select" required>
                                <option value="">Selecione</option>
                                <option value="Eletricista" ${p.category == 'Eletricista' ? 'selected' : ''}>Eletricista</option>
                                <option value="Encanador" ${p.category == 'Encanador' ? 'selected' : ''}>Encanador</option>
                                <option value="Pedreiro" ${p.category == 'Pedreiro' ? 'selected' : ''}>Pedreiro</option>
                                <option value="Carpinteiro" ${p.category == 'Carpinteiro' ? 'selected' : ''}>Carpinteiro</option>
                                <option value="Outro" ${p.category == 'Outro' ? 'selected' : ''}>Outro</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descrição / Bio</label>
                            <textarea name="bio" class="form-control" rows="4">${p.bio}</textarea>
                        </div>

                        <div class="mb-3 row">
                            <div class="col-md-6">
                                <label class="form-label">Preço médio (R$)</label>
                                <input name="priceAvg" type="number" step="0.01" class="form-control" value="${p.priceAvg}">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Rating atual</label>
                                <input class="form-control" value="${p.ratingAvg != null ? p.ratingAvg : '—'}" readonly>
                            </div>
                        </div>

                        <hr>

                        <h6>Alterar senha (opcional)</h6>
                        <div class="mb-3 row">
                            <div class="col">
                                <label class="form-label">Nova senha</label>
                                <input name="newPassword" type="password" class="form-control" placeholder="Deixe em branco para manter">
                            </div>
                            <div class="col">
                                <label class="form-label">Confirmar nova senha</label>
                                <input name="newPassword2" type="password" class="form-control" placeholder="Confirme a nova senha">
                            </div>
                        </div>

                        <hr>

                        <div class="mb-3">
                            <label class="form-label">Pergunta de segurança</label>
                            <select name="securityQuestion" class="form-select" required>
                                <option value="">Selecione uma pergunta</option>
                                <option value="nome_mae" ${p.securityQuestion == 'nome_mae' ? 'selected' : ''}>Qual o nome da sua mãe?</option>
                                <option value="cidade_natal" ${p.securityQuestion == 'cidade_natal' ? 'selected' : ''}>Qual sua cidade natal?</option>
                                <option value="primeiro_pet" ${p.securityQuestion == 'primeiro_pet' ? 'selected' : ''}>Qual o nome do seu primeiro pet?</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Resposta</label>
                            <input name="securityAnswer" type="text" class="form-control" placeholder="Preencha para atualizar a resposta">
                            <div class="form-text">Se não quiser alterar a resposta, deixe em branco.</div>
                        </div>

                        <div class="d-flex gap-2">
                            <button class="btn btn-primary" type="submit">Salvar alterações</button>
                            <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/profile.jsp?providerId=${p.id}">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>

            <div class="small text-muted">Alterações serão aplicadas ao seu perfil público. Em produção, senhas são sempre armazenadas como hash.</div>
        </div>
    </div>
</main>

<footer class="bg-white footer-small mt-4">
    <div class="container text-center text-muted">© <span id="year"></span> Meu Sobrinho.</div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // simple client-side validation for password match
    (function(){
        const form = document.querySelector('form[action$="/provider/update"]');
        if(!form) return;
        form.addEventListener('submit', function(e){
            const p1 = form.querySelector('input[name="newPassword"]').value;
            const p2 = form.querySelector('input[name="newPassword2"]').value;
            if(p1 || p2){
                if(p1 !== p2){
                    e.preventDefault();
                    alert('As novas senhas não coincidem.');
                } else if(p1.length > 0 && p1.length < 6){
                    e.preventDefault();
                    alert('A nova senha deve ter ao menos 6 caracteres.');
                }
            }
        });
    })();

    document.getElementById && (document.getElementById('year').textContent = new Date().getFullYear());
</script>
</body>
</html>
