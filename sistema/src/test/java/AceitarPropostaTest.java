import model.entity.Categoria;
import model.entity.StatusPropostaServico;
import model.entity.StatusSolicitacaoServico;
import model.service.implementacoes.PropostaServicoServiceImp;
import model.service.implementacoes.SolicitacaoServicoServiceImp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AceitarPropostaTest {

    @Test
    void deveAceitarUmaPropostaERecusarAsDemais() {

        SolicitacaoServicoServiceImp solicitacaoService =
                new SolicitacaoServicoServiceImp();

        PropostaServicoServiceImp propostaService =
                new PropostaServicoServiceImp();

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Frontend");

        solicitacaoService.publicarSolicitacao(
                1L,
                "Sistema Java",
                "Preciso de um sistema",
                1000.0,
                categoria,
                LocalDate.now().plusDays(15)
        );

        var solicitacao =
                solicitacaoService.listarAbertas().get(0);

        propostaService.enviarProposta(
                solicitacao.getId(),
                2L,
                900.0,
                "Primeira proposta",
                LocalDate.now().plusDays(10)
        );

        propostaService.enviarProposta(
                solicitacao.getId(),
                3L,
                850.0,
                "Segunda proposta",
                LocalDate.now().plusDays(8)
        );

        var propostas =
                propostaService.listarPorSolicitacao(
                        solicitacao.getId()
                );

        Long propostaAceitaId =
                propostas.get(0).getId();

        propostaService.aceitarProposta(
                propostaAceitaId
        );

        propostas =
                propostaService.listarPorSolicitacao(
                        solicitacao.getId()
                );

        assertEquals(
                StatusPropostaServico.ACEITA,
                propostas.get(0).getStatus()
        );

        assertEquals(
                StatusPropostaServico.RECUSADA,
                propostas.get(1).getStatus()
        );

        solicitacao =
                solicitacaoService.buscarPorId(
                        solicitacao.getId()
                );

        assertEquals(
                StatusSolicitacaoServico.FECHADA,
                solicitacao.getStatus()
        );
    }
}