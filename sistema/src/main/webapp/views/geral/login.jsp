<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html lang="pt-BR">
<head>
    <meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Entrar — Meu Site</title>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
<div class="container center" style="min-height:70vh; align-items:center">
    <div style="width:100%; max-width:420px">
        <div class="card">
            <h2>Entrar</h2>
            <form id="loginForm" class="grid">
                <label class="small">Email
                    <input class="input" type="email" name="email" required>
                </label>
                <label class="small">Senha
                    <input class="input" type="password" name="password" required>
                </label>
                <button class="btn" type="submit">Entrar</button>
            </form>
            <p class="small" style="margin-top:12px">Ainda não tem conta? <a href="contact.html">Fale conosco</a></p>
        </div>
    </div>
</div>
<script src="assets/js/app.js"></script>
</body>
</html>
