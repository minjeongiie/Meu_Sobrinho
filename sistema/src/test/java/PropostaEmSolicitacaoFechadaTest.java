import model.entity.Categoria;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropostaEmSolicitacaoFechadaTest {

    @Test
    void naoDeveEnviarPropostaParaSolicitacaoFechada() {

        SolicitacaoServicoServiceImp solicitacaoService =
                new SolicitacaoServicoServiceImp();

        PropostaServicoServiceImp propostaService =
                new PropostaServicoServiceImp();

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Frontend");

        solicitacaoService.publicarSolicitacao(
                1L,
                "Criar landing page",
                "Preciso de uma landing page simples",
                400.0,
                categoria,
                LocalDate.now().plusDays(10)
        );

        var solicitacao =
                solicitacaoService.listarAbertas().get(0);

        solicitacaoService.fecharSolicitacao(
                solicitacao.getId()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> propostaService.enviarProposta(
                        solicitacao.getId(),
                        2L,
                        350.0,
                        "Faço a landing page",
                        LocalDate.now().plusDays(7)
                )
        );
    }
}