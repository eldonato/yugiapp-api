package don.leo.yugiapp.service.usuario;

import don.leo.yugiapp.data.entities.Permissao;
import don.leo.yugiapp.data.entities.Usuario;
import don.leo.yugiapp.data.repositories.PermissaoRepository;
import don.leo.yugiapp.data.repositories.UsuarioRepository;
import don.leo.yugiapp.service.pessoa.PessoaService;
import don.leo.yugiapp.shared.PermissaoEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PermissaoRepository permissaoRepository;
    private final PessoaService pessoaService;

    public void cadastrarOrganizador(CadastroRequest request) {
        var permissao = permissaoRepository.findByDescricao(PermissaoEnum.ORGANIZADOR.name());
        repository.save(toUsuario(request, permissao));
    }

    public void cadastrarAdmin(CadastroRequest request) {
        var permissao = permissaoRepository.findByDescricao(PermissaoEnum.ADMIN.name());
        repository.save(toUsuario(request, permissao));
    }

    public Optional<Usuario> buscarPorNomeDeUsuario(String username) {
        return repository.findByUsername(username);
    }

    public Optional<Usuario> buscarPorId(UUID uuid) {
        return repository.findById(uuid);
    }

    private Usuario toUsuario(CadastroRequest request, Permissao... permissoes) {
        var pessoa = pessoaService.buscarOuCriar(request.pessoa());
        return Usuario.builder()
                .username(request.usuario())
                .senha(passwordEncoder.encode(request.senha()))
                .pessoa(pessoa)
                .permissoes(Set.of(permissoes))
                .build();
    }

    public void alterarSenha(UUID uuid, String senhaAtual) {
        var usuario = buscarPorId(uuid)
                .orElseThrow(usuarioNaoEncontrado());
        usuario.setSenha(passwordEncoder.encode(senhaAtual));
        repository.save(usuario);
    }

    private static Supplier<EntityNotFoundException> usuarioNaoEncontrado() {
        return () -> new EntityNotFoundException("Usuário não encontrado");
    }
}
