package don.leo.yugiapp.facade.loja;

import don.leo.yugiapp.service.loja.LojaService;
import don.leo.yugiapp.service.loja.LojaValidator;
import don.leo.yugiapp.service.loja.FiltroLoja;
import don.leo.yugiapp.service.loja.LojaRecord;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LojaFacade {

    private final LojaService service;
    private final LojaValidator validator;

    public LojaRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(LojaRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<LojaRecord> listar(FiltroLoja filtro) {
        return service.listar(filtro)
                .stream().map(LojaRecord::new)
                .toList();
    }

    public LojaRecord cadastrar(LojaRecord record) {
        validator.validarCadastro(record);
        return new LojaRecord(service.cadastrar(record));
    }

    public LojaRecord atualizar(Integer id, LojaRecord record) {
        validator.validarAtualizacao(record);
        return new LojaRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        service.deletar(id);
    }
}
