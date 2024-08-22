package don.leo.yugiapp.facade.jogador;

import don.leo.yugiapp.service.jogador.FiltroJogador;
import don.leo.yugiapp.service.jogador.JogadorRecord;
import don.leo.yugiapp.service.jogador.JogadorService;
import don.leo.yugiapp.service.jogador.JogadorValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JogadorFacade {

    private final JogadorService service;
    private final JogadorValidator validator;

    public JogadorRecord detalhar(Integer id) {
        return service.detalhar(id)
                .map(JogadorRecord::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<JogadorRecord> listar(FiltroJogador filtro) {
        return service.listar(filtro)
                .stream().map(JogadorRecord::new)
                .toList();
    }

    public JogadorRecord cadastrar(JogadorRecord record) {
        validator.validarCadastro(record);
        return new JogadorRecord(service.cadastrar(record));
    }

    public JogadorRecord atualizar(Integer id, JogadorRecord record) {
        validator.validarAtualizacao(record);
        return new JogadorRecord(service.atualizar(id, record));
    }

    public void deletar(Integer id) {
        service.deletar(id);
    }
}
