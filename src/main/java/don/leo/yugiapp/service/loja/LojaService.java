package don.leo.yugiapp.service.loja;

import don.leo.yugiapp.data.entities.Loja;
import don.leo.yugiapp.data.repositories.LojaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LojaService {

    private final LojaRepository repository;

    public Optional<Loja> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Loja> listar(FiltroLoja filtro) {
        var predicate = LojaPredicate.criarPredicate(filtro);
        return repository.listar(predicate);
    }

    @Transactional
    public Loja cadastrar(LojaRecord record) {
        return repository.save(toLoja(record));
    }

    @Transactional
    public Loja atualizar(Integer id, LojaRecord record) {
        var Loja = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        Loja.setNome(record.nome());

        return repository.save(Loja);
    }

    private Loja toLoja(LojaRecord record) {
        return Loja.builder()
                .nome(record.nome())
                .build();
    }

    public void deletar(Integer id) {
        var Loja = detalhar(id)
                .orElseThrow(EntityNotFoundException::new);

        repository.delete(Loja);
    }
}
