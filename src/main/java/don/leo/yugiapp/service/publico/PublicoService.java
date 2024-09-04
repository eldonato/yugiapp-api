package don.leo.yugiapp.service.publico;

import don.leo.yugiapp.data.entities.Classificacao;
import don.leo.yugiapp.data.entities.Liga;
import don.leo.yugiapp.service.classificacao.ClassificacaoService;
import don.leo.yugiapp.service.classificacao.FiltroClassificacao;
import don.leo.yugiapp.service.liga.FiltroLiga;
import don.leo.yugiapp.service.liga.LigaPredicate;
import don.leo.yugiapp.service.liga.LigaService;
import don.leo.yugiapp.service.publico.records.ClassificacaoLiga;
import don.leo.yugiapp.service.publico.records.RankingJogadorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublicoService {

    private final LigaService ligaService;
    private final ClassificacaoService classificacaoService;

    public ClassificacaoLiga classificacaoLiga(FiltroLiga filtro) {
        Liga liga = ligaService.buscarUm(LigaPredicate.criarPredicate(filtro))
                .orElseThrow(() -> new RuntimeException("Não há liga em andamento."));

        var filtroClassificacoes = FiltroClassificacao.builder().idLiga(liga.getId()).build();
        List<Classificacao> classificacoes = classificacaoService.listar(filtroClassificacoes);

        var rankingMapeado = getRankingJogadores(classificacoes);

        return new ClassificacaoLiga(
                liga.getId(),
                liga.getDescricao(),
                rankingMapeado
        );
    }

    /**
     * Mapeia as classificações com base no Kossy de cada jogador, soma as pontuações
     * e retorna em ordem decrescente
     *
     * @param classificacoes As classificações a serem calculadas
     * @return O ranking dos jogadores ordenado por pontuação
     */
    private List<RankingJogadorDTO> getRankingJogadores(List<Classificacao> classificacoes) {
        Map<String, RankingJogadorDTO> rankingJogadorMap = new HashMap<>();
        for (Classificacao c : classificacoes) {
            var rankingJogador = new RankingJogadorDTO(c);
            var rankingMapeado = rankingJogadorMap.getOrDefault(rankingJogador.getKossy(), new RankingJogadorDTO());

            rankingJogador.setPontuacao(rankingJogador.getPontuacao() + rankingMapeado.getPontuacao());
            rankingJogador.getDecks().addAll(rankingJogador.getDecks());

            rankingJogadorMap.put(rankingJogador.getKossy(), rankingJogador);
        }

        return rankingJogadorMap.values().stream()
                .sorted(Comparator.comparing(RankingJogadorDTO::getPontuacao).reversed())
                .toList();
    }
}
