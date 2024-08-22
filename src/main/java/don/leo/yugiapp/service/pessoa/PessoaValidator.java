package don.leo.yugiapp.service.pessoa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class PessoaValidator {

    public void validarCadastro(PessoaRecord record) {
        Assert.hasText(record.nome(), "Nome é obrigatório");
    }

    public void validarAtualizacao(PessoaRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.nome(), "Nome é obrigatório");
    }

}
