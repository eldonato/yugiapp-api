package don.leo.yugiapp.service.publico.records;

import java.time.LocalDate;
import java.util.List;

public record ClassificacaoLiga(
        Integer id,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<RankingJogadorDTO> rankingJogadores
) {
}
