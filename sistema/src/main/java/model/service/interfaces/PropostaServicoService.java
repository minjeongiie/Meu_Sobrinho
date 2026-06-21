package model.service.interfaces;

import model.entity.PropostaServico;

import java.time.LocalDate;
import java.util.List;

public interface PropostaServicoService {

    void enviarProposta(
            Long solicitacaoId,
            Long prestadorId,
            Double valorProposto,
            String descricao,
            LocalDate prazoConclusao
    );

    void aceitarProposta(Long propostaId);

    void recusarProposta(Long propostaId);

    PropostaServico buscarPorId(Long id);

    List<PropostaServico> listarPorSolicitacao(Long solicitacaoId);

    List<PropostaServico> listarPorPrestador(Long prestadorId);
}