package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.liga.LigaFacade;
import don.leo.yugiapp.service.liga.FiltroLiga;
import don.leo.yugiapp.service.liga.LigaRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ligas")
@Tag(name = "Ligas", description = "Gerenciamento de Ligas")
public class LigaController {

    private final LigaFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    LigaRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<LigaRecord> listar(FiltroLiga filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    LigaRecord cadastrar(@RequestBody LigaRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    LigaRecord atualizar(@PathVariable Integer id, LigaRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
