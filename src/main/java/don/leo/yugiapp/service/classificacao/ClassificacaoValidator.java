package don.leo.yugiapp.service.classificacao;

import don.leo.yugiapp.service.classificacao.records.ClassificacaoEmLoteRecord;
import don.leo.yugiapp.service.classificacao.records.PontuacaoJogador;
import don.leo.yugiapp.service.jogador.JogadorService;
import don.leo.yugiapp.service.torneio.TorneioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ClassificacaoValidator {

    private final ClassificacaoService service;
    private final JogadorService jogadorService;
    private final TorneioService torneioService;

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

    public void validarCadastroEmLote(ClassificacaoEmLoteRecord record) {
        Assert.notNull(record.idTorneio(), "Informe o torneio");
        Assert.notEmpty(record.resultados(), "Informe os resultados");

        var idsJogadores = record.resultados().stream().map(PontuacaoJogador::idJogador).toList();
        if (!jogadorService.todosIdsExistem(idsJogadores)) {
            throw new IllegalArgumentException("Algum jogador não pode ser encontrado.");
        }

        if (!torneioService.idExiste(record.idTorneio())) {
            throw new EntityNotFoundException("Torneio não foi encontrado");
        }
    }
}
