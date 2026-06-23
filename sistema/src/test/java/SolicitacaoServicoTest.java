import model.entity.Categoria;
import model.entity.PropostaServico;
import model.entity.StatusPropostaServico;
import model.entity.StatusSolicitacaoServico;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitacaoServicoTest {

    @Test
    void devePublicarSolicitacaoEEnviarProposta() {

        SolicitacaoServicoServiceImp solicitacaoService =
                new SolicitacaoServicoServiceImp();

        PropostaServicoServiceImp propostaService =
                new PropostaServicoServiceImp();

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Frontend");

        solicitacaoService.publicarSolicitacao(
                1L,
                "Criar site",
                "Preciso de um site simples",
                300.0,
                categoria,
                LocalDate.now().plusDays(10)
        );

        var solicitacoes =
                solicitacaoService.listarAbertas();

        assertFalse(solicitacoes.isEmpty());

        var solicitacao =
                solicitacoes.get(0);

        assertEquals(
                StatusSolicitacaoServico.ABERTA,
                solicitacao.getStatus()
        );

        assertEquals(
                "Frontend",
                solicitacao.getCategoria().getNome()
        );

        propostaService.enviarProposta(
                solicitacao.getId(),
                2L,
                250.0,
                "Faço em HTML, CSS e Java",
                LocalDate.now().plusDays(7)
        );

        var propostas =
                propostaService.listarPorSolicitacao(
                        solicitacao.getId()
                );

        assertEquals(1, propostas.size());

        PropostaServico proposta =
                propostas.get(0);

        assertEquals(
                StatusPropostaServico.PENDENTE,
                proposta.getStatus()
        );
    }
}