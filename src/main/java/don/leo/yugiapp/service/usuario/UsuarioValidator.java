package don.leo.yugiapp.service.usuario;

import don.leo.yugiapp.service.pessoa.PessoaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;

    public void validarCadastro(CadastroRequest cadastro) {
        Assert.hasText(cadastro.usuario(), "Nome de Usuario é obrigatório");
        Assert.hasText(cadastro.senha(), "Senha é obrigatório");
        Assert.notNull(cadastro.pessoa(), "Informe a pessoa");
        Assert.isTrue(pessoaTemNomeOuId(cadastro.pessoa()), "Informe a pessoa");

        usuarioService.buscarPorNomeDeUsuario(cadastro.usuario())
                .ifPresent(_ -> {
                    throw new IllegalArgumentException("Nome de usuário já cadastrado");
                });
    }

    private boolean pessoaTemNomeOuId(PessoaRecord pessoa) {
        return StringUtils.hasText(pessoa.nome()) || pessoa.id() != null;
    }

    public void validarAtualizacao(PessoaRecord record) {
        Assert.notNull(record.id(), "Id deve ser informado");
        Assert.hasText(record.nome(), "Nome é obrigatório");
    }

    public void validarAlterarSenha(AlterarSenhaRecord record, JwtAuthenticationToken token) {
        Assert.notNull(record.senhaAntiga(), "Senha antiga deve ser informada");
        Assert.notNull(record.senhaAtual(), "Senha atual deve ser informada");
        Assert.isTrue(senhasDiferentes(record.senhaAntiga(), record.senhaAtual()),
                "Senha atual deve ser diferente da anterior");

        var usuario = usuarioService.buscarPorId(UUID.fromString(token.getPrincipal().toString()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Assert.isTrue(passwordEncoder.matches(usuario.getSenha(), record.senhaAntiga()),
                "Senha fornecida incorreta");
    }

    private boolean senhasDiferentes(String senhaAntiga, String senhaNova) {
        return !senhaNova.equals(senhaAntiga);
    }
}
