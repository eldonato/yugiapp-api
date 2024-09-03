package don.leo.yugiapp.facade.login;

import don.leo.yugiapp.service.login.AuthService;
import don.leo.yugiapp.service.login.LoginRequest;
import don.leo.yugiapp.service.usuario.AlterarSenhaRecord;
import don.leo.yugiapp.service.usuario.CadastroRequest;
import don.leo.yugiapp.service.usuario.UsuarioService;
import don.leo.yugiapp.service.usuario.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;
    private final UsuarioService usuarioService;
    private final UsuarioValidator usuarioValidator;

    public String login(LoginRequest request) {
        return authService.logar(request);
    }

    public void cadastrarOrganizador(CadastroRequest request) {
        usuarioValidator.validarCadastro(request);
        usuarioService.cadastrarOrganizador(request);
    }

    public void cadastrarAdmin(CadastroRequest request) {
        usuarioService.cadastrarAdmin(request);
    }

    public void alterarSenha(AlterarSenhaRecord record, JwtAuthenticationToken token) {
        usuarioValidator.validarAlterarSenha(record, token);
        UUID uuid = UUID.fromString(token.getPrincipal().toString());

        usuarioService.alterarSenha(uuid, record.senhaAtual());
    }
}
