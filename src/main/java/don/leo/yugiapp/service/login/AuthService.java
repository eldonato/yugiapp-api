package don.leo.yugiapp.service.login;


import don.leo.yugiapp.data.entities.Permissao;
import don.leo.yugiapp.data.entities.Usuario;
import don.leo.yugiapp.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtEncoder jwtEncoder;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${spring.application.name}")
    private String issuer;

    public String logar(LoginRequest request) {
        var usuario = usuarioService.buscarPorNomeDeUsuario(request.usuario())
                .orElseThrow(this::credenciaisInvalidas);

        if (!senhaCorreta(request.senha(), usuario)) {
            throw credenciaisInvalidas();
        }

        var claims = criarToken(usuario);

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private JwtClaimsSet criarToken(Usuario usuario) {
        var agora = Instant.now();
        var expiraEm = 60L * 60L * 2L;
        var permissoes = usuario.getPermissoes().stream()
                .map(Permissao::getDescricao)
                .collect(Collectors.joining(" "));

        return JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(usuario.getId().toString())
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(expiraEm))
                .claim("scope", permissoes)
                .build();
    }

    private boolean senhaCorreta(String senha, Usuario usuario) {
        return passwordEncoder.matches(senha, usuario.getSenha());
    }

    private BadCredentialsException credenciaisInvalidas() {
        return new BadCredentialsException("Usuário ou senha inválido");
    }
}
