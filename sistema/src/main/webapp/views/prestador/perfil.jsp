<%@ page contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8" %>

<%@ page import="model.entity.Prestador" %>

<%
    Object usuario =
            session.getAttribute("usuarioLogado");

    if (usuario == null
            || !(usuario instanceof Prestador)) {

        response.sendRedirect(
                request.getContextPath()
                        + "/views/geral/login.jsp"
        );

        return;
    }
%>


<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil do Prestador</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold text-success" href="#">
            <i class="fa-solid fa-screwdriver-wrench"></i>
            Meu Sobrinho
        </a>

        <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Início</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">Categorias</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">Mensagens</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- PERFIL -->
<section class="container py-5">

    <div class="profile-card">

        <!-- HEADER -->
        <div class="profile-header">

            <div class="row align-items-center">

                <div class="col-lg-2 text-center">
                    <img
                            src="https://randomuser.me/api/portraits/men/32.jpg"
                            class="profile-avatar"
                    >
                </div>

                <div class="col-lg-7 mt-4 mt-lg-0">

                    <h1 class="profile-name">
                        João Silva
                    </h1>

                    <p class="profile-role">
                        Eletricista • Instalador
                    </p>

                    <div class="profile-rating">
                        <i class="fa-solid fa-star"></i>
                        4.8 (23 avaliações)
                    </div>

                    <div class="profile-actions">

                        <!-- POPUP CONTATO -->
                        <button
                                class="btn-main"
                                data-bs-toggle="modal"
                                data-bs-target="#contatoModal"
                        >
                            <i class="fa-solid fa-phone"></i>
                            Ver contato
                        </button>

                        <!-- SOLICITAR SERVIÇO -->
                        <button
                                class="btn-dark-custom"
                                data-bs-toggle="modal"
                                data-bs-target="#servicoModal"
                        >
                            <i class="fa-solid fa-briefcase"></i>
                            Solicitar serviço
                        </button>

                    </div>

                </div>

                <div class="col-lg-3 mt-4 mt-lg-0">

                    <div class="price-box">
                        <h5>Valor médio</h5>

                        <h2>R$ 150</h2>

                        <p>por serviço</p>
                    </div>

                </div>

            </div>

        </div>

        <!-- BODY -->
        <div class="profile-body">

            <h3 class="profile-section-title">
                Sobre mim
            </h3>

            <p class="profile-description">
                Profissional com mais de 10 anos de experiência
                em instalações elétricas residenciais e comerciais.
                Comprometido com qualidade, segurança e pontualidade.
            </p>

            <!-- STATUS -->
            <div id="statusServico" class="service-status status-pending">
                Serviço ainda não solicitado.
            </div>

            <!-- TIMELINE -->
            <div class="service-timeline">

                <div class="timeline-step">

                    <div class="timeline-icon">
                        <i class="fa-solid fa-paper-plane"></i>
                    </div>

                    <div class="timeline-content">
                        <h6>Solicitação enviada</h6>
                        <p>O cliente requisitou o serviço</p>
                    </div>

                </div>

                <div class="timeline-step">

                    <div class="timeline-icon">
                        <i class="fa-solid fa-check"></i>
                    </div>

                    <div class="timeline-content">
                        <h6>Prestador confirmou</h6>
                        <p>O prestador aprovou o serviço</p>
                    </div>

                </div>

                <div class="timeline-step">

                    <div class="timeline-icon">
                        <i class="fa-solid fa-star"></i>
                    </div>

                    <div class="timeline-content">
                        <h6>Avaliação liberada</h6>
                        <p>Cliente poderá avaliar após confirmação</p>
                    </div>

                </div>

            </div>

            <!-- BOTÃO PRESTADOR -->
            <div class="mt-4">

                <button
                        class="confirm-service-btn"
                        onclick="confirmarServico()"
                >
                    Confirmar serviço
                </button>

            </div>

            <!-- AVALIAÇÃO -->
            <div id="reviewSection" class="review-section">

                <h3 class="review-title">
                    Avaliar serviço
                </h3>

                <div class="stars">

                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>

                </div>

                <div class="review-box">

          <textarea
                  class="form-control"
                  placeholder="Conte sua experiência..."
          ></textarea>

                    <button class="review-btn">
                        Enviar avaliação
                    </button>

                </div>

            </div>

        </div>

    </div>

</section>

<!-- MODAL CONTATO -->
<div class="modal fade" id="contatoModal">

    <div class="modal-dialog modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">

                <h5 class="modal-title">
                    Contato do prestador
                </h5>

                <button
                        class="btn-close"
                        data-bs-dismiss="modal"
                ></button>

            </div>

            <div class="modal-body">

                <div class="contact-item">
                    <i class="fa-solid fa-phone"></i>
                    (21) 99999-9999
                </div>

                <div class="contact-item">
                    <i class="fa-solid fa-envelope"></i>
                    joao@email.com
                </div>

                <div class="contact-item">
                    <i class="fa-solid fa-location-dot"></i>
                    Seropédica - RJ
                </div>

            </div>

        </div>

    </div>

</div>

<!-- MODAL SERVIÇO -->
<div class="modal fade" id="servicoModal">

    <div class="modal-dialog modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">

                <h5 class="modal-title">
                    Solicitar serviço
                </h5>

                <button
                        class="btn-close"
                        data-bs-dismiss="modal"
                ></button>

            </div>

            <div class="modal-body">

                <div class="request-card">

                    <div class="mb-3">

                        <label class="form-label">
                            Tipo de serviço
                        </label>

                        <input
                                type="text"
                                class="form-control"
                                placeholder="Ex: instalação elétrica"
                        >

                    </div>

                    <div class="mb-3">

                        <label class="form-label">
                            Descrição
                        </label>

                        <textarea
                                class="form-control"
                                rows="5"
                                placeholder="Descreva o serviço..."
                        ></textarea>

                    </div>

                    <button
                            class="btn-main w-100"
                            onclick="solicitarServico()"
                            data-bs-dismiss="modal"
                    >
                        Enviar solicitação
                    </button>

                </div>

            </div>

        </div>

    </div>

</div>

<!-- JS -->
<script>

    function solicitarServico() {

        const status = document.getElementById('statusServico');

        status.className = 'service-status status-pending';

        status.innerHTML =
            'Solicitação enviada. Aguardando confirmação do prestador.';
    }

    function confirmarServico() {

        const status = document.getElementById('statusServico');

        status.className = 'service-status status-confirmed';

        status.innerHTML =
            'Serviço confirmado pelo prestador. Avaliação liberada.';

        document
            .getElementById('reviewSection')
            .classList
            .add('active');
    }

    // SISTEMA DE ESTRELAS
    const stars = document.querySelectorAll('.stars i');

    stars.forEach((star, index) => {

        star.addEventListener('click', () => {

            stars.forEach((s, i) => {

                if(i <= index) {
                    s.classList.add('active');
                } else {
                    s.classList.remove('active');
                }

            });

        });

    });

</script>

<!-- Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>