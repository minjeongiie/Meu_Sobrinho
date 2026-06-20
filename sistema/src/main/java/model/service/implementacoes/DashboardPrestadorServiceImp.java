package model.service.implementacoes;

import model.entity.Contratacao;
import model.entity.DashboardPrestador;
import model.entity.StatusContratacao;
import model.service.interfaces.AvaliacaoService;
import model.service.interfaces.ContratacaoService;
import model.service.interfaces.DashboardPrestadorService;

import java.util.List;

public class DashboardPrestadorServiceImp implements DashboardPrestadorService {

    private final ContratacaoService contratacaoService;
    private final AvaliacaoService avaliacaoService;

    public DashboardPrestadorServiceImp() {
        this.contratacaoService = new ContratacaoServiceImp();
        this.avaliacaoService = new AvaliacaoServiceImp();
    }

    @Override
    public DashboardPrestador gerarDashboard(Long prestadorId) {

        List<Contratacao> contratacoes =
                contratacaoService.listarPorPrestador(prestadorId);

        DashboardPrestador dashboard = new DashboardPrestador();

        dashboard.setTotalSolicitacoes(contratacoes.size());

        int pendentes = 0;
        int aceitas = 0;
        int recusadas = 0;
        int concluidas = 0;

        for (Contratacao contratacao : contratacoes) {

            if (contratacao.getStatus() == StatusContratacao.PENDENTE ||
                    contratacao.getStatus() == StatusContratacao.CONTRAPROPOSTA) {
                pendentes++;
            }

            if (contratacao.getStatus() == StatusContratacao.ACEITA) {
                aceitas++;
            }

            if (contratacao.getStatus() == StatusContratacao.RECUSADA) {
                recusadas++;
            }

            if (contratacao.getStatus() == StatusContratacao.CONCLUIDA) {
                concluidas++;
            }
        }

        dashboard.setSolicitacoesPendentes(pendentes);
        dashboard.setSolicitacoesAceitas(aceitas);
        dashboard.setSolicitacoesRecusadas(recusadas);
        dashboard.setServicosConcluidos(concluidas);

        double taxaAceitacao = 0;
        double taxaConclusao = 0;

        if (contratacoes.size() > 0) {
            taxaAceitacao =
                    ((aceitas + concluidas) * 100.0)
                            / contratacoes.size();
        }

        if ((aceitas + concluidas) > 0) {
            taxaConclusao =
                    (concluidas * 100.0)
                            / (aceitas + concluidas);
        }

        dashboard.setTaxaAceitacao(
                Math.round(taxaAceitacao * 100.0) / 100.0
        );

        dashboard.setTaxaConclusao(
                Math.round(taxaConclusao * 100.0) / 100.0
        );

        dashboard.setTotalAvaliacoes(
                avaliacaoService.contarAvaliacoesPorPrestador(prestadorId)
        );

        dashboard.setNotaMedia(
                avaliacaoService.calcularMediaPorPrestador(prestadorId)
        );

        return dashboard;
    }
}