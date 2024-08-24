package don.leo.yugiapp.service.classificacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ClassificacaoValidator {

    private final ClassificacaoService service;

    public void validarCadastro(ClassificacaoRecord record) {
        Assert.notNull(record.pontuacao(), "Pontuação é obrigatória");
        Assert.notNull(record.torneio(), "Informe o torneio");
        Assert.notNull(record.torneio().id(), "Informe o torneio");
        Assert.notNull(record.jogador(), "Informe o jogador");
        Assert.notNull(record.jogador().id(), "Informe o jogador");

        if (service.jogadorJaParticipa(record.jogador().id(), record.torneio().id())) {
            throw new IllegalArgumentException("Classificação do jogador já existe");
        }
    }

    public void validarAtualizacao(ClassificacaoRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.notNull(record.pontuacao(), "Pontuação é obrigatória");
    }

}
