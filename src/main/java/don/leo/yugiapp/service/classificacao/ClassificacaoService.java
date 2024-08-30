package don.leo.yugiapp.service.classificacao;

import com.querydsl.core.types.OrderSpecifier;
import don.leo.yugiapp.data.entities.Classificacao;
import don.leo.yugiapp.data.entities.Jogador;
import don.leo.yugiapp.data.entities.QClassificacao;
import don.leo.yugiapp.data.entities.Torneio;
import don.leo.yugiapp.data.repositories.ClassificacaoRepository;
import don.leo.yugiapp.service.jogador.JogadorService;
import don.leo.yugiapp.service.torneio.TorneioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static don.leo.yugiapp.service.classificacao.ClassificacaoPredicate.doJogador;
import static don.leo.yugiapp.service.classificacao.ClassificacaoPredicate.doTorneio;


@Service
@AllArgsConstructor
public class ClassificacaoService {

    private final ClassificacaoRepository repository;
    private final JogadorService jogadorService;
    private final TorneioService torneioService;

    public Optional<Classificacao> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Classificacao> listar(FiltroClassificacao filtro) {
        var predicate = ClassificacaoPredicate.criarPredicate(filtro);
        var orders = ordenarPorTorneioEPontuacao();
        return repository.findAll(predicate, orders);
    }

    @Transactional
    public Classificacao cadastrar(ClassificacaoRecord record) {
        var torneio = torneioService.detalhar(record.torneio().id())
                .orElseThrow(torneioNaoEncontrado());
        var jogador = jogadorService.detalhar(record.jogador().id())
                .orElseThrow(jogadorNaoEncontrado());
        return repository.save(toClassificacao(record, torneio, jogador));
    }

    private static Supplier<EntityNotFoundException> torneioNaoEncontrado() {
        return () -> new EntityNotFoundException("Torneio não encontrado");
    }

    private static Supplier<EntityNotFoundException> jogadorNaoEncontrado() {
        return () -> new EntityNotFoundException("Jogador não encontrado");
    }

    @Transactional
    public Classificacao atualizar(Integer id, ClassificacaoRecord record) {
        var classificacao = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        classificacao.setPontuacao(record.pontuacao());
        classificacao.setDeck(record.deck());

        return repository.save(classificacao);
    }

    public void deletar(Integer id) {
        var classificacao = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        repository.delete(classificacao);
    }

    public boolean jogadorJaParticipa(Integer idJogador, Integer idTorneio) {
        var classificacoes = repository.listar(
                doTorneio(idTorneio).and(doJogador(idJogador)));

        return !classificacoes.isEmpty();
    }

    private Classificacao toClassificacao(ClassificacaoRecord record, Torneio torneio, Jogador jogador) {
        return Classificacao.builder()
                .deck(record.deck())
                .pontuacao(record.pontuacao())
                .torneio(torneio)
                .jogador(jogador)
                .build();
    }

    private static OrderSpecifier<?>[] ordenarPorTorneioEPontuacao() {
        var porTorneio = QClassificacao.classificacao.torneio.id.desc();
        var porPontuacao = QClassificacao.classificacao.pontuacao.desc();
        return new OrderSpecifier<?>[]{porTorneio, porPontuacao};
    }
}
