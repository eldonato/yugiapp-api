package don.leo.yugiapp.service.torneio;

import don.leo.yugiapp.data.entities.Torneio;
import don.leo.yugiapp.service.liga.LigaRecord;
import don.leo.yugiapp.service.loja.LojaRecord;

import java.time.LocalDate;

public record TorneioRecord(
        Integer id,
        String descricao,
        LocalDate dataRealizacao,
        LigaRecord liga,
        LojaRecord loja
) {
    public TorneioRecord(Torneio torneio) {
        this(
                torneio.getId(),
                torneio.getDescricao(),
                torneio.getData(),
                new LigaRecord(torneio.getLiga()),
                new LojaRecord(torneio.getLoja())
        );
    }
}
