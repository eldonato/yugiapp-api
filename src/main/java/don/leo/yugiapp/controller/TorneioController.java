package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.torneio.TorneioFacade;
import don.leo.yugiapp.service.torneio.FiltroTorneio;
import don.leo.yugiapp.service.torneio.TorneioRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/torneios")
@Tag(name = "Torneios", description = "Gerenciamento de Torneios")
public class TorneioController {

    private final TorneioFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    TorneioRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<TorneioRecord> listar(FiltroTorneio filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    TorneioRecord cadastrar(@RequestBody TorneioRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    TorneioRecord atualizar(@PathVariable Integer id,
                           @RequestBody TorneioRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
