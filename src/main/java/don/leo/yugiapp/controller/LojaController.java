package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.loja.LojaFacade;
import don.leo.yugiapp.service.loja.FiltroLoja;
import don.leo.yugiapp.service.loja.LojaRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lojas")
@Tag(name = "Lojas", description = "Gerenciamento de Lojas")
public class LojaController {

    private final LojaFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    LojaRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<LojaRecord> listar(FiltroLoja filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    LojaRecord cadastrar(@RequestBody LojaRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    LojaRecord atualizar(@PathVariable Integer id,
                         @RequestBody LojaRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
