package don.leo.yugiapp.service.torneio;

import don.leo.yugiapp.data.entities.Liga;
import don.leo.yugiapp.data.entities.Loja;
import don.leo.yugiapp.data.entities.Torneio;
import don.leo.yugiapp.data.repositories.TorneioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
@AllArgsConstructor
public class TorneioService {

    private final TorneioRepository repository;

    public boolean idExiste(Integer id) {
        return repository.existsById(id);
    }

    public Optional<Torneio> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Torneio> listar(FiltroTorneio filtro) {
        var predicate = TorneioPredicate.criarPredicate(filtro);
        return repository.listar(predicate);
    }

    @Transactional
    public Torneio cadastrar(TorneioRecord record) {
        return repository.save(toTorneio(record));
    }

    @Transactional
    public Torneio atualizar(Integer id, TorneioRecord record) {
        var torneio = detalhar(id)
                .orElseThrow(torneioNaoEncontrado());

        torneio.setDescricao(record.descricao());
        torneio.setData(record.dataRealizacao());

        return repository.save(torneio);
    }

    private Torneio toTorneio(TorneioRecord record) {
        var loja = Loja.builder().id(record.loja().id()).build();
        var liga = Liga.builder().id(record.liga().id()).build();
        return Torneio.builder()
                .descricao(record.descricao())
                .data(record.dataRealizacao())
                .loja(loja)
                .liga(liga)
                .build();
    }

    public void deletar(Integer id) {
        var Torneio = detalhar(id)
                .orElseThrow(torneioNaoEncontrado());

        repository.delete(Torneio);
    }

    private static Supplier<EntityNotFoundException> torneioNaoEncontrado() {
        return () -> new EntityNotFoundException("Torneio não encontrado");
    }
}
