package don.leo.yugiapp.service.jogador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class JogadorValidator {

    private final JogadorService service;

    public void validarCadastro(JogadorRecord record) {
        Assert.hasText(record.kossy(), "Nome é obrigatório");
        Assert.isTrue(record.kossy().length() == 10, "O Kossy deve ter 10 dígitos");
        if (service.kossyCadastrado(record.kossy())) {
            throw new IllegalArgumentException("Kossy já cadastrado");
        }
    }

    public void validarAtualizacao(JogadorRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.kossy(), "Kossy é obrigatório");
    }

}
