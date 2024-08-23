package don.leo.yugiapp.service.torneio;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FiltroTorneio(
        String descricao,
        Integer idLiga,
        Integer idLoja,
        LocalDate dataRealizacao,
        LocalDate periodoInicio,
        LocalDate periodoFim
) {
}
