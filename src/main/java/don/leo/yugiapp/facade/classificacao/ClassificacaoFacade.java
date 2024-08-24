package don.leo.yugiapp.facade.classificacao;

import don.leo.yugiapp.service.classificacao.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassificacaoFacade {

    private final ClassificacaoService service;
    private final ClassificacaoValidator validator;

    public ClassificacaoRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(ClassificacaoRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<ClassificacaoMinimaRecord> listar(FiltroClassificacao filtro) {
        return service.listar(filtro)
                .stream().map(ClassificacaoMinimaRecord::new)
                .toList();
    }

    public ClassificacaoRecord cadastrar(ClassificacaoRecord record) {
        validator.validarCadastro(record);
        return new ClassificacaoRecord(service.cadastrar(record));
    }

    public ClassificacaoRecord atualizar(Integer id, ClassificacaoRecord record) {
        validator.validarAtualizacao(record);
        return new ClassificacaoRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        service.deletar(id);
    }
}
