package don.leo.yugiapp.service.usuario;

import don.leo.yugiapp.service.pessoa.PessoaRecord;

public record CadastroRequest(
        String usuario,
        String senha,
        PessoaRecord pessoa
) {
}
