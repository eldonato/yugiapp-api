package don.leo.yugiapp.service.liga;

import don.leo.yugiapp.data.entities.Liga;
import don.leo.yugiapp.data.repositories.LigaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LigaValidator {

    private final LigaRepository repository;

    public void validarCadastro(LigaRecord record) {

        Assert.isNull(record.id(), "Id não deve ser informado para cadastrar");
        Assert.hasText(record.descricao(), "Descrição é obrigatório");
        Assert.notNull(record.dataInicio(), "Data Início é obrigatório");
        Assert.notNull(record.dataFim(), "Data Fim é obrigatório");

        if (record.dataInicio().isAfter(record.dataFim())) {
            throw new IllegalArgumentException("Período informado inválido");
        }
    }

    public void validarAtualizacao(Integer id, LigaRecord record) {

        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.descricao(), "Descrição é obrigatório");
        Assert.notNull(record.dataInicio(), "Data Início é obrigatório");
        Assert.notNull(record.dataFim(), "Data Fim é obrigatório");

        if (record.dataInicio().isAfter(record.dataFim())) {
            throw new IllegalArgumentException("Período informado inválido");
        }

        if (id.equals(record.id())) {
            throw new IllegalArgumentException("Id informado é diferente da entidade");
        }

        var liga = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (emAndamento(liga)) {
            throw new IllegalArgumentException("Não é possível editar uma liga em andamento");
        }

        if (encerrou(liga)) {
            throw new IllegalArgumentException("Não é possível editar uma liga já encerrada");
        }
    }

    public void validarDelecao(Integer id) {

        Assert.notNull(id, "Id deve ser informado");

        var liga = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (emAndamento(liga)) {
            throw new IllegalArgumentException("Não é possível deletar uma liga em andamento");
        }

        if (encerrou(liga)) {
            throw new IllegalArgumentException("Não é possível deletar uma liga já encerrada");
        }
    }

    private boolean emAndamento(Liga liga) {
        var hoje = LocalDate.now();

        return hoje.isAfter(liga.getDataInicio()) && hoje.isBefore(liga.getDataFim());
    }

    private boolean encerrou(Liga liga) {
        var hoje = LocalDate.now();

        return hoje.isAfter(liga.getDataFim());
    }
}
