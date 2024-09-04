package don.leo.yugiapp.service.classificacao;

import lombok.Builder;

@Builder
public record FiltroClassificacao(
        String deck,
        Integer idTorneio,
        Integer idJogador,
        Integer idLiga
) {
}
