package don.leo.yugiapp.service.liga;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FiltroLiga(
        String descricao,
        LocalDate data
) {
}
