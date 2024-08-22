package don.leo.yugiapp.service.jogador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class JogadorValidator {

    public void validarCadastro(JogadorRecord record) {
        Assert.hasText(record.kossy(), "Nome é obrigatório");
    }

    public void validarAtualizacao(JogadorRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.kossy(), "Kossy é obrigatório");
    }

}
