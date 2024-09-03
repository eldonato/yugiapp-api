package don.leo.yugiapp.service.pessoa;

import don.leo.yugiapp.data.entities.Pessoa;
import don.leo.yugiapp.data.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public Optional<Pessoa> detalhar(Integer id) {
        return repository.findById(id);
    }

    public List<Pessoa> listar(FiltroPessoa filtro) {
        var predicate = PessoaPredicate.criarPredicate(filtro);
        return repository.listar(predicate);
    }

    @Transactional
    public Pessoa cadastrar(PessoaRecord record) {
        return repository.save(toPessoa(record));
    }

    @Transactional
    public Pessoa atualizar(Integer id, PessoaRecord record) {
        var Pessoa = detalhar(id)
                .orElseThrow(pessoaNaoEncontrada());

        Pessoa.setNome(record.nome());

        return repository.save(Pessoa);
    }

    public Pessoa buscarOuCriar(PessoaRecord record) {
        Pessoa pessoa;
        if (record.id() != null) {
            pessoa = detalhar(record.id())
                    .orElseThrow(pessoaNaoEncontrada());
        } else {
            pessoa = cadastrar(record);
        }

        return pessoa;
    }

    private Pessoa toPessoa(PessoaRecord record) {
        return Pessoa.builder()
                .id(null)
                .nome(record.nome())
                .build();
    }

    public void deletar(Integer id) {
        var Pessoa = detalhar(id)
                .orElseThrow(pessoaNaoEncontrada());

        repository.delete(Pessoa);
    }

    private static Supplier<EntityNotFoundException> pessoaNaoEncontrada() {
        return () -> new EntityNotFoundException("Pessoa não encontrada");
    }
}
