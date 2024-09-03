package don.leo.yugiapp.controller;

import don.leo.yugiapp.facade.login.AuthFacade;
import don.leo.yugiapp.service.login.LoginRequest;
import don.leo.yugiapp.service.usuario.AlterarSenhaRecord;
import don.leo.yugiapp.service.usuario.CadastroRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autorização")
public class AuthController {

    private final AuthFacade facade;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return facade.login(request);
    }

    @PostMapping("/cadastrar-organizador")
    public void cadastrarOrganizador(@RequestBody CadastroRequest request) {
        facade.cadastrarOrganizador(request);
    }

    @PostMapping("/cadastrar-admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void cadastrarAdmin(@RequestBody CadastroRequest request, JwtAuthenticationToken token) {
        facade.cadastrarAdmin(request);
    }

    @PostMapping(("/alterar-senha"))
    public void alterarSenha(@RequestBody AlterarSenhaRecord record, JwtAuthenticationToken token) {
        facade.alterarSenha(record, token);
    }
}
