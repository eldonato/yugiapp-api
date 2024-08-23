package don.leo.yugiapp.service.torneio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class TorneioValidator {

    public void validarCadastro(TorneioRecord record) {
        infosObrigatorias(record);
    }

    public void validarAtualizacao(TorneioRecord record) {
        infosObrigatorias(record);
    }

    private static void infosObrigatorias(TorneioRecord record) {
        Assert.hasText(record.descricao(), "Nome é obrigatório");
        Assert.notNull(record.dataRealizacao(), "Data é obrigatório");
        Assert.notNull(record.liga().id(), "Informe a liga");
        Assert.notNull(record.loja().id(), "Informe a loja");
    }

}
