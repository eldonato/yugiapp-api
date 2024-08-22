package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.loja.LojaFacade;
import don.leo.yugiapp.service.loja.FiltroLoja;
import don.leo.yugiapp.service.loja.LojaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loja")
public class LojaController {

    private final LojaFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    LojaRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    List<LojaRecord> listar(FiltroLoja filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    LojaRecord cadastrar(@RequestBody LojaRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    LojaRecord atualizar(@PathVariable Integer id, LojaRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
