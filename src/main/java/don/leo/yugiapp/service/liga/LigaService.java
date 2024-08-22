package don.leo.yugiapp.service.liga;

import don.leo.yugiapp.data.entities.Liga;
import don.leo.yugiapp.data.repositories.LigaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LigaService {

    private final LigaRepository repository;

    public Optional<Liga> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Liga> listar(FiltroLiga filtro) {
        var predicate = LigaPredicate.criarPredicate(filtro);
        return repository.listar(predicate);
    }

    @Transactional
    public Liga cadastrar(LigaRecord record) {
        return repository.save(toLiga(record));
    }

    @Transactional
    public Liga atualizar(Integer id, LigaRecord record) {
        var liga = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        liga.setDescricao(record.descricao());
        liga.setDataInicio(record.dataInicio());
        liga.setDataFim(record.dataFim());

        return repository.save(liga);
    }

    private Liga toLiga(LigaRecord record) {
        return new Liga(null, record.descricao(), record.dataInicio(), record.dataFim());
    }

    public void deletar(Integer id) {
        var liga = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        repository.delete(liga);
    }
}
