package don.leo.yugiapp.facade.torneio;

import don.leo.yugiapp.service.torneio.FiltroTorneio;
import don.leo.yugiapp.service.torneio.TorneioRecord;
import don.leo.yugiapp.service.torneio.TorneioService;
import don.leo.yugiapp.service.torneio.TorneioValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TorneioFacade {

    private final TorneioService service;
    private final TorneioValidator validator;

    public TorneioRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(TorneioRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<TorneioRecord> listar(FiltroTorneio filtro) {
        return service.listar(filtro)
                .stream().map(TorneioRecord::new)
                .toList();
    }

    public TorneioRecord cadastrar(TorneioRecord record) {
        validator.validarCadastro(record);
        return new TorneioRecord(service.cadastrar(record));
    }

    public TorneioRecord atualizar(Integer id, TorneioRecord record) {
        validator.validarAtualizacao(record);
        return new TorneioRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        service.deletar(id);
    }
}
