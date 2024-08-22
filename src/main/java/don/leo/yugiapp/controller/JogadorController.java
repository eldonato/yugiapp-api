package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.jogador.JogadorFacade;
import don.leo.yugiapp.service.jogador.FiltroJogador;
import don.leo.yugiapp.service.jogador.JogadorRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogadores")
@Tag(name = "Jogadores", description = "Gerenciamento de Jogadores")
public class JogadorController {

    private final JogadorFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    JogadorRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<JogadorRecord> listar(FiltroJogador filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    JogadorRecord cadastrar(@RequestBody JogadorRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    JogadorRecord atualizar(@PathVariable Integer id,
                         @RequestBody JogadorRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
