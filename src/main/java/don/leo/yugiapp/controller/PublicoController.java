package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.publico.PublicoFacade;
import don.leo.yugiapp.service.liga.FiltroLiga;
import don.leo.yugiapp.service.publico.records.ClassificacaoLiga;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publico")
@RequiredArgsConstructor
@Tag(name = "Publico", description = "Rota utilizada para informações gerais para o público")
public class PublicoController {

    private final PublicoFacade facade;

    @GetMapping("liga/classificacao")
    @Operation(summary = "Busca a liga com base nos parâmetros e uma lista com a pontuação dos jogadores")
    ClassificacaoLiga classificacaoLiga(FiltroLiga filtro) {
        return facade.classificacaoLiga(filtro);
    }

}
