package don.leo.yugiapp.service.jogador;

import don.leo.yugiapp.data.entities.Jogador;
import don.leo.yugiapp.data.entities.Pessoa;
import don.leo.yugiapp.data.entities.QJogador;
import don.leo.yugiapp.data.repositories.JogadorRepository;
import don.leo.yugiapp.service.pessoa.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
@AllArgsConstructor
public class JogadorService {

    private final JogadorRepository repository;
    private final PessoaService pessoaService;

    public Optional<Jogador> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Jogador> listar(FiltroJogador filtro) {
        var predicate = JogadorPredicate.criarPredicate(filtro);
        var order = QJogador.jogador.pessoa.nome.asc();
        return repository.listar(predicate, order);
    }

    @Transactional
    public Jogador cadastrar(JogadorRecord record) {
        var pessoa = pessoaService.buscarOuCriar(record.pessoa());
        return repository.save(toJogador(record, pessoa));
    }

    @Transactional
    public Jogador atualizar(Integer id, JogadorRecord record) {
        var jogador = detalhar(id)
                .orElseThrow(jogadorNaoEncontrado());

        pessoaService.atualizar(record.id(), record.pessoa());

        jogador.setKossy(record.kossy());

        return repository.save(jogador);
    }

    public void deletar(Integer id) {
        var jogador = detalhar(id)
                .orElseThrow(jogadorNaoEncontrado());

        repository.delete(jogador);
    }

    public boolean kossyCadastrado(String kossy) {
        return repository.exists(JogadorPredicate.comKossy(kossy));
    }

    private Jogador toJogador(JogadorRecord record, Pessoa pessoa) {
        return Jogador.builder()
                .id(null)
                .kossy(record.kossy())
                .pessoa(pessoa)
                .build();
    }

    private static Supplier<EntityNotFoundException> jogadorNaoEncontrado() {
        return () -> new EntityNotFoundException("Jogador não encontrado");
    }

    /**
     * Realiza um count com base em todos os jogadores com id na lista
     * compara com a quantidade de elementos na lista
     * @return true se for igual, false se for diferente
     */
    public boolean todosIdsExistem(List<Integer> idsJogadores) {
        var idsNoBanco = repository.findAllById(idsJogadores).size();
        return idsNoBanco == idsJogadores.size();
    }
}
