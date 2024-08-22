package don.leo.yugiapp.facade.liga;

import don.leo.yugiapp.service.liga.LigaRecord;
import don.leo.yugiapp.service.liga.LigaService;
import don.leo.yugiapp.service.liga.LigaValidator;
import don.leo.yugiapp.service.liga.FiltroLiga;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LigaFacade {

    private final LigaService service;
    private final LigaValidator validator;

    public LigaRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(LigaRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<LigaRecord> listar(FiltroLiga filtro) {
        return service.listar(filtro)
                .stream().map(LigaRecord::new)
                .toList();
    }

    public LigaRecord cadastrar(LigaRecord record) {
        validator.validarCadastro(record);
        return new LigaRecord(service.cadastrar(record));
    }

    public LigaRecord atualizar(Integer id, LigaRecord record) {
        validator.validarAtualizacao(id, record);
        return new LigaRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        validator.validarDelecao(id);
        service.deletar(id);
    }
}
