package don.leo.yugiapp.facade.pessoa;

import don.leo.yugiapp.service.pessoa.FiltroPessoa;
import don.leo.yugiapp.service.pessoa.PessoaRecord;
import don.leo.yugiapp.service.pessoa.PessoaService;
import don.leo.yugiapp.service.pessoa.PessoaValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PessoaFacade {

    private final PessoaService service;
    private final PessoaValidator validator;

    public PessoaRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(PessoaRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<PessoaRecord> listar(FiltroPessoa filtro) {
        return service.listar(filtro)
                .stream().map(PessoaRecord::new)
                .toList();
    }

    public PessoaRecord cadastrar(PessoaRecord record) {
        validator.validarCadastro(record);
        return new PessoaRecord(service.cadastrar(record));
    }

    public PessoaRecord atualizar(Integer id, PessoaRecord record) {
        validator.validarAtualizacao(record);
        return new PessoaRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        service.deletar(id);
    }
}
