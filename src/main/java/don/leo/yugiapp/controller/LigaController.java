package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.liga.LigaFacade;
import don.leo.yugiapp.service.liga.FiltroLiga;
import don.leo.yugiapp.service.liga.LigaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/liga")
public class LigaController {

    private final LigaFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    LigaRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    List<LigaRecord> listar(FiltroLiga filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    LigaRecord cadastrar(@RequestBody LigaRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    LigaRecord atualizar(@PathVariable Integer id, LigaRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
