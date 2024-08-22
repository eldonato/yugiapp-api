package don.leo.yugiapp.service.pessoa;

import don.leo.yugiapp.data.entities.Pessoa;

public record PessoaRecord(
        Integer id,
        String nome
) {
    public PessoaRecord(Pessoa loja) {
        this(
                loja.getId(),
                loja.getNome()
        );
    }
}
