package don.leo.yugiapp.facade.publico;

import don.leo.yugiapp.service.liga.FiltroLiga;
import don.leo.yugiapp.service.publico.PublicoService;
import don.leo.yugiapp.service.publico.records.ClassificacaoLiga;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PublicoFacade {

    private final PublicoService service;

    public ClassificacaoLiga classificacaoLiga(FiltroLiga filtro) {
        filtro = filtroOrDefault(filtro);
        return service.classificacaoLiga(filtro);
    }

    private FiltroLiga filtroOrDefault(FiltroLiga filtro) {
        if (filtro.id() == null && filtro.data() == null) {
            return FiltroLiga.builder().data(LocalDate.now()).build();
        }
        return filtro;
    }
}
