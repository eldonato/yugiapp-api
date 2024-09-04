package don.leo.yugiapp.service.publico.records;

import java.util.List;

public record ClassificacaoLiga(
        Integer id,
        String descricao,
        List<RankingJogadorDTO> rankingJogadores
) {
}
