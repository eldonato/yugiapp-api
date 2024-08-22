package don.leo.yugiapp.service.liga;


import don.leo.yugiapp.data.entities.Liga;

import java.time.LocalDate;

public record LigaRecord(
        Integer id,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim
) {
    public LigaRecord(Liga liga) {
        this(
                liga.getId(),
                liga.getDescricao(),
                liga.getDataInicio(),
                liga.getDataFim()
        );
    }
}
