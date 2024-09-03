package don.leo.yugiapp.facade.login;

import don.leo.yugiapp.service.login.AuthService;
import don.leo.yugiapp.service.login.LoginRequest;
import don.leo.yugiapp.service.usuario.AlterarSenhaRecord;
import don.leo.yugiapp.service.usuario.CadastroRequest;
import don.leo.yugiapp.service.usuario.UsuarioService;
import don.leo.yugiapp.service.usuario.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthService authService;
    private final UsuarioService usuarioService;
    private final UsuarioValidator usuarioValidator;

    public String login(LoginRequest request) {
        var usuario = request.usuario();
        try {
            log.info("Realizando login do usuario >> {} <<", usuario);
            var token = authService.logar(request);
            log.info("Usuario >> {} << logado com sucesso", usuario);
            return token;
        } catch (Exception e) {
            log.error("Erro ao logar ao usuario >> {} <<", usuario);
            throw e;
        }
    }

    public void cadastrarOrganizador(CadastroRequest request) {
        try {
            logCadastro("organizador");
            usuarioValidator.validarCadastro(request);
            usuarioService.cadastrarOrganizador(request);
            logSucessoCadastro(request);
        } catch (Exception e) {
            logErroCadastro();
            throw e;
        }
    }

    public void cadastrarAdmin(CadastroRequest request) {
        try {
            logCadastro("admin");
            usuarioService.cadastrarAdmin(request);
            logSucessoCadastro(request);
        } catch (Exception e) {
            logErroCadastro();
            throw e;
        }
    }

    public void alterarSenha(AlterarSenhaRecord record, JwtAuthenticationToken token) {
        var tokenName = token.getName();
        try {
            log.info("Tentando alterar senha do usuario >> {} <<", tokenName);
            usuarioValidator.validarAlterarSenha(record, token);
            UUID uuid = UUID.fromString(tokenName);

            usuarioService.alterarSenha(uuid, record.senhaAtual());
            log.info("Senha do usuario >> {} << alterada com sucesso", tokenName);
        } catch (Exception e) {
            log.error("Erro ao alterar senha de usuario >> {} <<", tokenName);
            throw e;
        }
    }

    private static void logErroCadastro() {
        log.error("Erro ao cadastrar usuario");
    }

    private static void logCadastro(String tipo) {
        log.info("Cadastrando usuario {}", tipo);
    }

    private static void logSucessoCadastro(CadastroRequest request) {
        log.info("Admin >> {} << cadastrado", request.usuario());
    }
}
