package don.leo.yugiapp.service.loja;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class LojaValidator {

    public void validarCadastro(LojaRecord record) {
        Assert.hasText(record.nome(), "Nome é obrigatório");
    }

    public void validarAtualizacao(LojaRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.nome(), "Nome é obrigatório");
    }

}
