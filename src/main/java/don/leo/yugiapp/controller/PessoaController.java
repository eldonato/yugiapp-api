package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.pessoa.PessoaFacade;
import don.leo.yugiapp.service.pessoa.FiltroPessoa;
import don.leo.yugiapp.service.pessoa.PessoaRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Gerenciamento de Pessoas")
public class PessoaController {

    private final PessoaFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    PessoaRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<PessoaRecord> listar(FiltroPessoa filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    PessoaRecord cadastrar(@RequestBody PessoaRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    PessoaRecord atualizar(@PathVariable Integer id,
                           @RequestBody PessoaRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
