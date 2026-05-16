// app.js - simulação mínima para navegação e dados (demo)
document.addEventListener('DOMContentLoaded', ()=>{

    // Atualiza ano no footer
    const yearEl = document.getElementById('year');
    if(yearEl) yearEl.textContent = new Date().getFullYear();

    // Simple auth simulation
    const loginForm = document.getElementById('loginForm');
    if(loginForm){
        loginForm.addEventListener('submit', e=>{
            e.preventDefault();
            const email = loginForm.email.value.trim();
            const pass = loginForm.password.value.trim();
            if(!email || !pass){ alert('Preencha email e senha.'); return; }
            // store user type if selected
            const type = loginForm.dataset.type || 'client';
            localStorage.setItem('ms_auth','true');
            localStorage.setItem('ms_user', JSON.stringify({email, type, name: email.split('@')[0]}));
            window.location.href = (type==='provider') ? 'profile.html' : 'client-profile.html';
        });
    }

    // Register (single page handles both types)
    const registerForm = document.getElementById('registerForm');
    if(registerForm){
        registerForm.addEventListener('submit', e=>{
            e.preventDefault();
            if(!registerForm.checkValidity()){ registerForm.classList.add('was-validated'); return; }
            alert('Conta criada (demo). Faça login.');
            window.location.href = 'login.html';
        });
    }

    // Protect dashboard-like pages
    const protectedPages = ['profile.html','profile-edit.html','messages.html','hire.html','dashboard.html','client-profile.html'];
    if(protectedPages.some(p=>location.pathname.endsWith(p))){
        if(localStorage.getItem('ms_auth') !== 'true'){
            // redirect to login but preserve intended page
            localStorage.setItem('ms_next', location.pathname);
            window.location.href = 'login.html';
        } else {
            // populate user name in navbar
            const user = JSON.parse(localStorage.getItem('ms_user')||'null');
            if(user){
                document.querySelectorAll('.nav-user-name').forEach(el=>el.textContent = user.name);
            }
        }
    }

    // Logout
    const logoutBtn = document.getElementById('logoutBtn');
    if(logoutBtn) logoutBtn.addEventListener('click', ()=>{
        localStorage.removeItem('ms_auth');
        localStorage.removeItem('ms_user');
        window.location.href = 'index.html';
    });

    // Contact / message forms
    document.querySelectorAll('form.needs-validation').forEach(f=>{
        f.addEventListener('submit', e=>{
            if(!f.checkValidity()){ e.preventDefault(); e.stopPropagation(); f.classList.add('was-validated'); return; }
            e.preventDefault();
            alert('Enviado (demo).');
            f.reset();
            f.classList.remove('was-validated');
        });
    });

    // Simple search demo: populate results if q param present
    if(location.pathname.endsWith('search.html')){
        const params = new URLSearchParams(location.search);
        const q = params.get('q') || '';
        const resultsList = document.getElementById('resultsList');
        if(resultsList){
            // sample data
            const sample = [
                {id:1,name:'João Silva',cat:'Eletricista',city:'Seropédica',price:150,rating:4.8},
                {id:2,name:'Maria Souza',cat:'Encanador',city:'Nova Iguaçu',price:120,rating:4.6},
                {id:3,name:'Carlos Lima',cat:'Pedreiro',city:'Seropédica',price:200,rating:4.4}
            ];
            const filtered = sample.filter(s=>!q || s.name.toLowerCase().includes(q.toLowerCase()) || s.cat.toLowerCase().includes(q.toLowerCase()));
            if(filtered.length===0){
                resultsList.innerHTML = '<div class="empty-state card-shadow">Nenhum profissional encontrado. Tente outro filtro.</div>';
            } else {
                resultsList.innerHTML = filtered.map(p=>`
          <div class="col-12">
            <div class="card card-shadow">
              <div class="card-body d-flex gap-3 align-items-center">
                <div class="avatar-sm"></div>
                <div class="flex-grow-1">
                  <h6 class="mb-1">${p.name} <small class="text-muted">— ${p.cat}</small></h6>
                  <div class="small-muted">${p.city} • <span class="badge-rating">${p.rating} ★</span> <span class="text-muted ms-2">${Math.round(Math.random()*100)} avaliações</span></div>
                </div>
                <div class="text-end">
                  <div class="result-price">R$ ${p.price}</div>
                  <a href="profile.html?id=${p.id}" class="btn btn-sm btn-outline-primary mt-2">Ver perfil</a>
                </div>
              </div>
            </div>
          </div>
        `).join('');
            }
        }
    }

    // Messages demo: simple sample conversation
    if(location.pathname.endsWith('messages.html')){
        const convList = document.getElementById('convList');
        const convWindow = document.getElementById('convWindow');
        if(convList && convWindow){
            convList.innerHTML = `
        <a class="list-group-item list-group-item-action active" href="#">João Silva <div class="small text-white-50">• 2 novas</div></a>
        <a class="list-group-item list-group-item-action" href="#">Maria Souza</a>
      `;
            convWindow.innerHTML = `
        <div class="small-muted mb-2">Conversa com João Silva</div>
        <div class="border rounded p-3 mb-2"><strong>João:</strong> Olá, posso ajudar com a instalação?</div>
        <div class="border rounded p-3 mb-2 text-end"><strong>Você:</strong> Sim, qual o valor e disponibilidade?</div>
      `;
        }
    }

    // Hire flow demo: show provider name if query param
    if(location.pathname.endsWith('hire.html')){
        const params = new URLSearchParams(location.search);
        const pid = params.get('provider') || 'João Silva';
        const hireTitle = document.getElementById('hireTitle');
        if(hireTitle) hireTitle.textContent = `Contratar ${pid}`;
        const hireForm = document.getElementById('hireForm');
        if(hireForm){
            hireForm.addEventListener('submit', e=>{
                e.preventDefault();
                alert('Pedido enviado. Aguarde resposta do prestador (demo).');
                window.location.href = 'messages.html';
            });
        }
    }

    // Profile page: populate sample data
    if(location.pathname.endsWith('profile.html')){
        const nameEl = document.getElementById('providerName');
        if(nameEl) nameEl.textContent = 'João Silva';
        const ratingEl = document.getElementById('providerRating');
        if(ratingEl) ratingEl.textContent = '4.8';
        const priceEl = document.getElementById('providerPrice');
        if(priceEl) priceEl.textContent = 'R$ 150';
    }
    // Recuperação unificada: pergunta sempre visível
    (function setupRecoverFullForm(){
        const form = document.getElementById('recoverFullForm');
        if(!form) return;

        const emailInput = document.getElementById('recoverEmail');
        const qText = document.getElementById('recoverQuestionText');
        const qSelect = document.getElementById('recoverQuestionSelect');
        const qCustomWrap = document.getElementById('recoverCustomQuestionWrap');
        const qCustomInput = document.getElementById('recoverCustomQuestion');
        const answerInput = document.getElementById('recoverAnswer');
        const newPassInput = document.getElementById('recoverNewPassword');

        // Função para mostrar pergunta salva (se existir) ou fallback para seleção
        function updateQuestionDisplayForEmail(email){
            const users = (function(){ try { return JSON.parse(localStorage.getItem('ms_users')||'{}'); } catch(e){ return {}; } })();
            if(email && users[email] && users[email].securityQuestion){
                // mostra a pergunta salva e esconde o select
                qText.textContent = users[email].securityQuestion;
                qText.classList.remove('text-muted');
                qSelect.classList.add('d-none');
                qCustomWrap.classList.add('d-none');
            } else {
                // não encontrou: mostra select para o usuário escolher
                qText.textContent = '';
                qSelect.classList.remove('d-none');
                // se select estiver em "outro", mostrar custom
                if(qSelect.value === 'outro') qCustomWrap.classList.remove('d-none');
                else qCustomWrap.classList.add('d-none');
            }
        }

        // Ao digitar/alterar email, atualiza a pergunta exibida
        emailInput.addEventListener('blur', ()=> updateQuestionDisplayForEmail(emailInput.value.trim()));
        emailInput.addEventListener('input', ()=> {
            // enquanto digita, limpa a pergunta exibida para evitar confusão
            qText.textContent = '';
        });

        // Quando o usuário escolhe "Outra pergunta" no select, mostra campo custom
        qSelect.addEventListener('change', (e)=>{
            if(e.target.value === 'outro') qCustomWrap.classList.remove('d-none');
            else qCustomWrap.classList.add('d-none');
        });

        // Submit: valida e tenta redefinir senha
        form.addEventListener('submit', (e)=>{
            e.preventDefault();
            if(!form.checkValidity()){ form.classList.add('was-validated'); return; }

            const email = emailInput.value.trim();
            const users = (function(){ try { return JSON.parse(localStorage.getItem('ms_users')||'{}'); } catch(e){ return {}; } })();

            // Determinar pergunta e resposta esperada
            let expectedAnswer = null;
            let questionText = '';

            if(email && users[email] && users[email].securityQuestion){
                questionText = users[email].securityQuestion;
                expectedAnswer = users[email].securityAnswer || '';
            } else {
                // usuário escolheu pergunta manualmente
                const sel = qSelect.value;
                if(sel === 'outro'){
                    questionText = qCustomInput.value.trim() || 'Pergunta personalizada';
                } else {
                    questionText = sel || '';
                }
                // como não há usuário cadastrado com esse email, não há resposta esperada
                expectedAnswer = null;
            }

            const providedAnswer = answerInput.value.trim();
            const newPass = newPassInput.value.trim();

            // Se existe usuário com esse email, validar resposta
            if(email && users[email]){
                if(expectedAnswer === null){
                    alert('Erro interno: pergunta não encontrada para este usuário.');
                    return;
                }
                if(providedAnswer !== expectedAnswer){
                    alert('Resposta incorreta.');
                    return;
                }
                // redefinir senha (demo)
                users[email].password = newPass;
                localStorage.setItem('ms_users', JSON.stringify(users));
                alert('Senha redefinida com sucesso (demo). Faça login.');
                window.location.href = 'login.html';
                return;
            }

            // Se não existe usuário com esse email, permitir fluxo alternativo:
            // aqui optamos por criar um "registro temporário" apenas para demo ou avisar usuário
            // comportamento escolhido: avisar que email não foi encontrado
            alert('Email não encontrado. Verifique o endereço ou crie uma conta.');
        });

        // Inicializa display (caso já haja valor no campo)
        updateQuestionDisplayForEmail(emailInput.value.trim());
    })();

});
