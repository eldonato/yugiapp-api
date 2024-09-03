package don.leo.yugiapp.config;

import don.leo.yugiapp.data.entities.QUsuario;
import don.leo.yugiapp.data.entities.Usuario;
import don.leo.yugiapp.data.repositories.PermissaoRepository;
import don.leo.yugiapp.data.repositories.UsuarioRepository;
import don.leo.yugiapp.shared.PermissaoEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private final PermissaoRepository permissaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${aplicacao.credencial.padrao}")
    private String loginSenhaPadrao;

    @Override
    @Transactional
    public void run(String... args) {
        var roleAdmin = permissaoRepository.findByDescricao(PermissaoEnum.ADMIN.name());
        var admins = usuarioRepository.findAll(QUsuario.usuario.permissoes.contains(roleAdmin));

        if (admins.isEmpty()) {
            var senha = passwordEncoder.encode(loginSenhaPadrao);

            var adminPadrao = Usuario.builder()
                    .username(loginSenhaPadrao)
                    .senha(senha)
                    .permissoes(Set.of(roleAdmin))
                    .build();
            usuarioRepository.save(adminPadrao);

            log.warn("Usuário admin padrão criado, não esqueça de alterar as credenciais");
        }

    }

}
