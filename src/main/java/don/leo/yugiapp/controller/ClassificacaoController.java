package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.classificacao.ClassificacaoFacade;
import don.leo.yugiapp.service.classificacao.ClassificacaoMinimaRecord;
import don.leo.yugiapp.service.classificacao.ClassificacaoRecord;
import don.leo.yugiapp.service.classificacao.FiltroClassificacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classificacoes")
@Tag(name = "Classificacoes", description = "Gerenciamento de Classificacoes")
public class ClassificacaoController {

    private final ClassificacaoFacade facade;

    // Endpoints Publicos

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar")
    ClassificacaoRecord detalhar(@PathVariable Integer id) {
        return facade.detalhar(id);
    }

    @GetMapping()
    @Operation(summary = "Listar")
    List<ClassificacaoMinimaRecord> listar(FiltroClassificacao filtro) {
        return facade.listar(filtro);
    }


    // Endpoints Restritos
    @PostMapping()
    @Operation(summary = "Cadastrar")
    ClassificacaoRecord cadastrar(@RequestBody ClassificacaoRecord record) {
        return facade.cadastrar(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar")
    ClassificacaoRecord atualizar(@PathVariable Integer id,
                           @RequestBody ClassificacaoRecord record) {
        return facade.atualizar(id, record);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar")
    void deletar(@PathVariable Integer id) {
        facade.deletar(id);
    }
}
