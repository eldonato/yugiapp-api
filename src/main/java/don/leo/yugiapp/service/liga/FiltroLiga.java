package don.leo.yugiapp.service.liga;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FiltroLiga(
        Integer id,
        String descricao,
        LocalDate data
) {
}
